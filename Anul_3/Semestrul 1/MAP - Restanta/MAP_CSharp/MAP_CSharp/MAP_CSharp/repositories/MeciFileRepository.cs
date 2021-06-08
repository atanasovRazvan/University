using System;
using MAP_CSharp.model;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Globalization;

namespace MAP_CSharp.repositories
{

    public class MeciFileRepository : FileRepository<string, Meci>
    {
        private EchipaFileRepository echipaFileRepository;
        public MeciFileRepository(String fileName, EchipaFileRepository _echipaFileRepository) : base(fileName)
        {
            echipaFileRepository = _echipaFileRepository;
            ReadFromFile();
        }

        protected override Meci ReadEntity(string line)
        {
            String[] attributes = line.Split(',');
            Echipa echipa1 = echipaFileRepository.FindOne(attributes[1]);
            Echipa echipa2 = echipaFileRepository.FindOne(attributes[2]);

            return new Meci(attributes[0], echipa1, echipa2, DateTime.ParseExact(attributes[3], "dd/MM/yyyy hh:mm:ss", CultureInfo.InvariantCulture));
        }

        protected override string WriteEntity(Meci entity)
        {
            return entity.Id + "," + entity.FirstTeam.Id + "," + entity.SecondTeam.Id + "," + entity.Date;
        }
    }
}
