using parkus_server.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace parkus_server.Controllers
{
    public class ParkingPointsController : ApiController
    {
        public List<ParkingPointItem> Get()
        {
            using (var database = new DatabaseMainEntities())
            {
                return database.ParkingPoints
                    .Select(pp => new ParkingPointItem
                    {
                        Id = pp.Id,
                        Longitude = pp.Longitude,
                        Latitude = pp.Latitude,
                        Photo = pp.Photo,
                        CreatedOn = pp.CreatedOn
                    }).ToList();
            }
        }

        public void Put([FromBody] ParkingPointItem parkingPointItemNew)
        {
            using (var database = new DatabaseMainEntities())
            {
                DateTime currentDate = DateTime.Now;

                var parkingPoint = new ParkingPoints
                {
                    Longitude = parkingPointItemNew.Longitude,
                    Latitude = parkingPointItemNew.Latitude,
                    Photo = parkingPointItemNew.Photo,
                    CreatedOn = currentDate
                };

                database.ParkingPoints.Add(parkingPoint);
                database.SaveChanges();
            }
        }
    }
}
