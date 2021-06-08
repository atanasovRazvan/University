using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MAP_CSharp.model
{
    public interface Entitate<ID>
    {
        ID Id { get; set; }
    }
}
