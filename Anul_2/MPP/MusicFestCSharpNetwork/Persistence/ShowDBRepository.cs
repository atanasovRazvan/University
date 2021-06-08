using System;
using System.Collections.Generic;
using System.Data.SQLite;
using log4net;
using Model;
using NUnit.Framework;

namespace Persistence
{
    public class ShowDBRepository : IShowRepository
    {
        private static readonly ILog log = LogManager.GetLogger("ShowDBRepository");

        public ShowDBRepository()
        {
            log.Info("Creating a ShowDBRepository");
        }

        public List<Show> FindAll()
        {
            log.Info("Entering findAll");
            String sql = "SELECT * FROM Shows";
            var conn = DBUtils.getConnection();
            using (var cmd = new SQLiteCommand(sql, conn))
            {
                using (var reader = cmd.ExecuteReader())
                {
                    List<Show> shows = new List<Show>();
                    while (reader.Read())
                        shows.Add(new Show(reader.GetInt32(0).ToString(),
                            reader.GetString(1),
                            reader.GetDateTime(2),
                            reader["venue"].ToString(),
                            Int32.Parse(reader["remainingTickets"].ToString()),
                            Int32.Parse(reader["totalTickets"].ToString())));
                    log.Info("Exiting findAll");
                    DBUtils.closeConnection();
                    return shows;
                }
            }
        }
        
        

        public void Update(Show s)
        {
            log.InfoFormat("Entering update for show {0}", s.Id);
            String sql =
                "UPDATE Shows SET artistName = @artist, showDate = @date ,venue = @venue, remainingTickets = @remaining, totalTickets = @total where id = @id";
            var conn = DBUtils.getConnection();
            using (var cmd = new SQLiteCommand(sql, conn))
            {
                var paramId = cmd.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = s.Id;
                cmd.Parameters.Add(paramId);

                var paramArtist = cmd.CreateParameter();
                paramArtist.ParameterName = "@artist";
                paramArtist.Value = s.ArtistName;
                cmd.Parameters.Add(paramArtist);

                var paramVenue = cmd.CreateParameter();
                paramVenue.ParameterName = "@venue";
                paramVenue.Value = s.Venue;
                cmd.Parameters.Add(paramVenue);

                var paramTotal = cmd.CreateParameter();
                paramTotal.ParameterName = "@total";
                paramTotal.Value = s.TotalTickets;
                cmd.Parameters.Add(paramTotal);

                var paramRemaining = cmd.CreateParameter();
                paramRemaining.ParameterName = "@remaining";
                paramRemaining.Value = s.RemainingTickets;
                cmd.Parameters.Add(paramRemaining);

                var paramDate = cmd.CreateParameter();
                paramDate.ParameterName = "@date";
                paramDate.Value = s.DateOfShow;
                cmd.Parameters.Add(paramDate);

                var result = cmd.ExecuteNonQuery();
                DBUtils.closeConnection();
                log.Info("Update was successful");
                if (result == 0)
                {
                    log.Info("Update was unsuccessful");
                    throw new Exception("No show was updated!");
                }
            }
        }

        public Show FindOne(string id)
        {
            log.InfoFormat("Entering findOne for show {0}", id);

            String sql = "SELECT * FROM Shows where id = @id";
            var conn = DBUtils.getConnection();
            using (var cmd = new SQLiteCommand(sql, conn))
            {
                var param1 = cmd.CreateParameter();
                param1.ParameterName = "@id";
                param1.Value = id;
                cmd.Parameters.Add(param1);

                using (var reader = cmd.ExecuteReader())
                {
                    if (reader.Read())
                    {
                        Show s = new Show(reader["id"].ToString(), reader["artistName"].ToString(),
                            reader.GetDateTime(5), reader["venue"].ToString(),
                            Int32.Parse(reader["remainingTickets"].ToString()),
                            Int32.Parse(reader["totalTickets"].ToString()));
                        log.InfoFormat("Exiting findOne with value {0}", s);
                        DBUtils.closeConnection();
                        return s;
                    }
                    else
                    {
                        log.Info("Exiting findAll with value null");
                        DBUtils.closeConnection();
                        return null;
                    }
                }
            }
        }

        public List<Show> findByDate(DateTime date)
        {
            log.InfoFormat("Entering findByDate, date: {0}",date);
            Console.WriteLine(date.ToString());
            String sql = "SELECT * FROM Shows where showDate = @date;";
            var conn = DBUtils.getConnection();
            using (var cmd = new SQLiteCommand(sql, conn))
            {
                var paramDate = cmd.CreateParameter();
                paramDate.ParameterName = "@date";
                paramDate.Value = date;
                cmd.Parameters.Add(paramDate);
                
                using (var reader = cmd.ExecuteReader())
                {
                    List<Show> shows = new List<Show>();
                    while (reader.Read())
                        shows.Add(new Show(reader.GetString(0),
                            reader.GetString(1),
                            reader.GetDateTime(5), reader["venue"].ToString(),
                            Int32.Parse(reader["remainingTickets"].ToString()),
                            Int32.Parse(reader["totalTickets"].ToString())));
                    log.Info("Exiting findAll");
                    DBUtils.closeConnection();
                    return shows;
                }
            }
            
        }
    }


}