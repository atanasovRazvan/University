using lab10.entities;
using lab10.repository;
using lab10.service;
using lab10.validation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab10
{
    class Program
    {
        public static object RepositoryEchipa { get; private set; }

        static void Main(string[] args)
        {
            ValidatorEchipa validatorEchipa = new ValidatorEchipa();
            RepositoryEchipe repositoryEchipa = new RepositoryEchipe(validatorEchipa, @"C:\Users\Razvan\Desktop\lab10\lab10\fisiereTXT\echipe.txt");
            EchipaService echipaService = new EchipaService(repositoryEchipa);

            ValidatorElev validatorElev = new ValidatorElev();
            RepositoryElevi repositoryElevi = new RepositoryElevi(validatorElev, @"C:\Users\Razvan\Desktop\lab10\lab10\fisiereTXT\elevi.txt");
            EleviService eleviService = new EleviService(repositoryElevi);

            ValidatorJucator validatorJucator = new ValidatorJucator();
            RepositoryJucatori repositoryJucatori = new RepositoryJucatori(validatorJucator,repositoryElevi,repositoryEchipa, @"C:\Users\Razvan\Desktop\lab10\lab10\fisiereTXT\jucatori.txt");
            JucatoriService jucatoriService = new JucatoriService(repositoryJucatori);

            ValidatorMeci validatorMeci = new ValidatorMeci();
            RepositoryMeciuri repositoryMeciuri = new RepositoryMeciuri(validatorMeci, repositoryEchipa, @"C:\Users\Razvan\Desktop\lab10\lab10\fisiereTXT\meciuri.txt");
            MeciuriService meciuriService = new MeciuriService(repositoryMeciuri);

            ValidatorJucatorActiv validatorJucatorActiv = new ValidatorJucatorActiv();
            RepositoryJucatoriActivi repositoryJucatoriActivi = new RepositoryJucatoriActivi(validatorJucatorActiv, @"C:\Users\Razvan\Desktop\lab10\lab10\fisiereTXT\jucatoriActivi.txt");
            JucatoriActiviService jucatoriActiviService = new JucatoriActiviService(repositoryJucatoriActivi,repositoryJucatori);

            Console console = new Console(echipaService,eleviService,jucatoriService,meciuriService,jucatoriActiviService);
            console.run();
        }
    }
}
