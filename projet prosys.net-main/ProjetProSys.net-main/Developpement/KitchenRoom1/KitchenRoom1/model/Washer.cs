using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KitchenRoom1.model
{
    public class Washer : KitchenEmployee
    {
        public Washer()
        {
            x = 7;
            y = 4;
            SetSprite("moving-left", Image.FromFile("D:\\UCAC\\X3\\Programmation Concurrente\\Projet\\projet\\ProjetProSys.net\\Developpement\\KitchenRoom1\\KitchenRoom1\\assets\\employees\\washer\\moving-left.png"));
            SetSprite("moving-right", Image.FromFile("D:\\UCAC\\X3\\Programmation Concurrente\\Projet\\projet\\ProjetProSys.net\\Developpement\\KitchenRoom1\\KitchenRoom1\\assets\\employees\\washer\\moving-right.png"));
            currentSprite = GetSprite("moving-left");
            
        }
       
    }
}
