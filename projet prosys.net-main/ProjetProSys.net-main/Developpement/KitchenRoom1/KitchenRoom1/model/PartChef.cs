using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KitchenRoom1.model
{
    public class PartChef : KitchenEmployee
    {
        public PartChef()
        {
            SetSprite("moving-up", Image.FromFile("D:\\UCAC\\X3\\Programmation Concurrente\\Projet\\projet\\ProjetProSys.net\\Developpement\\KitchenRoom1\\KitchenRoom1\\assets\\employees\\part-chef\\moving-up.png"));
            SetSprite("moving-down", Image.FromFile("D:\\UCAC\\X3\\Programmation Concurrente\\Projet\\projet\\ProjetProSys.net\\Developpement\\KitchenRoom1\\KitchenRoom1\\assets\\employees\\part-chef\\moving-down.png"));
            SetSprite("moving-left", Image.FromFile("D:\\UCAC\\X3\\Programmation Concurrente\\Projet\\projet\\ProjetProSys.net\\Developpement\\KitchenRoom1\\KitchenRoom1\\assets\\employees\\part-chef\\moving-left.png"));
            SetSprite("moving-right", Image.FromFile("D:\\UCAC\\X3\\Programmation Concurrente\\Projet\\projet\\ProjetProSys.net\\Developpement\\KitchenRoom1\\KitchenRoom1\\assets\\employees\\part-chef\\moving-right.png"));
            SetSprite("working", Image.FromFile("D:\\UCAC\\X3\\Programmation Concurrente\\Projet\\projet\\ProjetProSys.net\\Developpement\\KitchenRoom1\\KitchenRoom1\\assets\\employees\\part-chef\\working.png"));

            currentSprite = GetSprite("moving-up");
            
        }

        
    }
}
