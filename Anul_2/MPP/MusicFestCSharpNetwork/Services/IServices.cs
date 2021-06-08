using System;
using System.Collections.Generic;
using Model;

namespace Services
{
    public interface IServices
    {
        void login(String username, String password, IObserver client);
        void logout(User user, IObserver client);
        List<Show> getAllShows();
        List<Show> getShowByDate(DateTime date);
        void buyTicket(String idShow, String buyerName, int quantity);
    }
}