using System;

namespace Model
{
    [Serializable]
    public class Ticket
    {
        public string IdShow { get; set; }
        public int NrTicket { get; set; }
        public string BuyerName { get; set; }

        public Ticket(string idShow, int nrTicket, string buyerName)
        {
            IdShow = idShow;
            NrTicket = nrTicket;
            BuyerName = buyerName;
        }

        public override string ToString()
        {
            return IdShow + " " + NrTicket + " " + BuyerName;
        }

        protected bool Equals(Ticket other)
        {
            return IdShow == other.IdShow && NrTicket == other.NrTicket;
        }

        public override int GetHashCode()
        {
            unchecked
            {
                return ((IdShow != null ? IdShow.GetHashCode() : 0) * 397) ^ NrTicket;
            }
        }
    }
}