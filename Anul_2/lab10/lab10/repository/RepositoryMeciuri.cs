using lab10.entities;
using lab10.validation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab10.repository
{
    class RepositoryMeciuri : AbstractRepository<string, Meci>
    {
        private string fileName;
        private RepositoryEchipe RepositoryEchipe;
        public RepositoryMeciuri(ValidatorMeci validator,RepositoryEchipe repositoryEchipe,string fn):base(validator)
        {
            fileName = fn;
            this.RepositoryEchipe = repositoryEchipe;
            LoadFromFile();
        }
        public override void LoadFromFile()
        {
            string[] lines = System.IO.File.ReadAllLines(fileName);
            foreach (string line in lines)
            {
                string[] date = line.Split(',');
                string id = date[0];
                int id1 = Int16.Parse(date[1]);
                Echipa e1 = RepositoryEchipe.FindOne(id1);
                int id2 = Int16.Parse(date[2]);
                Echipa e2 = RepositoryEchipe.FindOne(id2);
                DateTime data = DateTime.Parse(date[3]);
                Meci j = new Meci(e1, e2, data);
                StoreFromFile(j);
            }
        }

        public override void WriteToFile()
        {
            List<Meci> l = FindAll();
            List<string> lines = new List<string>();
            foreach (Meci e in l)
            {
                string id = e.Id.ToString();
                string id1 = e.Echipa1.Id.ToString();
                string id2 = e.Echipa2.Id.ToString();
                string data = e.Data.ToString();
                string line = id + ',' + id1+ ',' + id2 + ',' + data;
                lines.Add(line);
            }
            System.IO.File.WriteAllLines(fileName, lines);
        }
    }
}
