
using System;

namespace Model
{
    [Serializable]
    public class Spectacol : Entity<int>
    {
        int id;
        string artist;
        DateTime dataSpectacol;
        string locatie;
        int bileteDisp;
        int bileteVandute;
        string ora;

        public Spectacol(int id, string artist, DateTime data, string loc, int disp, int vand, string ora)
        {
            Id = id;
            Artist = artist;
            DataSpectacol = data;
            Locatie = loc;
            BileteDisp = disp;
            BileteVandute = vand;
            Ora = ora;
        }

        public string Artist { get => artist; set => artist = value; }
        public DateTime DataSpectacol { get => dataSpectacol; set => dataSpectacol = value; }
        public string Locatie { get => locatie; set => locatie = value; }
        public int BileteDisp { get => bileteDisp; set => bileteDisp = value; }
        public int BileteVandute { get => bileteVandute; set => bileteVandute = value; }
        public string Ora { get => ora; set => ora = value; }
        public int Id { get => id; set => id = value; }

        public override bool Equals(object obj)
        {
            return base.Equals(obj);
        }

        public override int GetHashCode()
        {
            return base.GetHashCode();
        }

        public override string ToString()
        {
            return "Spectacol: id=" + Id + ", artist=" + Artist + ", data=" + DataSpectacol
                + ", loc=" + Locatie + ", disp=" + BileteDisp + ", vandute=" + BileteVandute +
                ", ora=" + Ora;
        }
    }
}
