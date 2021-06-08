using System;
using MAP_CSharp.model;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MAP_CSharp.repositories
{
    public class JucatorFileRepository : FileRepository<string, Jucator>
    {
        private EchipaFileRepository teamFileRepository;
        public JucatorFileRepository(EchipaFileRepository teamFileRepository, String fileName) : base(fileName)
        {
            this.teamFileRepository = teamFileRepository;
            ReadFromFile();
        }

        protected override Jucator ReadEntity(string line)
        {
            String[] attributes = line.Split(',');
            Echipa t = teamFileRepository.FindOne(attributes[3]);
            return new Jucator(attributes[0], attributes[1], attributes[2], t);

        }

        protected override string WriteEntity(Jucator entity)
        {
            return entity.Id + "," + entity.Nume + "," + entity.Scoala + "," + entity.Echipa.Id;
        }
    }
}
