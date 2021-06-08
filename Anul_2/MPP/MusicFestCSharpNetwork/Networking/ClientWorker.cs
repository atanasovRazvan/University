using System;
using System.Collections.Generic;
using System.Net.Sockets;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Threading;
using Model;
using Services;

namespace Networking
{
    public class ClientWorker : IObserver
    {
        private IServices server;
        private TcpClient connection;

        private NetworkStream stream;
        private IFormatter formatter;
        private volatile bool connected;

        public ClientWorker(IServices server, TcpClient connection)
        {
            this.server = server;
            this.connection = connection;
            try
            {
                stream = connection.GetStream();
                formatter = new BinaryFormatter();
                connected = true;
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
        }

        public virtual void run()
        {
            while (connected)
            {
                try
                {
                    object request = formatter.Deserialize(stream);
                    object response = handleRequest((Request) request);
                    if (response != null)
                    {
                        sendResponse((Response) response);
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.StackTrace);
                }

                try
                {
                    Thread.Sleep(1000);
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.StackTrace);
                }
            }

            try
            {
                stream.Close();
                connection.Close();
            }
            catch (Exception e)
            {
                Console.WriteLine("Error " + e);
            }
        }

        private Response handleRequest(Request request)
        {
            Response response = null;
            if (request is LoginRequest)
            {
                Console.WriteLine("Login request ...");
                LoginRequest logReq = (LoginRequest) request;
                User user = logReq.User;
                try
                {
                    lock (server)
                    {
                        server.login(user.Username, user.Password, this);
                    }

                    return new OkResponse();
                }
                catch (ChatException e)
                {
                    connected = false;
                    return new ErrorResponse(e.Message);
                }
            }

            if (request is LogoutRequest)
            {
                Console.WriteLine("Logout request");
                LogoutRequest logReq = (LogoutRequest) request;
                User user = logReq.User;
                try
                {
                    lock (server)
                    {
                        server.logout(user, this);
                    }

                    connected = false;
                    return new OkResponse();
                }
                catch (ChatException e)
                {
                    return new ErrorResponse(e.Message);
                }
            }

            if (request is GetAllShowsRequest)
            {
                Console.WriteLine("Get all shows request");
                try
                {
                    List<Show> shows;
                    lock (server)
                    {
                        shows = server.getAllShows();
                    }

                    return new GetAllShowsResponse(shows);
                }
                catch (ChatException e)
                {
                    return new ErrorResponse(e.Message);
                }
            }

            if (request is GetShowsByDateRequest)
            {
                Console.WriteLine("Get shows by date request");
                GetShowsByDateRequest dateRequest = (GetShowsByDateRequest) request;
                DateTime date = dateRequest.Date;
                try
                {
                    List<Show> shows;
                    lock (server)
                    {
                        shows = server.getShowByDate(date);
                    }

                    return new GetShowsByDateResponse(shows);
                }
                catch (ChatException e)
                {
                    return new ErrorResponse(e.Message);
                }
            }

            if (request is BuyTicketsRequest)
            {
                Console.WriteLine("Buy tickets request");
                BuyTicketsRequest buyRequest = (BuyTicketsRequest) request;
                TicketDTO ticket = buyRequest.TicketDto;
                try
                {
                    string idShow = ticket.IdShow;
                    string buyerName = ticket.BuyerName;
                    int quantity = ticket.Quantity;
                    server.buyTicket(idShow, buyerName, quantity);
                    return new OkResponse();
                }
                catch (ChatException e)
                {
                    return new ErrorResponse(e.Message);
                }
            }

            return response;
        }

        private void sendResponse(Response response)
        {
            Console.WriteLine("sending response " + response);
            formatter.Serialize(stream, response);
            stream.Flush();
        }

        public void soldTicketsUpdate(List<Show> shows)
        {
            Console.WriteLine("Announce sold tickets");
            try
            {
                sendResponse(new SoldTicketsResponse(shows));
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
        }
    }
}