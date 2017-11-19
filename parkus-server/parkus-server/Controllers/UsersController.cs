using parkus_server.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace parkus_server.Controllers
{
    public class UsersController : ApiController
    {
        public bool Put([FromBody] UserItem userNew)
        {
            using (var database = new DatabaseMainEntities())
            {
                if (UserDetailsVeryfication.CheckForEmptValues(userNew) ||
                    UserDetailsVeryfication.CheckUserExistance(userNew))
                {
                    return false;
                }

                string hashedPassword = Sha256Hashing.Hash(userNew.Password);
                var user = new User
                {
                    Username = userNew.Username,
                    Password = hashedPassword,
                    Name = userNew.Name,
                    Surname = userNew.Surname,
                    Points = Points.STARTING_POINTS
                };

                database.User.Add(user);
                database.SaveChanges();
            }

            return true;
        }

        public bool Post([FromBody] UserItem user)
        {
            return UserDetailsVeryfication.VerifyCredentials(user);
        }
    }
}
