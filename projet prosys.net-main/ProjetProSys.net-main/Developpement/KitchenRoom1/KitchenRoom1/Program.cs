using KitchenRoom1.controller;
using KitchenRoom1.model;
using KitchenRoom1.view;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace KitchenRoom1
{
    internal static class Program
    {
        /// <summary>
        /// Point d'entrée principal de l'application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            //Application.Run(new KitchenForm());
            //Application.Initialize();

            KitchenModel model = new KitchenModel();
            KitchenView view = new KitchenView(model);
            KitchenController controller = new KitchenController(model, view);

            controller.Start();
        }
    }
}
