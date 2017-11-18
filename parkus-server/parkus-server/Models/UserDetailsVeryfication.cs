using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace parkus_server.Models
{
    public static class UserDetailsVeryfication
    {
        public static bool CheckForEmptValues(UserItem user)
        {
            return (user.Name == null || user.Name == "" || user.Password == null || user.Password == "" ||
                    user.Username == null || user.Username == "" || user.Surname == null || user.Surname == "");
        }

        public static bool VerifyCredentials(string username, string password)
        {
            string passwordHashed = Sha256Hashing.Hash(password);

            using (var database = new DatabaseMainEntities())
            {
                return database.User.Any(u => u.Username == username &&
                                               u.Password == passwordHashed);
            }
        }

        public static bool CheckUserExistance(UserItem user)
        {
            return CheckUserExistance(user.Username);
        }

        public static bool CheckUserExistance(string username)
        {
            using (var database = new DatabaseMainEntities())
            {
                return database.User.Any(u => u.Username == username);
            }
        }
    }
}