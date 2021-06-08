using Model;

namespace Persistence
{
    public interface ITicketRepository
    {
        void Save(Ticket ticket);
        Ticket FindOne(string id, int number);
        void Delete(string id, int number);
    }
}