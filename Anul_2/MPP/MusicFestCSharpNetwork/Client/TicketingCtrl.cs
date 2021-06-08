using System;
using System.Collections.Generic;
using Model;
using MusicFestCSharpNetwork;
using Services;

namespace MusicFest
{
    public class TicketingCtrl :IObserver
    {
        public event EventHandler<TicketingEventArgs> updateEvent;
        private readonly IServices server;
        private User currentUser;


        public TicketingCtrl(IServices server)
        {
            this.server = server;
            currentUser = null;
        }

        public void login(String username, String password){
            server.login(username, password,this);
            Console.WriteLine("Login succeeded ....");
            currentUser = new User(username,password);
            Console.WriteLine("Current user {0}", username);
        }

        public void logout()
        {
            Console.WriteLine(currentUser.Username+" logging out");
            server.logout(currentUser, this);
            currentUser = null;
        }

        public List<Show> getAllShows()
        {
            Console.WriteLine("Requesting shows");
            return server.getAllShows();
        }

        public List<Show> getShowsByDate(DateTime date)
        {
            Console.WriteLine("requesting shows for date {0}", date);
            return server.getShowByDate(date);
        }

        public void buyTickets(string idShow, string buyerName, int quantity)
        {
            Console.WriteLine("requesting buy");
            server.buyTicket(idShow,buyerName,quantity);
        }

        public void soldTickets(List<Show> shows)
        {
            System.Console.WriteLine("notifying window");
            TicketingEventArgs userArgs = new TicketingEventArgs(shows);
            OnUserEvent(userArgs);
        }
        
        protected virtual void OnUserEvent(TicketingEventArgs e)
        {
            if (updateEvent == null) return;
            updateEvent(this, e);
            Console.WriteLine("Update Event called");
        }
    }
}