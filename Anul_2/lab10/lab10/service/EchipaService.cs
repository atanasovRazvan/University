using lab10.entities;
using lab10.repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab10.service
{
    class EchipaService
    {
        RepositoryEchipe rep;
        public EchipaService(RepositoryEchipe rep)
        {
            this.rep = rep;
        }

        public Echipa FindOneService(int id)
        {
            return rep.FindOne(id);
        }

        public List<entities.Echipa> FindAllService()
        {
            return rep.FindAll();
        }

        public Echipa SaveService(entities.Echipa e)
        {
            return rep.Save(e);
        }

        public Echipa DeleteService(int id)
        {
            return rep.Delete(id);
        }

        public Echipa UpdateService(entities.Echipa e)
        {
            return rep.Update(e);
        }
    }
}
