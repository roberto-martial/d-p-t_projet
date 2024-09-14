using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KitchenRoom1.model
{
    public class Recipe
    {
        public string name { get; set; }
        public Ingredient[] ingredients { get; set; }
        public RecipeTask[] recipeTasks { get; set; }

        public Recipe(string name, Ingredient[] commodities, RecipeTask[] recipeTasks)
        {
            this.name = name;
            this.ingredients = ingredients;
            this.recipeTasks = recipeTasks;
        }
    }
}
