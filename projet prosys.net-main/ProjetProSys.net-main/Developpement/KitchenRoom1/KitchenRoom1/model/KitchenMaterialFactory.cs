using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KitchenRoom1.model
{
    public class KitchenMaterialFactory
    {
        private static KitchenMaterial COOKING_FIRE = new KitchenMaterial("cooking fire", 5, Image.FromFile("D:\\UCAC\\X3\\Programmation Concurrente\\Projet\\projet\\ProjetProSys.net\\Developpement\\KitchenRoom1\\KitchenRoom1\\assets\\kitchen-material\\cooking_fire.png"));
        private static readonly KitchenMaterial PAN = new KitchenMaterial("pan", 10, Image.FromFile("D:\\UCAC\\X3\\Programmation Concurrente\\Projet\\projet\\ProjetProSys.net\\Developpement\\KitchenRoom1\\KitchenRoom1\\assets\\kitchen-material\\cooking_fire.png"));
        private static KitchenMaterial OVEN = new KitchenMaterial("oven", 1, Image.FromFile("D:\\UCAC\\X3\\Programmation Concurrente\\Projet\\projet\\ProjetProSys.net\\Developpement\\KitchenRoom1\\KitchenRoom1\\assets\\kitchen-material\\oven.png"));
        private static readonly KitchenMaterial BLENDER = new KitchenMaterial("blender", 1, Image.FromFile("D:\\UCAC\\X3\\Programmation Concurrente\\Projet\\projet\\ProjetProSys.net\\Developpement\\KitchenRoom1\\KitchenRoom1\\assets\\kitchen-material\\cooking_fire.png"));
        private static readonly KitchenMaterial KITCHEN_KNIFE = new KitchenMaterial("kitchen knife", 5, Image.FromFile("D:\\UCAC\\X3\\Programmation Concurrente\\Projet\\projet\\ProjetProSys.net\\Developpement\\KitchenRoom1\\KitchenRoom1\\assets\\kitchen-material\\cooking_fire.png"));


        public static KitchenMaterial CreateCookingFire()
        {
            COOKING_FIRE.washable = false;
            return COOKING_FIRE;
        }
        public static KitchenMaterial CreatePan()
        {
            return PAN;
        }
        public static KitchenMaterial CreateOven()
        {
            OVEN.washable = false;
            return OVEN;
        }
        public static KitchenMaterial CreateBlender()
        {
            return BLENDER;
        }
        public static KitchenMaterial CreateKitchenKnife()
        {
            return KITCHEN_KNIFE;
        }
    }
}
