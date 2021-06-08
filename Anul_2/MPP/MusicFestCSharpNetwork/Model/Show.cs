using System;

namespace Model
{
    [Serializable]
    public class Show
    {
        public string Id { get; set; }
        public string ArtistName { get; set; }
        public DateTime DateOfShow { get; set; }
        public string Venue { get; set; }
        public int RemainingTickets { get; set; }
        public int TotalTickets { get; set; }

        public Show(string id, string artistName, DateTime dateOfShow, string venue, int remainingTickets, int totalTickets)
        {
            Id = id;
            ArtistName = artistName;
            DateOfShow = dateOfShow;
            Venue = venue;
            RemainingTickets = remainingTickets;
            TotalTickets = totalTickets;
        }


        protected bool Equals(Show other)
        {
            return Id == other.Id || (ArtistName == other.ArtistName && DateOfShow.Equals(other.DateOfShow));
        }

        public override int GetHashCode()
        {
            unchecked
            {
                var hashCode = (Id != null ? Id.GetHashCode() : 0);
                hashCode = (hashCode * 397) ^ (ArtistName != null ? ArtistName.GetHashCode() : 0);
                hashCode = (hashCode * 397) ^ DateOfShow.GetHashCode();
                return hashCode;
            }
        }

        public override string ToString()
        {
            return Id + " " + ArtistName + " " + DateOfShow + " " + Venue + " " + RemainingTickets + " " + TotalTickets;
        }
    }
}