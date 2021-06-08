using Grpc.Net.Client;
using NetworkingGrpc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using Grpc.Core;

namespace ClientGrpc
{
    static class Program
    {
        /// <summary>
        ///  The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.SetHighDpiMode(HighDpiMode.SystemAware);
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);

            var channel = new Channel("localhost", 55555, ChannelCredentials.Insecure);
            var client = new FestivalService.FestivalServiceClient(channel);
            // se trimite un request catre server pentru a se inregistra ca observer
            // serverul trimite ca raspuns un canal prin care va trimite datele necesare pentru observeri
            var reply = client.subscribe(new SubscribeRequest()).ResponseStream;
            
            LoginForm loginForm = new LoginForm(client, reply);
            Application.Run(loginForm);
            channel.ShutdownAsync().Wait();

        }
    }
}
