using System;
using KitchenRoom1.model;

public class KitchenMobileElementTest
{
	public  MoveLeftTest()
	{
		Chef chef = new Chef();
        int PastX = chef.x
		chef.MoveLeft();
		Assert.AreEqual<int>(chef.x,PastX - MobileKitchenElement.speed);
	}

    public MoveRightTest()
    {
        Chef chef = new Chef();
        int PastX = chef.x

        chef.MoveRight();
        Assert.AreEqual<int>(chef.x, PastX + MobileKitchenElement.speed);
    }
    public MoveDownTest()
    {
        Chef chef = new Chef();
        int PastY = chef.y

        chef.MoveDown();
        Assert.AreEqual<int>(chef.y, PastY + MobileKitchenElement.speed);
    }
    public MoveUpTest()
    {
        Chef chef = new Chef();
        int PastY = chef.y

        chef.MoveUp();
        Assert.AreEqual<int>(chef.y, PastY - MobileKitchenElement.speed);
    }

}
