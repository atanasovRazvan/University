using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MAP_CSharp.model
{
    public class JucatorActiv : Entitate<Tuple<string, string>>
    {
        public Tuple<string, string> Id { get; set; }
        public int Goluri { get; set; }
        public TipJucator TipJucator { get; set; }
        public JucatorActiv(string idJucator, string idMeci, int goluri, TipJucator tipJucator)
        {
            Id = new Tuple<string, string>(idJucator, idMeci);
            Goluri = goluri;
            TipJucator = tipJucator;
        }
        public override string ToString()
        {
            return Id.Item1 + " " + Id.Item2 + " " + Goluri + " " + TipJucator;
        }
    }
}
