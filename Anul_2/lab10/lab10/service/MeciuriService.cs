using lab10.entities;
using lab10.repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab10.service
{
    class MeciuriService
    {
        RepositoryMeciuri rep;
        public MeciuriService(RepositoryMeciuri rep)
        {
            this.rep = rep;
        }
        public Meci FindOneService(string id)
        {
            return rep.FindOne(id);
        }

        public List<Meci> FindAllService()
        {
            return rep.FindAll();
        }

        public Meci SaveService(Meci e)
        {
            return rep.Save(e);
        }

        public Meci DeleteService(string id)
        {
            return rep.Delete(id);
        }

        public Meci UpdateService(Meci e)
        {
            return rep.Update(e);
        }

        public List<Meci> MeciuriPerioada(DateTime data1,DateTime data2)
        {
            if (data1 > data2)
            {
                var aux = data1;
                data1 = data2;
                data2 = aux;
            }
            List<Meci> meciuri = FindAllService();
            var rez=meciuri.Where(s=>(s.Data>data1&&s.Data<data2));
            return rez.ToList();
        }
    }
}
