using System;
using System.Collections.Generic;
using System.Text;

namespace Model
{
    [Serializable]
    public class Bilet : Entity<int>
    {
        int id;
        string nume;
        int spectacolId;
        int nrLocuri;

        public Bilet(int biletId, string nume, int spectacolId, int nrLocuri)
        {
            Id = biletId;
            Nume = nume;
            SpectacolId = spectacolId;
            NrLocuri = nrLocuri;
            
        }

        
        public string Nume { get => nume; set => nume = value; }
        public int SpectacolId { get => spectacolId; set => spectacolId = value; }
        public int NrLocuri { get => nrLocuri; set => nrLocuri = value; }
        public int Id { get => id; set => id = value; }
    }
}
