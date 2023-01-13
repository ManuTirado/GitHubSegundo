using Microsoft.Data.SqlClient;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics;
using U10.Models;

namespace U10.Controllers
{
    public class HomeController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }

        [HttpPost]
        [ActionName("Index")]
        public IActionResult IndexPost()
        {
            SqlConnection miConexion = new SqlConnection();
            try
            {
                miConexion.ConnectionString 
                    = "server=dhurtado.database.windows.net;database=diegoDB;uid=fernando;pwd=Mandaloriano69;";
                miConexion.Open();
                ViewBag.estadoConexion = miConexion.State;
            }
            catch
            {
                // TO DO => 
            }
            finally
            {
                miConexion.Close();
            }
            return View();
        }

        private readonly ILogger<HomeController> _logger;

        public HomeController(ILogger<HomeController> logger)
        {
            _logger = logger;
        }

        public IActionResult Privacy()
        {
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}