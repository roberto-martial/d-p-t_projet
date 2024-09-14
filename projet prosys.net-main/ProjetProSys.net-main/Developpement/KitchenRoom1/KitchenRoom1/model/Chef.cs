using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Security.Policy;
using System.Text;
using System.Threading.Tasks;

namespace KitchenRoom1.model
{
    public class Chef : KitchenEmployee
    {
        public Chef()
        {
            x = 0;
            y = 0;
            SetSprite("waiting", Image.FromFile("D:\\UCAC\\X3\\Programmation Concurrente\\Projet\\projet\\ProjetProSys.net\\Developpement\\KitchenRoom1\\KitchenRoom1\\assets\\employees\\chef\\waiting.png"));
            SetSprite("ready", Image.FromFile("D:\\UCAC\\X3\\Programmation Concurrente\\Projet\\projet\\ProjetProSys.net\\Developpement\\KitchenRoom1\\KitchenRoom1\\assets\\employees\\chef\\ready.png"));

            currentSprite = GetSprite("waiting");
         
        }

    }
}
