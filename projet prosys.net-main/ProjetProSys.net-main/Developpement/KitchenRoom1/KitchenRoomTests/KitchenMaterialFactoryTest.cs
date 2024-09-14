using System;
using KitchenRoom1.model;

public class KitchenMaterialFactoryTest
{
	public void CreateCookingFireTest()
	{
		KitchenMaterial cookingFire = KitchenMaterialFactoryTest.CreateCookingFire();
		Assert.AreEqual<String>(cookingFire.name, "cooking fire")
	}

    public void CreatePanTest()
    {
        KitchenMaterial pan = KitchenMaterialFactoryTest.CreatePan();
        Assert.AreEqual<String>(pan.name, "Pan")

    }

    public void CreateOvenTest()
    {
        KitchenMaterial oven = KitchenMaterialFactoryTest.Create.CreateOven();
        Assert.AreEqual<String>(oven.name, "oven")

    }

    public void CreateBlenderTest()
    {
        KitchenMaterial blender = KitchenMaterialFactoryTest.Create.CreateBlender();
        Assert.AreEqual<String>(blender.name, "blender")

    }
    public void CreateKitchenKnifeTest()
    {
        KitchenMaterial kitchenKnife = KitchenMaterialFactoryTest.Create.CreateKitchenKnife();
        Assert.AreEqual<String>(kitchenKnife.name, "kitchenKnife")

    }
}
