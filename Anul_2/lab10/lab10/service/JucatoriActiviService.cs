using lab10.entities;
using lab10.repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab10.service
{
    class JucatoriActiviService
    {
        RepositoryJucatoriActivi rep;
        RepositoryJucatori repJ;
        public JucatoriActiviService(RepositoryJucatoriActivi rep, RepositoryJucatori repj)
        {
            this.rep = rep;
            this.repJ = repj;
        }
        public JucatorActiv FindOneService(IdJucatorActiv id)
        {
            return rep.FindOne(id);
        }

        public List<JucatorActiv> FindAllService()
        {
            return rep.FindAll();
        }

        public JucatorActiv SaveService(JucatorActiv e)
        {
            return rep.Save(e);
        }

        public JucatorActiv DeleteService(IdJucatorActiv id)
        {
            return rep.Delete(id);
        }

        public JucatorActiv UpdateService(JucatorActiv e)
        {
            return rep.Update(e);
        }

        public List<JucatorActiv> jucatoriiActiviEchMeci(string idMeci, int idEchipa)
        {
            List<JucatorActiv> jucatori = FindAllService();
            var rez = jucatori.Where(s => (s.IdM.Equals(idMeci) && repJ.FindOne(s.IdJ).Echipa.Id.Equals(idEchipa)));
            return rez.ToList();
        }

        public string ScorMeci(string idm)
        {
            List<JucatorActiv> l = FindAllService();
            int s1 = 0, s2 = 0;
            var rez = l.Where(s => s.IdM.Equals(idm)).ToList();
            if (rez.Count() == 0)
            {
                s1 = -1;s2 = -1;
            }
            string[] i = idm.Split(' ');
            int idE1 = int.Parse(i[0]);
            int idE2 = int.Parse(i[1]);
            foreach(var j in rez)
            {
                if (repJ.FindOne(j.IdJ).Echipa.Id.Equals(idE1))
                {
                    s1 += j.NrPuncteInscrise;
                }
                else
                {
                    s2 += j.NrPuncteInscrise;
                }
            }
            return s1.ToString() + ' ' + s2.ToString();
        }
    }
}
