using System;

namespace MusicFestCSharpNetwork
{
    public class TicketingEventArgs : EventArgs
    {
        private readonly Object data;

        public TicketingEventArgs(object data)
        {
            this.data = data;
        }
        
        public object Data
        {
            get { return data; }
        }
    }
}