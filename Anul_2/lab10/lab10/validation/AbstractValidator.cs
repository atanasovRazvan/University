using lab10.entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab10.validation
{
    abstract class AbstractValidator<ID,E> where E:Entity<ID>
    {
        abstract public void Validate(E entity);
    }
}
