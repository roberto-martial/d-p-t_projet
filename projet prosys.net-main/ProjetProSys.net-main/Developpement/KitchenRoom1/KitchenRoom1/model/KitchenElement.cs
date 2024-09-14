using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KitchenRoom1.model
{
    public abstract class KitchenElement
    {
        private Dictionary<String, Image> sprites;
        public Image currentSprite { get; set; }

        public KitchenElement()
        {
            sprites = new Dictionary<String, Image>();
        }

        public Image GetSprite(String key)
        {
            return sprites[key];
        }

        public void SetSprite(String key, Image sprite)
        {
            sprites[key] = sprite;
        }
    }
}
