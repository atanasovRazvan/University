using System;
using System.Windows.Forms;
using MusicFest;
using Networking;
using Services;


namespace MusicFestCSharpNetwork
{
    static class Program
    {
       [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            IServices server = new ServerProxy("127.0.0.1", 55555);
            Ticketing ticketing = new Ticketing(server);
            Application.Run(new Form1(ticketing));
        }
    }
}