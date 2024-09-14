using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KitchenRoom1.model
{
    public class Ingredient : Entity
    {
        public String name { get; set; }

        public Ingredient(String name)
        {
            this.name = name;
        }
    }
}
