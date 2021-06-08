using System.Data;
using System.Data.SQLite;
using System.Configuration;

namespace Persistence
{
    public class DBUtils
    {
        private static SQLiteConnection instance = null;
        public static SQLiteConnection getConnection()
        {
            if (instance == null || instance.State == System.Data.ConnectionState.Closed)
            {
                instance = getNewConnection();
                instance.Open();
            }
            return instance;
        }

        private static SQLiteConnection getNewConnection()
        {
            string connectionString = "Data Source=C:\\Users\\Razvan\\Desktop\\Facultate\\Anul_2\\MPP\\MusicFestCSharpNetwork\\MusicFest.db;Version=3;";
            return new SQLiteConnection(connectionString);
        }

        public static void closeConnection()
        {
            if (instance != null && instance.State != ConnectionState.Closed)
            {
                instance.Close();
            }
        }
    
    }
}