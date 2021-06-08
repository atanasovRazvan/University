using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using Grpc.Core;
using NetworkingGrpc;

namespace ClientGrpc
{
    public partial class LoginForm : Form
    {
        private FestivalService.FestivalServiceClient server;
        private IAsyncStreamReader<ServerReply> serverReply;
        public LoginForm(FestivalService.FestivalServiceClient server, IAsyncStreamReader<ServerReply> reply)
        {
            InitializeComponent();
            this.server = server;
            this.serverReply = reply;
        }

        private void loginButton_Click(object sender, EventArgs e)
        {
            NetworkingGrpc.User userProto = new NetworkingGrpc.User { Username = userText.Text, Password = passText.Text };
            LoginRequest req = new LoginRequest();
            req.User = userProto;
            var reply = server.login(req);
            
            if (reply.Logged == true)
            {
                Form2 mainForm = new Form2(server, serverReply);
                mainForm.Show();
                userText.Text = "";
                passText.Text = "";
                //this.Close();
                this.Hide();
            }
            else
            {
                MessageBox.Show("Username/parola invalide!");
            }
        }
    }
}
