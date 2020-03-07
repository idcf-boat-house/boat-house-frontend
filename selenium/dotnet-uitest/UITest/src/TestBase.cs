using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Remote;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading;
using Xunit.Abstractions;

namespace UITest
{
    public class TestBase
    {
        private readonly ITestOutputHelper output;

        public TestBase(ITestOutputHelper output)
        {
            this.output = output;
        }

        private IWebDriver _driver;

        //http://d-docker.southeastasia.cloudapp.azure.com:5001/#/
        public string Url= "http://d-docker.southeastasia.cloudapp.azure.com:5001/#/FoodCategory";

        //是否是本地测试，使用本机的
        public bool localtest = false;

        public IWebDriver GetDriver(DriverOptions options)
        {
            //InternetExplorerOptions Options = new InternetExplorerOptions();
            if (!localtest)
            {
                if (_driver == null)
                {
                    _driver = new RemoteWebDriver(new Uri("http://localhost:4444/wd/hub/"), options.ToCapabilities(), TimeSpan.FromSeconds(600));
                }
            }
            else
            {
                if (_driver == null)
                {
                    _driver = new ChromeDriver();
                }
            }
            return _driver;
        }

        public void Wait()
        {
            Thread.Sleep(1000);
        }
    }
}
