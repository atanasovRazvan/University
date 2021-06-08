using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab10.entities
{
    class Elev:Entity<int>
    {
        public string Nume { get; set; }
        public enum ScoalaTD { Scoala_Gimnaziala_Horea, Scoala_Gimnaziala_Octavian_Goga,Liceul_Teoretic_Lucian_Blaga, Scoala_Gimnaziala_Ioan_Bob, Scoala_Gimnaziala_Ion_Creanga,Colegiul_National_Pedagogic_Gheorghe_Lazar, Scoala_Gimnaziala_Internationala_SPECTRUM,Colegiul_National_Emil_Racovita,Colegiul_National_George_Cosbuc, Scoala_Gimnaziala_Ion_Agrarbiceanu, Scoala_Gimnaziala_Constantin_Brancusi,Liceul_Teoretic_Avram_Iancu, Liceul_Teoretic_Onisifon_Ghibu, Liceul_cu_program_sportiv_Cluj_Napoca, Liceul_Teoretic_Nicolae_Balcescu, Liceul_Teoretic_Gheorghe_Sincai, Liceul_Teoretic_Bathory_Istvan, Liceul_Teoretic_Apaczai_Csere_Janos, Liceul_Teoretic_ELF, Scoala_Nicolae_Titulescu,Scoala_Gimnaziala_Liviu_Rebreanu, Scoala_Gimnaziala_Iuliu_Hatieganu,Colegiul_National_George_Baritiu,Seminarul_Teologic_Ortodox,Liceul_de_Informatica_Tiberiu_Popoviciu, Scoala_Gimnaziala_Alexandru_Vaida_Voevod, Scoala_Gimnaziala_Gheorghe_Sincai_Floresti}
        public ScoalaTD Scoala { get; set; }
        public Elev()
        {

        }
        public Elev(int id,string nume, ScoalaTD scoala):base(id)
        {
            Nume = nume;
            Scoala = scoala;
        }
        public override string ToString()
        {
            return "Elevul " + Nume + " de la scoala " + Scoala.ToString();
        }
    }
}
