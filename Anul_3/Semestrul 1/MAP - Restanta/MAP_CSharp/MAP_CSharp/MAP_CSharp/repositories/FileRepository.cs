using System;
using MAP_CSharp.model;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MAP_CSharp.repositories
{
    public abstract class FileRepository<ID, E> : AbstractRepository<ID, E> where E : Entitate<ID>
    {
        private string fileName;

        public FileRepository(string fileName) : base()
        {
            this.fileName = fileName;
        }

        protected abstract E ReadEntity(string line);
        protected abstract string WriteEntity(E entity);

        public void ReadFromFile()
        {
            if (System.IO.File.Exists(fileName))
            {
                System.IO.StreamReader file = new System.IO.StreamReader(fileName);
                while (!file.EndOfStream)
                {
                    E entity = ReadEntity(file.ReadLine());
                    base.Save(entity);
                }
                file.Close();
            }
        }

        public void WriteToFile()
        {
            using (System.IO.StreamWriter file = new System.IO.StreamWriter(fileName))
            {
                foreach (E entity in base.FindAll())
                    file.WriteLine(WriteEntity(entity));
            }
        }

        public override E Delete(ID id)
        {
            E toReturn = base.Delete(id);
            WriteToFile();
            return toReturn;
        }

        public override E Save(E e)
        {
            E toReturn = base.Save(e);
            WriteToFile();
            return toReturn;
        }

        public override E Update(E entity)
        {
            E toReturn = base.Update(entity);
            WriteToFile();
            return toReturn;
        }
    }
}
