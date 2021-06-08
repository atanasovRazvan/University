using lab10.entities;
using lab10.service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab10
{
    class Console
    {
        private EchipaService echipaService;
        private EleviService eleviService;
        private JucatoriService jucatoriService;
        private MeciuriService meciuriService;
        private JucatoriActiviService jucatoriActiviService;

        public Console(EchipaService echipaService, EleviService eleviService,JucatoriService jucatoriService,MeciuriService meciuriService,JucatoriActiviService jucatoriActiviService)
        {
            this.echipaService = echipaService;
            this.eleviService = eleviService;
            this.jucatoriService = jucatoriService;
            this.meciuriService = meciuriService;
            this.jucatoriActiviService = jucatoriActiviService;
        }

        private void Case1(int id)
        {
            List<Jucator> l = jucatoriService.JucatoriiUneiEchipe(id);
            if (l.Count() == 0)
            {
                System.Console.WriteLine("Nu exista jucatori in echipa cu id-ul dat");
            }
            foreach(var j in l)
            {
                System.Console.WriteLine(j);
            }
        }

        private void Case2(string idm,int ide)
        {
            List<JucatorActiv> l = jucatoriActiviService.jucatoriiActiviEchMeci(idm, ide);
            if (l.Count()==0)
            {
                System.Console.WriteLine("Meciul cu id-ul dat nu exista");
            }
            foreach(var j in l)
            {
                System.Console.WriteLine(j);
            }
        }

        private void Case3(DateTime d1,DateTime d2)
        {
            List<Meci> l = meciuriService.MeciuriPerioada(d1, d2);
            if (l.Count() == 0)
            {
                System.Console.WriteLine("Nu exista niciun meci in perioada data");
            }
            foreach(var m in l)
            {
                System.Console.WriteLine(m);
            }
        }

        private void Case4(string idm)
        {
            string[] e = idm.Split(' ');
            string text = jucatoriActiviService.ScorMeci(idm);
            string[] s = text.Split(' ');
            if (s[0].Equals("-1") && s[1].Equals("-1"))
            {
                System.Console.WriteLine("Nu exista meciul cu id-ul dat");
            }
            else
                System.Console.WriteLine(echipaService.FindOneService(int.Parse(e[0])).Nume.ToString()+":"+s[0]+"--"+ echipaService.FindOneService(int.Parse(e[1])).Nume.ToString() + ":" + s[1]);
        }

        public void run()
        {
            while (true)
            {
                System.Console.WriteLine("Lista de comenzi:");
                System.Console.WriteLine("0-EXIT");
                System.Console.WriteLine("1-toti jucatorii unei anumite echipe");
                System.Console.WriteLine("2-toti jucatorii activi ai unei echipe de la un anumit meci");
                System.Console.WriteLine("3-toate meciurile dintr-o anumita perioada calendaristica");
                System.Console.WriteLine("4-scorul de la un anumit meci");
                System.Console.WriteLine("Alegeti comanda: ");
                try
                {
                    int cmd = int.Parse(System.Console.ReadLine());
                    switch (cmd)
                    {
                        case 0:
                            return;
                        case 1:
                            System.Console.WriteLine("Alegeti id-ul echipei:");
                            foreach (var e in echipaService.FindAllService())
                            {
                                System.Console.WriteLine(e);
                            }
                            try
                            {
                                int id = int.Parse(System.Console.ReadLine());
                                Case1(id);
                            }
                            catch (Exception ex)
                            {
                                System.Console.WriteLine("Id invalid");
                            }
                            break;
                        case 2:
                            foreach (var m in meciuriService.FindAllService())
                            {
                                System.Console.WriteLine(m);
                            }
                            System.Console.WriteLine("Alegeti id ul meciului");
                            try
                            {
                                string idm = System.Console.ReadLine();
                                System.Console.WriteLine("Alegeti echipa (echipa 1/echipa 2)");
                                try
                                {
                                    int ide = int.Parse(System.Console.ReadLine());
                                    Case2(idm, ide);
                                }
                                catch (Exception ex)
                                {
                                    System.Console.WriteLine("Invalid. Trebuie selectata tasta 1 sau tasta 2");
                                }
                            }
                            catch (Exception ex)
                            {
                                System.Console.WriteLine("Id invalid");
                            }
                            break;
                        case 3:
                            System.Console.WriteLine("Introduceti prima data calendaristica (MM.dd.yyyy)");
                            try
                            {
                                DateTime d1 = DateTime.Parse(System.Console.ReadLine());
                                System.Console.WriteLine("Introduceti a doua data calendaristica (MM.dd.yyyy)");
                                try
                                {
                                    DateTime d2 = DateTime.Parse(System.Console.ReadLine());
                                    Case3(d1, d2);
                                }
                                catch (Exception ex)
                                {
                                    System.Console.WriteLine("Format data invalid");
                                }
                            }
                            catch (Exception ex)
                            {
                                System.Console.WriteLine("Format data invalid");
                            }
                            break;
                        case 4:
                            foreach (var m in meciuriService.FindAllService())
                            {
                                System.Console.WriteLine(m);
                            }
                            System.Console.WriteLine("Alegeti id ul meciului (id_echipa1 id_echipa2)");
                            try
                            {
                                string idm = System.Console.ReadLine();
                                Case4(idm);
                            }
                            catch (Exception ex)
                            {
                                System.Console.WriteLine("Id invalid");
                            }
                            break;
                        default:
                            System.Console.WriteLine("comanda invalida");
                            break;
                    }
                }
                catch (Exception ex)
                {
                    System.Console.WriteLine("Comanda invalida");
                }

            }
        }
    }
}
