using lab10.entities;
using lab10.repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab10.service
{
    class JucatoriService
    {
        RepositoryJucatori rep;
        public JucatoriService(RepositoryJucatori rep)
        {
            this.rep = rep;
        }
        public Jucator FindOneService(int id)
        {
            return rep.FindOne(id);
        }

        public List<Jucator> FindAllService()
        {
            return rep.FindAll();
        }

        public Jucator SaveService(Jucator e)
        {
            return rep.Save(e);
        }

        public Jucator DeleteService(int id)
        {
            return rep.Delete(id);
        }

        public Jucator UpdateService(Jucator e)
        {
            return rep.Update(e);
        }

        public List<Jucator> JucatoriiUneiEchipe(int idEchipa)
        {
            List<Jucator> jucatori = FindAllService();
            var rez = jucatori.Where(s => s.Echipa.Id.Equals(idEchipa));
            return rez.ToList();
        }
    }
}
