using KitchenRoom1.model;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;

namespace KitchenRoom1Test
{
    [TestClass]
    public class KitchenMobileElementTest
    {
        public void MoveLeftTest()
        {
            Chef chef = new Chef();
            int PastX = chef.x;
    
            chef.MoveLeft();
            Assert.AreEqual<int>(chef.x, PastX - MobileKitchenElement.speed);
        }

        public void MoveRightTest()
        {
            Chef chef = new Chef();
            int PastX = chef.x;
    
            chef.MoveRight();
            Assert.AreEqual<int>(chef.x, PastX + MobileKitchenElement.speed);
        }
        public void MoveDownTest()
        {
            Chef chef = new Chef();
            int PastY = chef.y;
    
            chef.MoveDown();
            Assert.AreEqual<int>(chef.y, PastY + MobileKitchenElement.speed);
        }
        public void MoveUpTest()
        {
            Chef chef = new Chef();
            int PastY = chef.y;
    
            chef.MoveUp();
            Assert.AreEqual<int>(chef.y, PastY - MobileKitchenElement.speed);
        }

    }
}
