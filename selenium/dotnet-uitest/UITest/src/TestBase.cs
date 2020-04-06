using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Remote;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading;
using UITest.src.Util;
using Xunit.Abstractions;
using System.Linq;
using Xunit.Sdk;

namespace UITest
{
    public abstract class TestBase
    {
        private readonly ITestOutputHelper output;

        public TestBase(ITestOutputHelper output)
        {
            this.output = output;
        }

        public TestEnv env = TestEnv.MainRepo;

        private IWebDriver _driver;

        public string HostName
        {
            get
            {
                switch (env)
                {
                    case TestEnv.Local:
                        return "localhost";
                    case TestEnv.Group:
                        return "d-docker.southeastasia.cloudapp.azure.com";
                    case TestEnv.MainRepo:
                        return "138.91.37.88";
                    default:
                        break;
                }
                throw new Exception("TestEnv wrong");
            }
        }

        //http://d-docker.southeastasia.cloudapp.azure.com:5001/#/FoodCategory
        public string Backend_Food_Category_Url
        {
            get
            {
                return $"http://{HostName}:5001/#/FoodCategory";
            }
        }

        //http://d-docker.southeastasia.cloudapp.azure.com:5001/#/Food
        public string Backend_Food_Url
        {
            get
            {
                return $"http://{HostName}:5001/#/Food";
            }

        }

        //http://d-docker.southeastasia.cloudapp.azure.com:5000

        public IWebDriver GetDriver()
        {
            //InternetExplorerOptions Options = new InternetExplorerOptions();
            if (_driver == null)
            {
                if (env== TestEnv.Local)
                {
                    _driver = new ChromeDriver();
                }
                else
                {
                    var options = new ChromeOptions();
                    //172.17.0.1 是docker宿主的ip，通过这个ip可以访问selenium hub
                    _driver = new RemoteWebDriver(new Uri("http://172.17.0.1:4444/wd/hub/"), options.ToCapabilities(), TimeSpan.FromSeconds(60));
                }
               
            }
            return _driver;
        }

        public string GetXPath(int index)
        {
            return GetXPathArray()[index];
        }

        public abstract string[] GetXPathArray();

        public void Click(int xpahtIndex)
        {
           
            Wait();
            GetDriver().FindElement(By.XPath(GetXPath(xpahtIndex))).Click();
            Wait();
        }

        public void SendKey(int xpahtIndex, string content,bool clearFirst=true)
        {
            //Click(xpahtIndex);
            if (clearFirst)
            {
                ClearText(xpahtIndex);
            }
            GetDriver().FindElement(By.XPath(GetXPath(xpahtIndex))).SendKeys(content);
        }
        public void GoToUrl(string url, Action action)
        {
            using (var driver = GetDriver())
            {
                output.WriteLine($"nav to {url}");
                driver.Navigate().GoToUrl(url);
                Wait(3000);
                action();
            }
        }

        public string GetText(int xpahtIndex)
        {
            return GetDriver().FindElement(By.XPath(GetXPath(xpahtIndex))).Text;
        }

        public void ClearText(int xpahtIndex)
        {
             GetDriver().FindElement(By.XPath(GetXPath(xpahtIndex))).Clear();
        }

        public bool ElementExist(int xpahtIndex,out IWebElement element)
        {
            element = GetDriver().FindElement(By.XPath(GetXPath(xpahtIndex)));
            return element != null;
        }
        public void Wait(int ms= 2000)
        {
            Thread.Sleep(ms);
        }
    }

    public enum TestEnv
    {
        Local,
        Group,
        MainRepo

    }

    [AttributeUsage(AttributeTargets.Method, AllowMultiple = false)]
    public class TestPriorityAttribute : Attribute
    {
        public TestPriorityAttribute(int priority)
        {
            Priority = priority;
        }

        public int Priority { get; private set; }
    }

    public class PriorityOrderer : ITestCaseOrderer
    {
        public IEnumerable<TTestCase> OrderTestCases<TTestCase>(IEnumerable<TTestCase> testCases) where TTestCase : ITestCase
        {
            var sortedMethods = new SortedDictionary<int, List<TTestCase>>();

            foreach (TTestCase testCase in testCases)
            {
                int priority = 0;

                foreach (IAttributeInfo attr in testCase.TestMethod.Method.GetCustomAttributes((typeof(TestPriorityAttribute).AssemblyQualifiedName)))
                    priority = attr.GetNamedArgument<int>("Priority");

                GetOrCreate(sortedMethods, priority).Add(testCase);
            }

            foreach (var list in sortedMethods.Keys.Select(priority => sortedMethods[priority]))
            {
                list.Sort((x, y) => StringComparer.OrdinalIgnoreCase.Compare(x.TestMethod.Method.Name, y.TestMethod.Method.Name));
                foreach (TTestCase testCase in list)
                    yield return testCase;
            }
        }

        static TValue GetOrCreate<TKey, TValue>(IDictionary<TKey, TValue> dictionary, TKey key) where TValue : new()
        {
            TValue result;

            if (dictionary.TryGetValue(key, out result)) return result;

            result = new TValue();
            dictionary[key] = result;

            return result;
        }
    }
}
