using KitchenRoom1.model;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;

namespace KitchenRoom1Tests
{
    [TestClass]
    public class KitchenMaterialFactoryTest
    {
        public void CreateCookingFireTest()
        {
            KitchenMaterial cookingFire = KitchenMaterialFactory.CreateCookingFire();
            Assert.AreEqual<String>(cookingFire.name, "cooking fire");
    
        }

        public void CreatePanTest()
        {
            KitchenMaterial pan = KitchenMaterialFactory.CreatePan();
            Assert.AreEqual<String>(pan.name, "Pan");
    
        }

        public void CreateOvenTest()
        {
            KitchenMaterial oven = KitchenMaterialFactory.CreateOven();
            Assert.AreEqual<String>(oven.name, "oven");
    
        }

        public void CreateBlenderTest()
        {
            KitchenMaterial blender = KitchenMaterialFactory.CreateBlender();
            Assert.AreEqual<String>(blender.name, "blender");
    
        }
        public void CreateKitchenKnifeTest()
        {
            KitchenMaterial kitchenKnife = KitchenMaterialFactory.CreateKitchenKnife();
            Assert.AreEqual<String>(kitchenKnife.name, "kitchenKnife");
    
        }
    }
}
