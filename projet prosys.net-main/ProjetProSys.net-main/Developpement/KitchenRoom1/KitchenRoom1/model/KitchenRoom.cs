using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KitchenRoom1.model
{
    public class KitchenRoom
    {
        public int cookingFireNumber = 5;
        public int ovenNumber = 1;
        public KitchenMaterial[,] map { get; set; } 

        public KitchenRoom()
        {
            map = new KitchenMaterial[11, 10];

            for (int i = 0; i < cookingFireNumber; i++)
            {
                map[i, 0] = KitchenMaterialFactory.CreateCookingFire();
            }

            for (int i = 0; i < ovenNumber; i++)
            {
                map[i, 1] = KitchenMaterialFactory.CreateOven();
            }
        }
    }
}
