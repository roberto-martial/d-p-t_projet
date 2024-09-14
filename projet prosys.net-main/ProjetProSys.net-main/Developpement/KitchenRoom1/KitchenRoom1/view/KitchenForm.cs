using KitchenRoom1.model;
using KitchenRoom1.view;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace KitchenRoom1
{
    public partial class KitchenForm : Form
    {
        public KitchenModel model { get; set; }

        public KitchenForm(KitchenModel model)
        {
            this.model = model;
            InitializeComponent();
        }

        private void KitchenForm_Load(object sender, EventArgs e)
        {
            int width = (model.kitchenRoom.map.GetUpperBound(0) - 1) * KitchenView.SQUARE_SIZE;
            int height = (model.kitchenRoom.map.GetUpperBound(1) - 1) * KitchenView.SQUARE_SIZE;
            Size = new Size(width, height);
        }


        private void KitchenForm_Paint(object sender, PaintEventArgs e)
        {
            Image tileImage = Image.FromFile("D:\\UCAC\\X3\\Programmation Concurrente\\Projet\\projet\\ProjetProSys.net\\Developpement\\KitchenRoom1\\KitchenRoom1\\assets\\tiles\\kitchen_tile.png");
            for (int i = 0; i < model.kitchenRoom.map.GetUpperBound(0); i++)
            {
                for (int j = 0; j < model.kitchenRoom.map.GetUpperBound(1); j++)
                {
                    e.Graphics.DrawImage(tileImage, i * KitchenView.SQUARE_SIZE, j * KitchenView.SQUARE_SIZE);
                }
            }

            Image decorator1 = Image.FromFile("D:\\UCAC\\X3\\Programmation Concurrente\\Projet\\projet\\ProjetProSys.net\\Developpement\\KitchenRoom1\\KitchenRoom1\\assets\\decorator\\decorator-1.png");
            Image decorator2 = Image.FromFile("D:\\UCAC\\X3\\Programmation Concurrente\\Projet\\projet\\ProjetProSys.net\\Developpement\\KitchenRoom1\\KitchenRoom1\\assets\\decorator\\decorator-2.png");
            Image decorator3 = Image.FromFile("D:\\UCAC\\X3\\Programmation Concurrente\\Projet\\projet\\ProjetProSys.net\\Developpement\\KitchenRoom1\\KitchenRoom1\\assets\\decorator\\decorator-3.png");

            e.Graphics.DrawImage(decorator1, 1 * KitchenView.SQUARE_SIZE, (int)(0.25 * KitchenView.SQUARE_SIZE));
            e.Graphics.DrawImage(decorator2, 1 * KitchenView.SQUARE_SIZE, (int)(2.9 * KitchenView.SQUARE_SIZE));
            e.Graphics.DrawImage(decorator3, 1 * KitchenView.SQUARE_SIZE, (int)(5.25 * KitchenView.SQUARE_SIZE));


            //for (int i = 0; i < model.kitchen.map.GetUpperBound(0); i++)
            //{
            //    for (int j = 0; j < model.kitchen.map.GetUpperBound(1); j++)
            //    {
            //        KitchenMaterial material = model.kitchen.map[i, j];

            //        if (material != null)
            //        {
            //            e.Graphics.DrawImage(material.sprite, i * KitchenView.SQUARE_SIZE, j * KitchenView.SQUARE_SIZE);
            //        }
            //    }
            //}

            foreach (Chef chef in model.chefs)
            {
                e.Graphics.DrawImage(chef.currentSprite, chef.x * KitchenView.SQUARE_SIZE, chef.y * KitchenView.SQUARE_SIZE);
            }

            foreach (PartChef partChef in model.partChefs)
            {
                e.Graphics.DrawImage(partChef.currentSprite, partChef.x * KitchenView.SQUARE_SIZE, partChef.y * KitchenView.SQUARE_SIZE);
            }

            foreach (KitchenClerk clerk in model.clerks)
            {
                e.Graphics.DrawImage(clerk.currentSprite, clerk.x * KitchenView.SQUARE_SIZE, clerk.y * KitchenView.SQUARE_SIZE);
            }


            foreach (Washer washer in model.washers)
            {
                e.Graphics.DrawImage(washer.currentSprite, washer.x * KitchenView.SQUARE_SIZE, washer.y * KitchenView.SQUARE_SIZE);
            }
        }

    }
}
