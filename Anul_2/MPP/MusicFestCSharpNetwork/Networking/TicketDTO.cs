﻿using System;

 namespace Networking
{
    [Serializable]
    public class TicketDTO
    {
        public string IdShow { get; set; }
        public string BuyerName { get; set; }
        public int Quantity { get; set; }

        public TicketDTO(string idShow, string buyerName, int quantity)
        {
            IdShow = idShow;
            BuyerName = buyerName;
            this.Quantity = quantity;
        }
    }
}