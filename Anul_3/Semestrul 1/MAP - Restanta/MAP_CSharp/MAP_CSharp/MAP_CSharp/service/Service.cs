using MAP_CSharp.model;
using MAP_CSharp.repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MAP_CSharp.service
{
    public class Service
    {
        private EchipaFileRepository EchipaFileRepository;
        private JucatorActivFileRepository JucatorActivFileRepository;
        private JucatorFileRepository JucatorFileRepository;
        private MeciFileRepository MeciFileRepository;
        public Service(EchipaFileRepository echipaFileRepository, JucatorActivFileRepository jucatorActivFileRepository, JucatorFileRepository jucatorFileRepository, MeciFileRepository meciFileRepository)
        {
            EchipaFileRepository = echipaFileRepository;
            JucatorActivFileRepository = jucatorActivFileRepository;
            JucatorFileRepository = jucatorFileRepository;
            MeciFileRepository = meciFileRepository;
        }

        public List<Jucator> arataJucatoriiEchipei(String idTeam)
        {
            return JucatorFileRepository.FindAll().Where(jucator => jucator.Echipa.Id.Equals(idTeam)).ToList();
        }

        public List<Meci> arataMeciuriDinPerioada(DateTime start, DateTime end)
        {
            return MeciFileRepository.FindAll().Where(g => g.Date.CompareTo(start) >= 0 && g.Date.CompareTo(end) <= 0).ToList();
        }

        public List<JucatorActiv> arataJucatoriiActiviDinMeci(String idMeci, List<String> jucatori)
        {
            return JucatorActivFileRepository.FindAll().Where(jucator => jucator.Id.Item2.Equals(idMeci) && jucatori.Contains(jucator.Id.Item1)).ToList();

        }

        public String afisareScor(String idMeci, List<String> jucatoriEchipa1, List<String> jucatoriEchipa2)
        {
            int scoreTeam1 = 0;
            int scoreTeam2 = 0;
            foreach (JucatorActiv jucator in JucatorActivFileRepository.FindAll().Where(_jucator => _jucator.Id.Item2.Equals(idMeci)).ToList())
            {
                if (jucatoriEchipa1.Contains(jucator.Id.Item1))
                {
                    scoreTeam1 += jucator.Goluri;
                }
                else scoreTeam2 += jucator.Goluri;
            }

            return scoreTeam1.ToString() + "-" + scoreTeam2.ToString();
        }

        public Meci gasesteMeci(String idMeci)
        {
            return MeciFileRepository.FindOne(idMeci);
        }
    }
}
