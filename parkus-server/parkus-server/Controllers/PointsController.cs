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

        [BasicAuthentication]
        public void Post([FromBody] int parkingPointId)
        {
            using (var database = new DatabaseMainEntities())
            {
                string username = Thread.CurrentPrincipal.Identity.Name;
                database.User.FirstOrDefault(u => u.Username == username).Points -= 10;
                database.ParkingPoints.FirstOrDefault(pp => pp.Id == parkingPointId).User.Points += 9;

                ParkingPoints point = database.ParkingPoints.FirstOrDefault(pp => pp.Id == parkingPointId);
                database.ParkingPoints.Remove(point);
                database.SaveChanges();
            }
        }
    }
}
