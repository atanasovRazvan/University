using lab10.entities;
using lab10.validation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab10.repository
{
    class RepositoryJucatori : AbstractRepository<int, Jucator>
    {
        private string fileName;
        private RepositoryEchipe RepositoryEchipe;
        private RepositoryElevi RepositoryElevi;
        public RepositoryJucatori(ValidatorJucator validator,RepositoryElevi repElevi,RepositoryEchipe rep,string fileName):base(validator)
        {
            this.fileName = fileName;
            RepositoryEchipe = rep;
            RepositoryElevi = repElevi;
            LoadFromFile();
        }
        public override void LoadFromFile()
        {
            string[] lines = System.IO.File.ReadAllLines(fileName);
            foreach (string line in lines)
            {
                string[] date = line.Split(',');
                int id = Int16.Parse(date[0]);
                //string nume = date[1];
                string nume = RepositoryElevi.FindOne(id).Nume;
                Elev.ScoalaTD scoala = (Elev.ScoalaTD)Enum.Parse(typeof(Elev.ScoalaTD), date[1]);
                int idEchipa = Int16.Parse(date[2]);
                Echipa e = RepositoryEchipe.FindOne(idEchipa);
                Jucator j = new Jucator(id, nume, scoala, e);
                StoreFromFile(j);
            }
        }

        public override void WriteToFile()
        {
            List<Jucator> l = FindAll();
            List<string> lines = new List<string>();
            foreach (Jucator e in l)
            {
                string id = e.Id.ToString();
                //string nume = e.Nume.ToString();
                string scoala = e.Scoala.ToString();
                string idEch = e.Echipa.Id.ToString();
                string line = id + ',' + ',' + scoala+','+idEch;
                lines.Add(line);
            }
            System.IO.File.WriteAllLines(fileName, lines);
        }
    }
}
