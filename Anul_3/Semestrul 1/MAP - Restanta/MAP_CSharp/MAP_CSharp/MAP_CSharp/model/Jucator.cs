using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MAP_CSharp.model
{
    public class Jucator : Elev
    {
        public Echipa Echipa {get; set;}
        public Jucator(string id, string nume, string scoala, Echipa echipa) : base(id, nume, scoala)
        {
            Echipa = echipa;
        }
        public override string ToString()
        {
            return base.ToString() + " " + Echipa;
        }
    }
}
