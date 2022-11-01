using Entidades;
using Microsoft.AspNetCore.Mvc;

namespace _08_ASP.NET_MVC_Ej3.Controllers
{
    public class HomeController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }

        public IActionResult Editar(clsPersona persona)
        {
            return View();
        }

        [HttpPost]
        public IActionResult Editar()
        {
            return View("PersonaModificada");
        }
    }
}