using System;
using MAP_CSharp.model;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MAP_CSharp.repositories
{
    public interface ICrudRepository<ID, E> where E : Entitate<ID>
    {
        int Size();
        E FindOne(ID id);
        IEnumerable<E> FindAll();
        E Save(E e);
        E Delete(ID id);
        E Update(E entity);


    }
}
