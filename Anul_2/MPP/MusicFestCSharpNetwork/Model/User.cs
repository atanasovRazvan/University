using System;

namespace Model
{
    [Serializable]
    public class User
    {
        public string Username { get; set;}
        public string Password { get; set; }

        public User(string username, string password)
        {
            Username = username;
            Password = password;
        }

        public override string ToString()
        {
            return Username + " " + Password;
        }

        protected bool Equals(User other)
        {
            return Username == other.Username && Password == other.Password;
        }

        public override int GetHashCode()
        {
            unchecked
            {
                return ((Username != null ? Username.GetHashCode() : 0) * 397) ^ (Password != null ? Password.GetHashCode() : 0);
            }
        }
    }
}