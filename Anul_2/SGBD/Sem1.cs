using System;
using System.Data.SqlClient;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            string sqlConnectionString = "Server=DESKTOP-HD16HCH;Database=SGBD;Integrated Security=true";
            try
            {
                using (SqlConnection connection = new SqlConnection(sqlConnectionString))
                {
                    connection.Open();
                    Console.WriteLine($"Starea conexiunii: {connection.State}");
                    string desc1 = "ursulet de plush";
                    string posesor1 = "nimeni";
                    float pret1 = 400.99F;

                    string desc2 = "grasa de Cotnari";
                    string posesor2 = "Ardeal";
                    float pret2 = 13.49F;

                    SqlCommand insertCommand = new SqlCommand("INSERT INTO Cadouri (descriere, posesor, pret) " +
                        "VALUES(@desc1, @posesor1, @pret1), (@desc2, @posesor2, @pret2);", connection);
                    insertCommand.Parameters.AddWithValue("@desc1", desc1);
                    insertCommand.Parameters.AddWithValue("@posesor1", posesor1);
                    insertCommand.Parameters.AddWithValue("@pret1", pret1);
                    insertCommand.Parameters.AddWithValue("@desc2", desc2);
                    insertCommand.Parameters.AddWithValue("@posesor2", posesor2);
                    insertCommand.Parameters.AddWithValue("@pret2", pret2);
                    int x = insertCommand.ExecuteNonQuery();
                    Console.WriteLine($"Au fost inserate {x} inregistrari.\n");

                    SqlCommand updateCommand = new SqlCommand("UPDATE Cadouri SET descriere=@descNew WHERE posesor=@posesor;", connection);
                    string descNew = "papushe";
                    updateCommand.Parameters.AddWithValue("@descNew", descNew);
                    updateCommand.Parameters.AddWithValue("@posesor", posesor1);
                    x = updateCommand.ExecuteNonQuery();
                    Console.WriteLine($"Au fost modificate {x} inregistrari.\n");

                    SqlCommand deleteAllCmd = new SqlCommand("DELETE FROM Cadouri;", connection);
                    x = deleteAllCmd.ExecuteNonQuery();
                    Console.WriteLine($"Au fost sterse {x} inregistrari.\n");
                }
            }
            catch(Exception e)
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine($"Mesajul erorii\n{e.Message}");
            }
            Console.ReadKey();
        }
    }
}
