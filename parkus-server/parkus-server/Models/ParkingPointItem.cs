using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace parkus_server.Models
{
    public class ParkingPointItem
    {
        public int Id { get; set; }
        public double? Longitude { get; set; }
        public double? Latitude { get; set; }
        public byte[] Photo { get; set; }
        public DateTime? CreatedOn { get; set; }
    }
}