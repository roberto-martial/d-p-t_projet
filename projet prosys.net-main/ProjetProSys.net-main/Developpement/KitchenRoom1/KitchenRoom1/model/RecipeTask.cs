using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KitchenRoom1.model
{
    public class RecipeTask
    {
        public string name { get; set; }
        public int duration { get; set; }
        public KitchenMaterial material { get; set; }

        public RecipeTask(string name, int duration, KitchenMaterial material)
        {
            this.name = name;
            this.duration = duration;
            this.material = material;
        }
    }
}
