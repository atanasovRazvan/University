using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab10.validation
{
    class ValidationException:Exception
    {
        public string Message { get; set; }
        public ValidationException(string mes)
        {
            Message = mes;
        }
    }
}
