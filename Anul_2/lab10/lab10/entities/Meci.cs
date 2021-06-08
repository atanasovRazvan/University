using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab10.entities
{
    class Meci:Entity<string>
    {
        public Echipa Echipa1{ get; set; }
        public Echipa Echipa2{ get; set; }
        public DateTime Data { get; set; }
        private string id;
        public Meci(Echipa e1,Echipa e2,DateTime d):base(e1.Id.ToString()+" "+e2.Id.ToString())
        {
            id = e1.Id.ToString() + " " + e2.Id.ToString();
            Echipa1 = e1;
            Echipa2 = e2;
            Data = d;
        }
        public override string ToString()
        {
            return "Meciul "+id+"   "+Echipa1.Id.ToString()+":"+Echipa1.Nume.ToString() + "-" + Echipa2.Id.ToString() + ":" + Echipa2.Nume.ToString() + " se joaca in data " + Data.ToString();
        }
    }
}
