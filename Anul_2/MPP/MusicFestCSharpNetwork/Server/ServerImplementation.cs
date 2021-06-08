using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Model;
using Persistence;
using Services;

namespace Server
{
    public class ServerImplementation : IServices
    {
        private IUserRepository userRepository;
        private IShowRepository showRepository;
        private ITicketRepository ticketRepository;
        private readonly IDictionary<String, IObserver> loggedClients;

        public ServerImplementation(IUserRepository userRepository, IShowRepository showRepository,
            ITicketRepository ticketRepository)
        {
            this.userRepository = userRepository;
            this.showRepository = showRepository;
            this.ticketRepository = ticketRepository;
            loggedClients = new Dictionary<String, IObserver>();
        }

        public void login(String username, String password, IObserver client)
        {
            bool userOk = userRepository.FindOne(username, password);
            if (userOk)
            {
                if (loggedClients.ContainsKey(username))
                    throw new ChatException("User already logged in.");
                loggedClients[username] = client;
            }
            else
                throw new ChatException("Authentication failed.");
        }

        public void logout(User user, IObserver client)
        {
            IObserver localClient = loggedClients[user.Username];
            if (localClient == null)
                throw new ChatException("User " + user.Username + " is not logged in.");
            loggedClients.Remove(user.Username);
        }

        public List<Show> getAllShows()
        {
            return showRepository.FindAll();
        }

        public List<Show> getShowByDate(DateTime date)
        {
            return showRepository.findByDate(date);
        }

        private void notifyTicketsSold()
        {
            foreach (var elem in loggedClients)
            {
                Console.WriteLine("notifying client {0}", elem.Key);
                Task.Run(() => elem.Value.soldTicketsUpdate(this.getAllShows()));
            }
        }

        public void buyTicket(string idShow, string buyerName, int quantity)
        {
            for (int i = 0; i < quantity; i++)
            {
                Show s = showRepository.FindOne(idShow);
                int number = s.TotalTickets - s.RemainingTickets + 1;
                ticketRepository.Save(new Ticket(idShow, number, buyerName));
                s.RemainingTickets = s.RemainingTickets - 1;
                showRepository.Update(s);
            }
            notifyTicketsSold();
        }
    }
}