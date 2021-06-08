using System;
using System.Collections.Generic;
using Model;

namespace Networking
{
    public interface Response
    {
    }

    [Serializable]
    public class OkResponse : Response
    {
    }

    [Serializable]
    public class ErrorResponse : Response
    {
        private string message;

        public ErrorResponse(string message)
        {
            this.message = message;
        }

        public virtual string Message
        {
            get { return message; }
        }
    }

    [Serializable]
    public class GetAllShowsResponse : Response
    {
        private List<Show> shows;

        public GetAllShowsResponse(List<Show> shows)
        {
            this.shows = shows;
        }

        public virtual List<Show> Shows
        {
            get { return shows; }
        }
    }

    [Serializable]
    public class GetShowsByDateResponse : Response
    {
        private List<Show> shows;

        public GetShowsByDateResponse(List<Show> shows)
        {
            this.shows = shows;
        }

        public virtual List<Show> Shows
        {
            get { return shows; }
        }
    }
    
    public interface UpdateResponse : Response
    {
    }
    
    [Serializable]
    public class SoldTicketsResponse : UpdateResponse
    {
        private List<Show> shows;

        public SoldTicketsResponse(List<Show> shows)
        {
            this.shows = shows;
        }

        public virtual List<Show> Shows
        {
            get { return shows; }
        }
    }
    
    
}