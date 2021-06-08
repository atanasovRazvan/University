using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab10.entities
{
    class Jucator:Elev
    {
        public Echipa Echipa { get; set; }
        public Jucator(int id,string nume,ScoalaTD scoala,Echipa echipa):base(id,nume,scoala)
        {
            Echipa = echipa;
        }
        public override string ToString()
        {
            return base.ToString() + " joaca in echipa " + Echipa.Nume.ToString();
        }
    }
}
