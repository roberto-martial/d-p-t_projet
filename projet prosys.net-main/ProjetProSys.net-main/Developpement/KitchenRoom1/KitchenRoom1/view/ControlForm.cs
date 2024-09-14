using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace KitchenRoom1.view
{
    public partial class ControlForm : Form
    {
        public KitchenForm kitchenForm { get; set; }
        public ControlForm(KitchenForm kitchenForm)
        {
            InitializeComponent();
            this.kitchenForm = kitchenForm;
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        private void label4_Click(object sender, EventArgs e)
        {

        }

        private void numericUpDown3_ValueChanged(object sender, EventArgs e)
        {

        }

        private void label5_Click(object sender, EventArgs e)
        {

        }

        private void numericUpDown4_ValueChanged(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            
            
             int chefNumber = Convert.ToInt32(Math.Round(numericUpDown1.Value, 0)) ;
             int partchefNumber = Convert.ToInt32(Math.Round(numericUpDown2.Value, 0)) ;
             int clerkNumber = Convert.ToInt32(Math.Round(numericUpDown3.Value, 0)) ;
             int washerNumber = Convert.ToInt32(Math.Round(numericUpDown4.Value, 0)) ;

            kitchenForm.model.SetEmployeeConfig(chefNumber, partchefNumber, clerkNumber, washerNumber);
            this.Close();
        }

        private void ControlForm_Load(object sender, EventArgs e)
        {

        }

        private void numericUpDown1_ValueChanged(object sender, EventArgs e)
        {

        }

        private void numericUpDown2_ValueChanged(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }
    }
    
}
