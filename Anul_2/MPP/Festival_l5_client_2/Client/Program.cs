using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using Grpc.Core;

namespace FestivalGUI
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);


            Channel channel = new Channel("localhost", 55555, ChannelCredentials.Insecure);
            
            //FestivalController controller = new FestivalController(server);
            //LoginForm loginForm = new LoginForm(controller);
            //Application.Run(loginForm);
        }
    }
}
