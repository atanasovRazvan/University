using System;
using System.Collections.Generic;
using Model;

namespace FestivalGUI
{
    public class FestivalController
    {
        public event EventHandler<FestivalEventArgs> updateEvent; 
        //private readonly IService server;
        
        //public FestivalController(IService server)
        //{
        //    this.server = server;
        //}

        //public void Login(string username, string pass)
        //{
        //    User user = new User(username,pass);
        //    server.Login(user,this);
        //    Console.WriteLine("Login succeeded ....");
        //}

        //public IEnumerable<Spectacol> GetAllSpectacol()
        //{
        //    return server.GetAllSpectacol();
        //}

        //public void AddBilet(Bilet bilet, Spectacol spectacol)
        //{
        //    spectacol.BileteDisp -= bilet.NrLocuri;
        //    spectacol.BileteVandute += bilet.NrLocuri;
        //    FestivalEventArgs eventArgs = new FestivalEventArgs(FestivalEventType.SpectacolUpdated, spectacol);
        //    Console.WriteLine("Spectacol updated");
        //    OnFestivalEvent(eventArgs);
        //    server.SaveBilet(bilet);
            
        //}

        //public void SpectacolUpdated(Spectacol spectacol)
        //{
        //    FestivalEventArgs eventArgs = new FestivalEventArgs(FestivalEventType.SpectacolUpdated, spectacol);
        //    Console.WriteLine("Spectacol updated");
        //    OnFestivalEvent(eventArgs);
        //}

        //protected virtual void OnFestivalEvent(FestivalEventArgs eventArgs)
        //{
        //    if (updateEvent == null) return;
        //    updateEvent(this, eventArgs);
        //    Console.WriteLine("Update Event called");
        //}

        //public object GetAllSpectacolByDate(DateTime data)
        //{
        //    return server.GetSpectacolByDate(data);
        //}
    }
}