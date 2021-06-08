using System;
using Model;

namespace Networking
{
    public interface Request
    {
    }

    [Serializable]
    public class LoginRequest : Request
    {
        private User user;

        public LoginRequest(User user)
        {
            this.user = user;
        }

        public virtual User User
        {
            get { return user; }
        }
    }

    [Serializable]
    public class LogoutRequest : Request
    {
        private User user;

        public LogoutRequest(User user)
        {
            this.user = user;
        }

        public virtual User User
        {
            get { return user; }
        }
    }

    [Serializable]
    public class GetAllShowsRequest : Request
    {
        public GetAllShowsRequest()
        {
        }
    }

    [Serializable]
    public class GetShowsByDateRequest : Request
    {
        private DateTime date;

        public GetShowsByDateRequest(DateTime date)
        {
            this.date = date;
        }

        public virtual DateTime Date
        {
            get { return date; }
        }
    }

    [Serializable]
    public class BuyTicketsRequest : Request
    {
        private TicketDTO ticket;

        public BuyTicketsRequest(TicketDTO ticket)
        {
            this.ticket = ticket;
        }

        public virtual TicketDTO TicketDto
        {
            get { return ticket; }
        }
    }
    
}