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
    public class ParkingPointsController : ApiController
    {
        [BasicAuthentication]
        public List<ParkingPointItem> Get()
        {
            using (var database = new DatabaseMainEntities())
            {
                string username = Thread.CurrentPrincipal.Identity.Name;
                return database.ParkingPoints
                    .Where(us => us.User.Username != username)
                    .Select(pp => new ParkingPointItem
                    {
                        Id = pp.Id,
                        Creator = pp.User.Name + " " + pp.User.Surname,
                        Longitude = pp.Longitude,
                        Latitude = pp.Latitude,
                        Photo = pp.Photo,
                        CreatedOn = pp.CreatedOn
                    }).ToList();
            }
        }

        [BasicAuthentication]
        public void Put([FromBody] ParkingPointItem parkingPointItemNew)
        {
            using (var database = new DatabaseMainEntities())
            {
                DateTime currentDate = DateTime.Now;

                string username = Thread.CurrentPrincipal.Identity.Name;
                User user = database.User.FirstOrDefault(u => u.Username == username);
                user.Points += 1;

                var parkingPoint = new ParkingPoints
                {
                    Longitude = parkingPointItemNew.Longitude,
                    Latitude = parkingPointItemNew.Latitude,
                    Photo = parkingPointItemNew.Photo,
                    CreatedOn = currentDate,
                    User = user
                };

                database.ParkingPoints.Add(parkingPoint);
                database.SaveChanges();
            }
        }
    }
}
