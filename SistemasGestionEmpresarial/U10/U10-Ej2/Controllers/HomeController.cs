using Microsoft.AspNetCore.Mvc;
using System.Diagnostics;
using U10_Ej2.Models;
using U10_Ej2.Models.Entidades;

namespace U10_Ej2.Controllers
{
    public class HomeController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }

        public ActionResult ListadoPersonas()
        {
            List<clsPersona> personas = U10_Ej2.Models.DAL.clsListadosPersonas.obtenerListadoCompleto();
            return View(personas);
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