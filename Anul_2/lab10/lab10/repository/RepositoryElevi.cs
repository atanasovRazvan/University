using lab10.entities;
using lab10.validation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab10.repository
{
    class RepositoryElevi : AbstractRepository<int, Elev>
    {
        private string NumeRandom(int length)
        {
            Random r = new Random();
            const string chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
            return new string(Enumerable.Repeat(chars, length).Select(s => s[r.Next(s.Length)]).ToArray());
        }

        private void GenerateEleviSc(string scoala, int a)
        {
            List<string> lines = new List<string>();
            for (int i = a; i < a + 15; i++)
            {
                string nume = NumeRandom(5);
                string line = i.ToString() + ',' + nume + ',' + scoala;
                lines.Add(line);
            }
            System.IO.File.AppendAllLines(@"C:\Users\Razvan\Desktop\lab10\lab10\fisiereTXT\elevi.txt", lines);
        }

        private void GenerateElevi()
        {
            GenerateEleviSc("Scoala_Gimnaziala_Horea", 1);
            GenerateEleviSc("Scoala_Gimnaziala_Octavian_Goga", 16);
            GenerateEleviSc("Liceul_Teoretic_Lucian_Blaga", 31);
            GenerateEleviSc("Scoala_Gimnaziala_Ioan_Bob", 46);
        }
        private string fileName;
        public RepositoryElevi(ValidatorElev validator,string fileName):base(validator)
        {
            this.fileName = fileName;
            GenerateElevi();
            LoadFromFile();
        }
        public override void LoadFromFile()
        {
            string[] lines = System.IO.File.ReadAllLines(fileName);
            foreach (string line in lines)
            {
                string[] date = line.Split(',');
                int id = Int16.Parse(date[0]);
                string nume = date[1];
                Elev.ScoalaTD scoala = (Elev.ScoalaTD)Enum.Parse(typeof(Elev.ScoalaTD),date[2]);
                Elev e = new Elev(id, nume, scoala);
                StoreFromFile(e);
            }
        }

        public override void WriteToFile()
        {
            List<Elev> l = FindAll();
            List<string> lines = new List<string>();
            foreach(Elev e in l)
            {
                string id = e.Id.ToString();
                string nume = e.Nume;
                string scoala = e.Scoala.ToString();
                string line = id + ',' + nume + ',' + scoala;
                lines.Add(line);
            }
            System.IO.File.WriteAllLines(fileName,lines);
        }
    }
}
