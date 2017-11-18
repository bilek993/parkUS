using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Web;

namespace parkus_server.Models
{
    public static class Sha256Hashing
    {
        public static string Hash(string valueToBeHashed)
        {
            System.Security.Cryptography.SHA256Managed shaManaged = new System.Security.Cryptography.SHA256Managed();
            StringBuilder hash = new StringBuilder();
            byte[] crypto = shaManaged.ComputeHash(Encoding.UTF8.GetBytes(valueToBeHashed), 0, 
                Encoding.UTF8.GetByteCount(valueToBeHashed));

            foreach (var theByte in crypto)
            {
                hash.Append(theByte.ToString("x2"));
            }
            return hash.ToString();
        }
    }
}