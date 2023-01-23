using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ApiAsincrona_DAL
{
    public class apiURI
    {
        private static string uri_base = "https://elcruddefresco.azurewebsites.net/api/";

        public static string Uri_Base { get { return uri_base; } }
    }
}
