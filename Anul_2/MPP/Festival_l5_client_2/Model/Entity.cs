using System;
using System.Collections.Generic;
using System.Text;

namespace Model
{
    [Serializable]
    public class Entity<T>
    {
        T Id { get; set; }
    }
}
