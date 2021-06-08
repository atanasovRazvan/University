using System;
using System.Collections.Generic;
using MAP_CSharp.model;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MAP_CSharp.repositories
{
    public abstract class AbstractRepository<ID, E> : ICrudRepository<ID, E> where E : Entitate<ID>
    {
        IDictionary<ID, E> elements;

        public AbstractRepository()
        {
            elements = new Dictionary<ID, E>();
        }
        public virtual E Delete(ID id)
        {
            if (elements.ContainsKey(id))
            {
                E toReturn = elements[id];
                elements.Remove(id);
                return toReturn;
            }
            return default(E);
        }

        public IEnumerable<E> FindAll()
        {
            return elements.Values;
        }

        public E FindOne(ID id)
        {
            if (id == null)
                throw new ArgumentNullException(nameof(id));
            if (elements.ContainsKey(id))
                return elements[id];
            return default(E);
        }

        public virtual E Save(E e)
        {
            if (e == null)
                throw new ArgumentNullException(nameof(e));
            if (elements.ContainsKey(e.Id))
            {
                return e;
            }
            elements.Add(e.Id, e);
            return default(E);
        }

        public int Size()
        {
            return elements.Count;
        }

        public virtual E Update(E entity)
        {
            if (entity == null)
                throw new ArgumentNullException(nameof(entity));
            if (elements.ContainsKey(entity.Id))
            {
                elements[entity.Id] = entity;
                return default(E);
            }
            return entity;

        }
    }

}
