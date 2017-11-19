using parkus_server.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading;
using System.Web.Http;

namespace parkus_server.Controllers
{
    public class PointsController : ApiController
    {
        [BasicAuthentication]
        public int Get()
        {
            using (var database = new DatabaseMainEntities())
            {
                string username = Thread.CurrentPrincipal.Identity.Name;
                User user = database.User.FirstOrDefault(u => u.Username == username);
                return user.Points ?? 0;
            }
        }
    }
}
