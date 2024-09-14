using KitchenRoom1.model;


namespace KitchenRoom1Tests
{

[TestClass]
public class KitchenElementTest
{
    [TestMethod]
    public void Image GetSpriteTest()
    {
        Chef chef = new Chef();
        Image spriteTest = chef.GetSpriteTest("waiting");
        Assert.IsNotNull(spriteTest);

    }
    public void Image SetSpriteTest()
    {
        Chef chef = new Chef();
        chef.SetSpriteTest("ready", Image.FromFile("D:\\UCAC\\X3\\ProgrammationConcurrente\\Projet\\projet\\ProjetProSys.net\\Developpement\\KitchenRoom1\\KitchenRoom1\\assets\\employees\\chef\\ready.png");
        Assert.IsNotNull(chef.SetSpriteTest("ready");

    }
}