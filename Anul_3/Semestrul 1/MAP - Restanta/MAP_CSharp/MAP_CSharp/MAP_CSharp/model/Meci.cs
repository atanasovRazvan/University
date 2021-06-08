using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Remoting.Messaging;
using System.Text;
using System.Threading.Tasks;

namespace MAP_CSharp.model
{
    public class Meci : Entitate<string>
    {
        public string Id { get; set; }
        public Echipa FirstTeam { get; set; }
        public Echipa SecondTeam { get; set; }
        public DateTime Date { get; set; }

        public Meci(string id, Echipa firstTeam, Echipa secondTeam, DateTime date)
        {
            Id = id;
            FirstTeam = firstTeam;
            SecondTeam = secondTeam;
            Date = date;
        }
        public override string ToString()
        {
            return Id + " " + FirstTeam + " " + SecondTeam + " " + Date;
        }
    }
}
