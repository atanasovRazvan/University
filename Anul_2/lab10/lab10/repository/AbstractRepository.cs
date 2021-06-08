using lab10.entities;
using lab10.validation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab10.repository
{
    abstract class AbstractRepository<ID,E>:IRepository<ID,E> where E:Entity<ID>
    {
        private AbstractValidator<ID, E> validator;
        private List<E> EList=new List<E>();
        public AbstractRepository(AbstractValidator<ID,E> validator)
        {
            this.validator = validator;
            //LoadFromFile();
        }
        public abstract void LoadFromFile();
        public abstract void WriteToFile();
        public void StoreFromFile(E entity)
        {
            if (entity == null)
                throw new ValidationException("Entitate vida");
            if (FindOne(entity.Id) == null)
            {
                validator.Validate(entity);
                EList.Add(entity);
            }
        }
        public E FindOne(ID id)
        {
            if (id == null)
            {
                throw new ValidationException("Id null");
            }
            var rez = EList.Where(e => e.Id.Equals(id));
            if (rez.Count() == 0)
                return null;
            return rez.First();
        }

        public List<E> FindAll()
        {
            return EList;
        }

        public E Save(E entity)
        {
            if (entity == null)
                throw new ValidationException("Entitate vida");
            if (FindOne(entity.Id) != null)
                return FindOne(entity.Id);
            validator.Validate(entity);
            EList.Add(entity);
            WriteToFile();
            return null;
        }

        public E Delete(ID id)
        {
            if (id == null)
            {
                throw new ValidationException("Id null");
            }
            E entity = FindOne(id);
            if (entity == null)
                return null;
            EList.Remove(entity);
            WriteToFile();
            return entity;
        }

        public E Update(E entity)
        {
            if (entity == null)
                throw new ValidationException("Entitate null");
            E e = FindOne(entity.Id);
            if (e == null)
                return entity;
            EList.Remove(e);
            EList.Add(entity);
            return null;
        }
    }
}
