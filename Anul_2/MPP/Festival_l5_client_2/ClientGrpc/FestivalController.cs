using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Grpc.Core;
using Model;
using NetworkingGrpc;
using Bilet = Model.Bilet;
using Spectacol = Model.Spectacol;

namespace ClientGrpc
{
    public class FestivalController
    {
        
        private FestivalService.FestivalServiceClient server;
        
        
        public FestivalController(FestivalService.FestivalServiceClient server)
        {
            this.server = server;
        }

        public bool Login(string username, string pass)
        {
            NetworkingGrpc.User userProto = new NetworkingGrpc.User { Username = username, Password = pass };
            LoginRequest req = new LoginRequest();
            req.User = userProto;
            var reply = server.login(req);
            return reply.Logged;
            
        }

        public IEnumerable<Spectacol> GetAllSpectacol()
        {
            var reply = server.getAllSpectacol(new GetAllRequest());
            var allProto = reply.Spectacole;
            List<Model.Spectacol> allSpectacole = new List<Spectacol>();
            foreach (var spectacol in allProto)
            {
                Model.Spectacol newSpectacol = new Spectacol(spectacol.Id, spectacol.Artist, DateTime.Parse(spectacol.Date), spectacol.Loc, spectacol.LocuriDisp, spectacol.LocuriVandute, spectacol.Ora);
                allSpectacole.Add(newSpectacol);
            }

            return allSpectacole;

        }

        public async void AddBilet(Bilet bilet)
        {
            NetworkingGrpc.Bilet biletProto = new NetworkingGrpc.Bilet(){Id = bilet.Id, NrLocuri = bilet.NrLocuri, NumeCumparator = bilet.Nume, SpectacolId = bilet.SpectacolId};
            var requests = new List<SaveBiletRequest>
            {
                new SaveBiletRequest(){Bilet = biletProto}
            };
            
            using (var call = server.saveBilet())
            {
                var responseReaderTask = Task.Run(async () =>
                {
                    while (await call.ResponseStream.MoveNext())
                    {
                        var note = call.ResponseStream.Current;
                        NetworkingGrpc.Spectacol spectacolProto = note.Spectacol;
                        Console.WriteLine("Received " + note);
                    }
                });

                foreach (SaveBiletRequest request in requests)
                {
                    await call.RequestStream.WriteAsync(request);
                }
                await call.RequestStream.CompleteAsync();
                await responseReaderTask;
            }
            
        }

        public IEnumerable<Model.Spectacol> GetAllSpectacolByDate(string data)
        {
            var reply = server.getSpectacolByDate(new GetAllByDateRequest(){Date = data});
            var allProto = reply.Spectacole;
            List<Model.Spectacol> allSpectacole = new List<Spectacol>();
            foreach (var spectacol in allProto)
            {
                Model.Spectacol newSpectacol = new Spectacol(spectacol.Id, spectacol.Artist, DateTime.Parse(spectacol.Date), spectacol.Loc, spectacol.LocuriDisp, spectacol.LocuriVandute, spectacol.Ora);
                allSpectacole.Add(newSpectacol);
            }

            return allSpectacole;
        }
    }
}