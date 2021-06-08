using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BD_Lab1
{
    public class Field
    {
        public string Fname { get; set; }
        public string Type { get; set; }
        public bool IsPK { get; set; }
        public bool IsFK { get; set; }
    }

    public class Table
    {
        public Table()
        {
            Fields = new List<Field>();
        }

        public string Name { get; set; }
        public int Nofields { get; set; }
        public List<Field> Fields { get; set; }

    }
}
