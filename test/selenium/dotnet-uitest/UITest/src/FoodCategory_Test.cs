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
    public class FoodCategory_Test : TestBase
    {
        private readonly ITestOutputHelper output;

        public FoodCategory_Test(ITestOutputHelper output) : base(output)
        {
            this.output = output;
        }

        #region Backend


        void Add()
        {
            
            string catName = "测试添加";
            string catDesc = "测试类型添加";
            Click(0);
            output.WriteLine($"点击添加");
            SendKey(1, catName);
            output.WriteLine($"输入{catName}");
            SendKey(2, catDesc);
            output.WriteLine($"输入{catDesc}");
            Click(3);
            output.WriteLine($"点击确定");
            var listCatName = GetText(4);
            var listCatDesc = GetText(5);
          
            output.WriteLine($"listCatName={listCatName}");
            output.WriteLine($"listCatDesc={listCatDesc}");

            Wait();
            Assert.Equal(catName, listCatName);
            Assert.Equal(catDesc, listCatDesc);
        }

        void Update()
        {
            string catName = "测试分类---2";
            string catDesc = "测试分类描述---2";

            Click(6);
            output.WriteLine($"点击编辑");
            SendKey(7, catName);
            output.WriteLine($"输入{catName}");
            SendKey(8, catDesc);
            output.WriteLine($"输入{catDesc}");
            Click(9);
            output.WriteLine($"点击保存");
            Wait();
            var listCatName = GetText(4);
            var listCatDesc = GetText(5);

            output.WriteLine($"listCatName={listCatName}");
            output.WriteLine($"listCatDesc={listCatDesc}");

            Wait(3000);
            Assert.Equal(catName, listCatName);
            Assert.Equal(catDesc, listCatDesc);

            //Assert.Equal(foodName, listFoodName);
            //Assert.Equal(foodDesc, listFoodDesc);
            //Assert.Equal(foodPrice, listFoodPrice);
        }

        void Delete()
        {
            //确保之前列中有一条数据
            if (ElementExist(4, out IWebElement element))
            {
                Click(10);
                //ElementExist(5, out IWebElement element2);
                //Assert.NotEqual(element, element2);
            }
            Wait();
        }

        [Fact]
        [TestPriority(1)]
        public void Backend_FoodCategory_Add_Test()
        {
            GoToUrl(Backend_Food_Category_Url, () =>
            {
                Add();
                Wait(2000);
                Update();
                Wait(2000);
                Delete();
                Wait(2000);
            });
        }

        [Fact]
        [TestPriority(2)]
        public void Backend_FoodCategory_Update_Test()
        {
            //GoToUrl(Backend_Food_Url, () =>
            //{

            //});
        }

        [Fact]
        [TestPriority(3)]
        public void Backend_FoodCategory_Delete_Test()
        {
            //GoToUrl(Backend_Food_Url, () =>
            //{



            //});
        }
        #endregion

        #region Frontend
        [Fact]
        public void Frontend_FoodCategory_Query_Test()
        {

        }


        #endregion
        public override string[] GetXPathArray()
        {
            return ConfigReader.GetFoodCategory_Xpath();
        }
    }




}
