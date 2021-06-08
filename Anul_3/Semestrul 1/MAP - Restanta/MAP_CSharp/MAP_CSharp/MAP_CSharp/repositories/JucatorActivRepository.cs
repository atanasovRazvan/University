using System;
using MAP_CSharp.model;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MAP_CSharp.repositories
{
    public class JucatorActivFileRepository : FileRepository<Tuple<string, string>, JucatorActiv>
    {
        public JucatorActivFileRepository(String fileName) : base(fileName)
        {
            ReadFromFile();
        }
        protected override JucatorActiv ReadEntity(string line)
        {
            String[] attributes = line.Split(',');
            return new JucatorActiv(attributes[0], attributes[1], int.Parse(attributes[2]), (TipJucator)Enum.Parse(typeof(TipJucator), attributes[3]));
        }

        protected override string WriteEntity(JucatorActiv entity)
        {
            return entity.Id.Item1 + "," + entity.Id.Item2 + "," + entity.Goluri.ToString() + "," + entity.TipJucator;
        }
    }
}
