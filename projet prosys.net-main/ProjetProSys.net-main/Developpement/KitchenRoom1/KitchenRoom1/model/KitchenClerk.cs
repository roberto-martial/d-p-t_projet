using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Security.Policy;
using System.Text;
using System.Threading.Tasks;

namespace KitchenRoom1.model
{
    public class KitchenClerk : KitchenEmployee
    {
        public KitchenClerk()
        {
            x = 50;
            y = 80;

            SetSprite("moving-up", Image.FromFile("D:\\UCAC\\X3\\Programmation Concurrente\\Projet\\projet\\ProjetProSys.net\\Developpement\\KitchenRoom1\\KitchenRoom1\\assets\\employees\\kitchen-clerk\\moving-up.png"));
            SetSprite("moving-down", Image.FromFile("D:\\UCAC\\X3\\Programmation Concurrente\\Projet\\projet\\ProjetProSys.net\\Developpement\\KitchenRoom1\\KitchenRoom1\\assets\\employees\\kitchen-clerk\\moving-down.png"));
            SetSprite("moving-left", Image.FromFile("D:\\UCAC\\X3\\Programmation Concurrente\\Projet\\projet\\ProjetProSys.net\\Developpement\\KitchenRoom1\\KitchenRoom1\\assets\\employees\\kitchen-clerk\\moving-left.png"));
            SetSprite("moving-right", Image.FromFile("D:\\UCAC\\X3\\Programmation Concurrente\\Projet\\projet\\ProjetProSys.net\\Developpement\\KitchenRoom1\\KitchenRoom1\\assets\\employees\\kitchen-clerk\\moving-right.png"));
            currentSprite = GetSprite("moving-down");
           
        }
        
    }
}
