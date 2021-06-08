using MAP_CSharp.model;
using MAP_CSharp.service;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MAP_CSharp.userinterface
{
    public class UI
    {
        private Service Service;

        public delegate List<Jucator> Del(String toPass);

        public UI(Service service)
        {
            Service = service;
        }

        public void showMainMenu()
        {
            Console.WriteLine("0.Exit");
            Console.WriteLine("1.Show players of given team");
            Console.WriteLine("2.Show active players of team in a certain game");
            Console.WriteLine("3.Show all games in time period");
            Console.WriteLine("4.Show game score");
        }

        public void start()
        {
            bool show = true;
            while (show)
            {
                showMainMenu();
                Console.WriteLine("Enter a command:");
                string command = Console.ReadLine();
                try
                {
                    switch (command)
                    {
                        case "0":
                            show = false;
                            break;
                        case "1":
                            Console.WriteLine("Enter a team id:");
                            String idEchipa = Console.ReadLine();
                            Del arataJucatoriiEchipei = Service.arataJucatoriiEchipei;
                            foreach (Jucator jucator in arataJucatoriiEchipei(idEchipa))
                            {
                                Console.WriteLine(jucator);
                            }
                            break;
                        case "2":
                            Console.WriteLine("Enter a team id:");
                            String _idEchipa = Console.ReadLine();
                            Console.WriteLine("Enter a game id:");
                            String idMeci = Console.ReadLine();
                            foreach (JucatorActiv activePlayer in Service.arataJucatoriiActiviDinMeci(idMeci, Service.arataJucatoriiEchipei(_idEchipa).Select(jucator => jucator.Id).ToList()))
                            {
                                Console.WriteLine(activePlayer);
                            }
                            break;
                        case "3":
                            Console.WriteLine("Enter start period (format dd/mm/yyyy):");
                            String start = Console.ReadLine();
                            Console.WriteLine("Enter end period (format dd/mm/yyyy):");
                            String end = Console.ReadLine();
                            foreach (Meci game in Service.arataMeciuriDinPerioada(DateTime.ParseExact(start, "dd/MM/yyyy", CultureInfo.InvariantCulture), DateTime.ParseExact(end, "dd/MM/yyyy", CultureInfo.InvariantCulture)))
                            {
                                Console.WriteLine(game);
                            }
                            break;
                        case "4":
                            Console.WriteLine("Enter game id:");
                            String _idMeci = Console.ReadLine();
                            Console.WriteLine("The game score for " + Service.gasesteMeci(_idMeci) + " is " + Service.afisareScor(_idMeci, Service.arataJucatoriiEchipei(Service.gasesteMeci(_idMeci).FirstTeam.Id).Select(jucator => jucator.Id).ToList(), Service.arataJucatoriiEchipei(Service.gasesteMeci(_idMeci).SecondTeam.Id).Select(jucator => jucator.Id).ToList()));
                            break;
                        default:
                            Console.WriteLine("The command you entered does not exist");
                            break;
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine(e);
                }
            }
        }
    }
}
