using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab10.entities
{
    class IdJucatorActiv
    {
        public int IdJuc { get; set; }
        public string IdMeci { get; set; }
        public IdJucatorActiv(int idj,string idm)
        {
            IdJuc = idj;
            IdMeci = idm;
        }
    }
    class JucatorActiv:Entity<IdJucatorActiv>
    {
        public int IdJ { get; set; }
        public string IdM { get; set;}
        public int NrPuncteInscrise { get; set; }
        public enum TipTD { Rezerva,Participant}
        public TipTD Tip { get; set; }
        public JucatorActiv(int idJ,string idM, int nrPuncteInscrise, TipTD tip):base(new IdJucatorActiv(idJ,idM))
        {
            IdJ = idJ;
            IdM = idM;
            NrPuncteInscrise = nrPuncteInscrise;
            Tip = tip;
        }
        public override string ToString()
        {
            return "Jucatorul " + IdJ.ToString() + " este jucator activ in meciul " + IdM.ToString()+" si are statut de "+Tip.ToString();
        }
    }
}
