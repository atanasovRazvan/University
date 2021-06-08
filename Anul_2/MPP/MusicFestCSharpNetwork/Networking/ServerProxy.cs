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
    public class ServerProxy : IServices
    {
        private string host;
        private int port;

        private IObserver client;

        private NetworkStream stream;

        private IFormatter formatter;
        private TcpClient connection;

        private Queue<Response> responses;
        private volatile bool finished;
        private EventWaitHandle _waitHandle;

        public ServerProxy(string host, int port)
        {
            this.host = host;
            this.port = port;
            responses = new Queue<Response>();
        }

        public void login(string username, string password, IObserver client)
        {
            initializeConnection();
            User user = new User(username, password);
            sendRequest(new LoginRequest(user));
            Response response = readResponse();
            if (response is OkResponse)
            {
                this.client = client;
                return;
            }

            if (response is ErrorResponse)
            {
                ErrorResponse err = (ErrorResponse) response;
                closeConnection();
                throw new ChatException(err.Message);
            }
        }

        public void logout(User user, IObserver client)
        {
            sendRequest(new LogoutRequest(user));
            Response response = readResponse();
            closeConnection();
            if (response is ErrorResponse)
            {
                ErrorResponse err = (ErrorResponse) response;
                throw new ChatException(err.Message);
            }
        }

        public List<Show> getAllShows()
        {
            sendRequest(new GetAllShowsRequest());
            Response response = readResponse();
            if (response is ErrorResponse)
            {
                ErrorResponse err = (ErrorResponse) response;
                throw new ChatException(err.Message);
            }

            GetAllShowsResponse resp = (GetAllShowsResponse) response;
            List<Show> shows = resp.Shows;
            return shows;
        }

        public List<Show> getShowByDate(DateTime date)
        {
            sendRequest(new GetShowsByDateRequest(date));
            Response response = readResponse();
            if (response is ErrorResponse)
            {
                ErrorResponse err = (ErrorResponse) response;
                throw new ChatException(err.Message);
            }

            GetShowsByDateResponse resp = (GetShowsByDateResponse) response;
            List<Show> shows = resp.Shows;
            return shows;
        }

        public void buyTicket(string idShow, string buyerName, int quantity)
        {
            TicketDTO ticketDto = new TicketDTO(idShow, buyerName, quantity);
            sendRequest(new BuyTicketsRequest(ticketDto));
            Response response = readResponse();
            if (response is OkResponse)
            {
                Console.WriteLine("Tickets successfully bought");
                return;
            }

            if (response is ErrorResponse)
            {
                ErrorResponse err = (ErrorResponse) response;
                closeConnection();
                throw new ChatException(err.Message);
            }
        }

        private void initializeConnection()
        {
            try
            {
                connection = new TcpClient(host, port);
                stream = connection.GetStream();
                formatter = new BinaryFormatter();
                finished = false;
                _waitHandle = new AutoResetEvent(false);
                startReader();
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
        }

        private void closeConnection()
        {
            finished = true;
            try
            {
                stream.Close();
                //output.close();
                connection.Close();
                _waitHandle.Close();
                client = null;
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
        }

        private void sendRequest(Request request)
        {
            try
            {
                formatter.Serialize(stream, request);
                stream.Flush();
            }
            catch (Exception e)
            {
                throw new ChatException("Error sending object " + e);
            }
        }

        private Response readResponse()
        {
            Response response = null;
            try
            {
                _waitHandle.WaitOne();
                lock (responses)
                {
                    //Monitor.Wait(responses); 
                    response = responses.Dequeue();
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }

            return response;
        }

        private void handleUpdate(UpdateResponse update)
        {
            SoldTicketsResponse soldUpdate = (SoldTicketsResponse) update;
            Console.WriteLine("updating sold Tickets...");
            try
            {
                client.soldTicketsUpdate(soldUpdate.Shows);
            }
            catch (ChatException e)
            {
                Console.WriteLine(e.StackTrace);
            }
        }

        private void startReader()
        {
            Thread tw = new Thread(run);
            tw.Start();
        }

        public virtual void run()
        {
            while (!finished)
            {
                try
                {
                    object response = formatter.Deserialize(stream);
                    Console.WriteLine("response received " + response);
                    if (response is UpdateResponse)
                    {
                        handleUpdate((UpdateResponse) response);
                    }
                    else
                    {
                        lock (responses)
                        {
                            responses.Enqueue((Response) response);
                        }

                        _waitHandle.Set();
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine("Reading error " + e);
                }
            }
        }
    }
}