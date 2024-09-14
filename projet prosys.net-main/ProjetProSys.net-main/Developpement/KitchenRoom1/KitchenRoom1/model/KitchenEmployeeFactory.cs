using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KitchenRoom1.model
{
    public class KitchenEmployeeFactory
    {
        private static Chef CHEF = new Chef();
        private static PartChef PART_CHEF = new PartChef();
        private static KitchenClerk KITCHEN_CLERK = new KitchenClerk();
        private static Washer WASHER = new Washer();

        public static Chef CreateChef()
        {
            return CHEF;
        }

        public static PartChef CreatePartChef()
        {
            return PART_CHEF;
        }

        public static Washer CreateWasher()
        {
            return WASHER;
        }

        public static KitchenClerk CreateKitchenClerk()
        {
            return KITCHEN_CLERK;
        }

    }
}
