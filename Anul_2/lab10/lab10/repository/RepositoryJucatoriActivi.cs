using lab10.entities;
using lab10.validation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab10.repository
{
    class RepositoryJucatoriActivi : AbstractRepository<IdJucatorActiv, JucatorActiv>
    {
        private string fileName;
        public RepositoryJucatoriActivi(ValidatorJucatorActiv validator,string fileName):base(validator)
        {
            this.fileName = fileName;
            LoadFromFile();
        }
        public override void LoadFromFile()
        {
            string[] lines = System.IO.File.ReadAllLines(fileName);
            foreach (string line in lines)
            {
                string[] date = line.Split(',');
                int idj = Int16.Parse(date[0]);
                string idm = date[1];
                int puncte = Int16.Parse(date[2]);
                JucatorActiv.TipTD tip = (JucatorActiv.TipTD)Enum.Parse(typeof(JucatorActiv.TipTD), date[3]);
                JucatorActiv j = new JucatorActiv(idj, idm, puncte, tip);
                StoreFromFile(j);
            }
        }

        public override void WriteToFile()
        {
            List<JucatorActiv> l = FindAll();
            List<string> lines = new List<string>();
            foreach (JucatorActiv e in l)
            {
                string idj = e.IdJ.ToString();
                string idm = e.IdM;
                string pct = e.NrPuncteInscrise.ToString();
                string tip = e.Tip.ToString();
                string line = idj + ',' + idm + ',' + pct + ',' + tip;
                lines.Add(line);
            }
            System.IO.File.WriteAllLines(fileName, lines);
        }
    }
}
