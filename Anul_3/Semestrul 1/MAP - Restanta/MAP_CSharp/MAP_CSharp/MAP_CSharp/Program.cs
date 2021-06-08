using MAP_CSharp.repositories;
using MAP_CSharp.service;
using MAP_CSharp.userinterface;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MAP_CSharp
{
    class Program
    {
        static void Main(string[] args)
        {
            String fisierEchipe = "C:/Users/Razvan/Desktop/Facultate/Anul_3/MAP - Restanta/MAP_CSharp/MAP_CSharp/MAP_CSharp/data/echipe.txt";
            String fisierJucatori = "C:/Users/Razvan/Desktop/Facultate/Anul_3/MAP - Restanta/MAP_CSharp/MAP_CSharp/MAP_CSharp/data/jucatori.txt";
            String fisierMeciuri = "C:/Users/Razvan/Desktop/Facultate/Anul_3/MAP - Restanta/MAP_CSharp/MAP_CSharp/MAP_CSharp/data/meciuri.txt";
            String fisierJucatoriActivi = "C:/Users/Razvan/Desktop/Facultate/Anul_3/MAP - Restanta/MAP_CSharp/MAP_CSharp/MAP_CSharp/data/jucatoriActivi.txt";

            EchipaFileRepository echipaFileRepository = new EchipaFileRepository(fisierEchipe);
            JucatorFileRepository jucatorFileRepository = new JucatorFileRepository(echipaFileRepository, fisierJucatori);
            MeciFileRepository meciFileRepository = new MeciFileRepository(fisierMeciuri, echipaFileRepository);
            JucatorActivFileRepository jucatorActivFileRepository = new JucatorActivFileRepository(fisierJucatoriActivi);

            Service service = new Service(echipaFileRepository, jucatorActivFileRepository, jucatorFileRepository, meciFileRepository);

            UI userInterface = new UI(service);
            userInterface.start();
        }
    }
}
