using System;
using System.Net.Sockets;
using System.Threading;
using Networking;
using Persistence;
using Services;

namespace Server
{
    static class StartServer
    {
        static void Main(string[] args)
        {
            IUserRepository userRepo = new UserDBRepository();
            IShowRepository showRepo = new ShowDBRepository();
            ITicketRepository ticketRepo = new TicketDBRepository();
            IServices serviceImpl = new ServerImplementation(userRepo, showRepo, ticketRepo);

            MyConcurrentServer server = new MyConcurrentServer("127.0.0.1", 55555, serviceImpl);
            server.Start();
            Console.WriteLine("Server awaiting connections on port 55555");
            Console.ReadLine();
        }
    }

    public class MyConcurrentServer : ServerUtils.ConcurrentServer
    {
        private IServices server;
        private ClientWorker worker;

        public MyConcurrentServer(string host, int port, IServices server) : base(host, port)
        {
            this.server = server;
            Console.WriteLine("MyConcurrentServer...");
        }

        protected override Thread createWorker(TcpClient client)
        {
            worker = new ClientWorker(server, client);
            return new Thread(new ThreadStart(worker.run));
        }
    }
}