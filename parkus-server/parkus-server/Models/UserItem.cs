using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace parkus_server.Models
{
    public class UserItem
    {
        public string Username { get; set; }
        public string Password { get; set; }
        public string Name { get; set; }
        public string Surname { get; set; }
        public int Points { get; set; }
    }
}