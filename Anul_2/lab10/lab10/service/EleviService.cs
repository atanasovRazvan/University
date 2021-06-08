using lab10.entities;
using lab10.repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab10.service
{
    class EleviService
    {
        RepositoryElevi rep;

        public EleviService(RepositoryElevi rep)
        {
            this.rep = rep;
        }

        public Elev FindOneService(int id)
        {
            return rep.FindOne(id);
        }

        public List<Elev> FindAllService()
        {
            return rep.FindAll();
        }

        public Elev SaveService(Elev e)
        {
            return rep.Save(e);
        }

        public Elev DeleteService(int id)
        {
            return rep.Delete(id);
        }

        public Elev UpdateService(Elev e)
        {
            return rep.Update(e);
        }
    }
}
