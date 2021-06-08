using lab10.entities;
using lab10.validation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab10.repository
{
    interface IRepository<ID,E> where E:Entity<ID>
    {
        E FindOne(ID id);
        List<E> FindAll();
        E Save(E entity);
        E Delete(ID id);
        E Update(E entity);
    }
}
