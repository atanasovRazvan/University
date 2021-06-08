using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab10.entities
{
    class Entity<ID>
    {
        public ID Id { get; set; }
        public Entity()
        {

        }
        public Entity(ID id)
        {
            this.Id = id;
        }
    }
}
