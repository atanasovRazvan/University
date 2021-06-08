using System;

namespace FestivalGUI
{
    public enum FestivalEventType
    {
        SpectacolUpdated
    }
    public class FestivalEventArgs : EventArgs
    {
        private readonly FestivalEventType festivalEvent;
        
        private readonly Object data;

        public FestivalEventArgs(FestivalEventType festivalEvent, object data)
        {
            this.festivalEvent = festivalEvent;
            this.data = data;
        }

        public FestivalEventType FestivalEvent => festivalEvent;

        public object Data => data;
    }
}