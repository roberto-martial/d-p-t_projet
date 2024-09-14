using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Drawing;
using KitchenRoom1.model;



namespace KitchenRoom1Tests
{
    [TestClass]
    public class KitchenElementTest
    {
        [TestMethod]
        public void GetSpriteTest()
        {
            Chef chef = new();
            Image spriteTest = chef.GetSprite("waiting");

            Assert.IsNotNull(spriteTest);

        }
        public void SetSpriteTest()
        {
            Chef chef = new Chef();
            chef.SetSprite("ready", Image.FromFile("D:\\UCAC\\X3\\ProgrammationConcurrente\\Projet\\projet\\ProjetProSys.net\\Developpement\\KitchenRoom1\\KitchenRoom1\\assets\\employees\\chef\\ready.png");
            Assert.IsNotNull(chef.GetSprite("ready"));

        }
    }
}
