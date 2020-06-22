using OpenQA.Selenium;
using System;
using OpenQA.Selenium.Chrome;
using Xunit;
using System.IO;
using System.Diagnostics;
using OpenQA.Selenium.Remote;
using System.Threading;
using Xunit.Abstractions;
using UITest.src.Util;

namespace UITest
{
    [TestCaseOrderer("UITest.PriorityOrderer", "UITest")]
    public class Food_Test : TestBase
    {
        private readonly ITestOutputHelper output;

        public Food_Test(ITestOutputHelper output) : base(output)
        {
            this.output = output;
        }

        #region Backend


        void Add()
        {
            string foodName = "测试食品";
            string foodDesc = "食品描述";
            string foodPrice = "30";
            Click(0);
            output.WriteLine($"点击添加");
            SendKey(1, foodName);
            output.WriteLine($"输入{foodName}");
            SendKey(2, foodPrice);
            output.WriteLine($"输入{foodPrice}");
            SendKey(3, foodDesc);
            output.WriteLine($"输入{foodDesc}");
            SendKey(1, foodName+"123");
            Click(4);
            output.WriteLine($"点击确定");
            var listFoodName = GetText(5);
            var listFoodDesc = GetText(6);
            var listFoodPrice = GetText(7);
            output.WriteLine($"listFoodName={listFoodName}");
            output.WriteLine($"listFoodDesc={listFoodDesc}");
            output.WriteLine($"listFoodPrice={listFoodPrice}");
            Wait(3000);
            Assert.Equal(foodName, listFoodName);
            Assert.Equal(foodDesc, listFoodDesc);
            Assert.Equal(foodPrice, listFoodPrice);
        }

        void Update()
        {
            string foodName = "测试食品2";
            string foodDesc = "食品描述2";
            string foodPrice = "40";
            Click(8);
            output.WriteLine($"点击更新");
            SendKey(1, foodName);
            output.WriteLine($"foodName={foodName}");
            SendKey(2, foodPrice);
            output.WriteLine($"foodPrice={foodPrice}");
            SendKey(3, foodDesc);
            output.WriteLine($"foodDesc={foodDesc}");
            Click(4);
            output.WriteLine($"点击保存");
            Wait(3000);
            var listFoodName = GetText(5);
            var listFoodDesc = GetText(6);
            var listFoodPrice = GetText(7);
            output.WriteLine($"listFoodName={listFoodName}");
            output.WriteLine($"listFoodDesc={listFoodDesc}");
            output.WriteLine($"listFoodPrice={listFoodPrice}");

            //Assert.Equal(foodName, listFoodName);
            //Assert.Equal(foodDesc, listFoodDesc);
            //Assert.Equal(foodPrice, listFoodPrice);
        }

        void Delete()
        {
            //确保之前列中有一条数据
            if (ElementExist(5, out IWebElement element))
            {
                Click(9);
                Wait(2000);
                //ElementExist(5, out IWebElement element2);
                //Assert.NotEqual(element, element2);
            }
            Wait(10000);
        }

        [Fact]
        [TestPriority(1)]
        public void Backend_Food_Add_Test()
        {
            // GoToUrl(Backend_Food_Url, () =>
            // {
            //     Add();
            //     Wait(5000);
            //     //Update();
            //     //Wait(5000);
            //     Delete();
            //     Wait(5000);
            // });
        }

        [Fact]
        [TestPriority(2)]
        public void Backend_Food_Update_Test()
        {
            //GoToUrl(Backend_Food_Url, () =>
            //{

            //});
        }

        [Fact]
        [TestPriority(3)]
        public void Backend_Food_Delete_Test()
        {
            //GoToUrl(Backend_Food_Url, () =>
            //{



            //});
        }
        #endregion

        #region Frontend
        [Fact]
        public void Frontend_Food_Query_Test()
        {

        }


        #endregion
        public override string[] GetXPathArray()
        {
            return ConfigReader.GetFoodXpath();
        }
    }




}
