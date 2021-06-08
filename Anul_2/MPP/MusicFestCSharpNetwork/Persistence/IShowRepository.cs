using System;
using System.Collections.Generic;
using Model;

namespace Persistence
{
    public interface IShowRepository
    {
        List<Show> FindAll();
        void Update(Show s);
        Show FindOne(string id);
        List<Show> findByDate(DateTime date);
        
    }
}