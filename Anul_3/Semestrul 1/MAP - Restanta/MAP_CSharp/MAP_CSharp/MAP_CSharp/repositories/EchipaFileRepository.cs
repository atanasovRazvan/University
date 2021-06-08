using System;
using MAP_CSharp.model;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MAP_CSharp.repositories
{
    public class EchipaFileRepository : FileRepository<string, Echipa>
    {
        public EchipaFileRepository(String fileName) : base(fileName)
        {
            ReadFromFile();
        }

        protected override Echipa ReadEntity(string line)
        {
            String[] attributes = line.Split(',');
            return new Echipa(attributes[0], attributes[1]);
        }

        protected override string WriteEntity(Echipa entity)
        {
            return entity.Id + "," + entity.Nume;
        }
    }
}
