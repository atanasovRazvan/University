using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MAP_CSharp.model
{
    public class Echipa:Entitate<string>
    {
        public string Id { get; set; }
        public string Nume { get; set; }
        public Echipa(string id, string nume)
        {
            Id = id;
            Nume = nume;
        }
        public override string ToString()
        {
            return Id + " " + Nume;
        }

    }
}
