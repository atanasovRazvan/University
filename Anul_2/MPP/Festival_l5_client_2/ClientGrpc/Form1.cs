using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;


namespace ClientGrpc
{
    public partial class LoginForm : Form
    {
        private FestivalController controller;
        
        public LoginForm(FestivalController controller)
        {
            InitializeComponent();
            this.controller = controller;
        }
        
        private void button1_Click(object sender, EventArgs e)
        {
            string username = userText.Text;
            string pass = passText.Text;

            try
            {
                controller.Login(username, pass);
                MainForm mainForm = new MainForm(controller);
                mainForm.Text = "Gestionare bilete";
                mainForm.Width = 900;
                mainForm.Show();
                this.Hide();
            }catch(Exception ex)
            {
                MessageBox.Show(this, "Login Error " + ex.Message/*+ex.StackTrace*/, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
        }
    }
}
