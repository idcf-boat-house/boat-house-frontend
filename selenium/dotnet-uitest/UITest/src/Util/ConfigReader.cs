using System;
using System.Collections.Generic;
using System.Text;
using System.IO;
using System.Linq;

namespace UITest.src.Util
{
    public class ConfigReader
    {
        public static string Food_Xpath =  "Food_Xpath.txt";
        public static string FoodCategory_Xpath = "FoodCategory_Xpath.txt";

        public static string[] ReadFile(string fileName)
        {
            if (!cache.ContainsKey(fileName))
            {
                var path = Path.Combine(Environment.CurrentDirectory, "src","Xpath", fileName);
                var arr= File.ReadAllLines(path).Where(line =>
                {
                    return line.Trim().Length > 0 && !line.StartsWith("--");

                }).ToArray();
                cache.Add(fileName, arr);
            }


            return cache[fileName];

        }

        private static Dictionary<string, string[]> cache = new Dictionary<string, string[]>();

        public static string[] GetFoodXpath()
        {
            return ReadFile(Food_Xpath);
        }

        public static string[] GetFoodCategory_Xpath()
        {
            return ReadFile(FoodCategory_Xpath);
        }
    }
}
