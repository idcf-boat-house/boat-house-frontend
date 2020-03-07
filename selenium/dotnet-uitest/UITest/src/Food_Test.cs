using OpenQA.Selenium;
using System;
using OpenQA.Selenium.Chrome;
using Xunit;
using System.IO;
using System.Diagnostics;
using OpenQA.Selenium.Remote;
using System.Threading;
using Xunit.Abstractions;

namespace UITest
{

    public class Food_Test : TestBase
    {
        public Food_Test(ITestOutputHelper output):base(output)
        {

        }
       

       
        [Fact]
        public void Food_Test1()
        {
            // using (var driver = GetDriver(new ChromeOptions()))
            // {
            //     driver.Navigate().GoToUrl(Url);
            //     Wait();
            //     driver.FindElement(By.CssSelector(".btn-primary")).Click();
            //     Wait();
            //     driver.FindElement(By.Id("food-category-name")).Click();
            //     Wait();
            //     driver.FindElement(By.Id("food-category-name")).SendKeys("蔬菜");
            //     Wait();
            //     driver.FindElement(By.Id("food-category-description")).Click();
            //     Wait();
            //     driver.FindElement(By.Id("food-category-description")).SendKeys("蔬菜");
            //     Wait();
            //     //var text = driver.FindElement(By.Id("food-category-description")).GetProperty("value");
            //     driver.FindElement(By.CssSelector("#add-modal .btn-outline-primary")).Click();
            //     Wait();
            //     var ele = driver.FindElement(By.XPath("/html/body/div[2]/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div/div[3]/table/tbody/tr/td[2]/div"));
            //     var latsTitle = ele.Text;
            //     Assert.Equal("蔬菜", latsTitle);
            // }
        }
    }




}
