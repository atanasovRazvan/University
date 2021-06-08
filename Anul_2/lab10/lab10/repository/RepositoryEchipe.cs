using lab10.entities;
using lab10.validation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab10.repository
{
    class RepositoryEchipe : AbstractRepository<int, Echipa>
    {
        private string fileName;
        public RepositoryEchipe(ValidatorEchipa validator,string fileName):base(validator)
        {
            this.fileName = fileName;
            LoadFromFile();
        }
        public override void LoadFromFile()
        {
            string[] lines = System.IO.File.ReadAllLines(fileName);
            foreach(string line in lines)
            {
                string[] date = line.Split(',');
                int id = Int16.Parse(date[0]);
                Echipa.NumeTD nume = (Echipa.NumeTD)Enum.Parse(typeof(Echipa.NumeTD), date[1]);
                Echipa e = new Echipa(id, nume);
                StoreFromFile(e);
            }
        }

        public override void WriteToFile()
        {
            List<Echipa> l = FindAll();
            List<String> lines=new List<String>();
            foreach(Echipa e in l)
            {
                string id = e.Id.ToString();
                string nume = e.Nume.ToString();
                string line = id + ',' + nume;
                lines.Add(line);
            }
            System.IO.File.WriteAllLines(fileName,lines);
        }
    }
}
