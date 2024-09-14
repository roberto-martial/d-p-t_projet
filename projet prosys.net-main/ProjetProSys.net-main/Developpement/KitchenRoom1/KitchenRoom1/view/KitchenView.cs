using KitchenRoom1.contract;
using KitchenRoom1.model;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace KitchenRoom1.view
{
    public class KitchenView : IObserver
    {
        private static int FPS = 25;
        private static int FRAME_RATE = 1000 / FPS;
        public static int SQUARE_SIZE = 64;
        public ControlForm controlForm { get; set; }
        public KitchenForm kitchenForm { get; set; }

        public KitchenView(KitchenModel model)
        {
            kitchenForm = new KitchenForm(model);
            controlForm = new ControlForm(kitchenForm);
        }

        void IObserver.UpdateHasMoved(int pastX, int pastY, int newX, int newY)
        {
            kitchenForm.Invoke((MethodInvoker)delegate
            {
                kitchenForm.Invalidate(new Rectangle(pastX * SQUARE_SIZE, pastY * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE));
                kitchenForm.Invalidate(new Rectangle(newX * SQUARE_SIZE, newY * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE));
                kitchenForm.Update();
            });

            Thread.Sleep(40);
        }
    }
}
