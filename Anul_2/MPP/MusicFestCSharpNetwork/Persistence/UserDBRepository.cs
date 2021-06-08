using System;
using System.Data.SQLite;
using log4net;
using NUnit.Framework;

namespace Persistence
{
    public class UserDBRepository : IUserRepository
    {
        private static readonly ILog log = LogManager.GetLogger("UserDBRepository");

        public UserDBRepository()
        {
            log.Info("Creating a UserDBRepository");
        }

        public bool FindOne(string username, string password)
        {
            log.InfoFormat("Entering findOne for user {0}", username);
            String sql = "SELECT 1 FROM Users where username = @username and password = @password;";
            var conn = DBUtils.getConnection();
            Console.WriteLine(conn.State);
            using (var cmd = new SQLiteCommand(sql, conn))
            {
                var param1 = cmd.CreateParameter();
                param1.ParameterName = "@username";
                param1.Value = username;
                var param2 = cmd.CreateParameter();
                param2.ParameterName = "@password";
                param2.Value = password;
                cmd.Parameters.Add(param1);
                cmd.Parameters.Add(param2);
                using (var reader = cmd.ExecuteReader())
                {
                    log.InfoFormat("Exiting findOne with value {0}", reader.HasRows);
                    return reader.Read();
                }
                
            }
            return true;
        }
    }

}