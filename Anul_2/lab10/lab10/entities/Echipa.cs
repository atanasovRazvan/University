using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab10.entities
{
    class Echipa:Entity<int>
    {
        public enum NumeTD { Houston_Rockets,Los_Angeles_Lakers,LA_Clippers,Chicago_Bulls,Cleveland_Cavaliers,Utah_Jazz,Brooklyn_Nets,New_Orleans_Pelicans,Indiana_Pacers,Toronto_Raptors,Charlotte_Hornets,Phoenix_Suns,Portland_TrailBlazers,Golden_State_Warriors,Washington_Wizards,San_Antonio_Spurs,Orlando_Magic,Denver_Nuggets,Detroit_Pistons,Atlanta_Hawks,Dallas_Mavericks,Sacramento_Kings,Oklahoma_City_Thunder,Boston_Celtics,New_York_Knicks,Minnesota_Timberwolves,Miami_Heat,Milwaukee_Bucks };
        public NumeTD Nume { get; set; }
        private int ide;
        public Echipa(int id, NumeTD nume) : base(id)
        {
            this.Nume = nume;
            ide = id;
        }
        public override string ToString()
        {
            return "Echipa "+ide.ToString()+"--" + Nume.ToString();
        }
    }
}
