using System;
using System.Collections.Generic;
using System.Text;

namespace Model
{
    [Serializable]
    public class User
    {
        string username;
        string password;

        public User(string username, string password)
        {
            Username = username;
            Password = password;
        }

        public string Username { get => username; set => username = value; }
        public string Password { get => password; set => password = value; }
    }
}
