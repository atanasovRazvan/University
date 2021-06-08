using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MusicFest
{
    public partial class Form1 : Form
    {
        private Ticketing ticketing;
        
        public Form1(Ticketing ticketing)
        {
            InitializeComponent();
            this.ticketing = ticketing;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if(usernameTextBox.Text=="" || passwordTextBox.Text=="")
            {
                MessageBox.Show("Nu ati introdus username-ul si/sau parola");
                return;
            }
            try
            {
                ticketing.login(usernameTextBox.Text, passwordTextBox.Text);
                ticketing.Text = "Ticketing window for " + usernameTextBox.Text;
                ticketing.Show();
                ticketing.initData(ticketing.getAllShows());
                this.Hide();
                
            }catch(Exception ex)
            {
                usernameTextBox.Clear();
                passwordTextBox.Clear();
                MessageBox.Show(this, "Login Error " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
        }
    }
}