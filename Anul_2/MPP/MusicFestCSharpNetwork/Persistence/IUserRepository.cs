namespace Persistence
{
    public interface IUserRepository
    {
        bool FindOne(string username, string password);
    }
}