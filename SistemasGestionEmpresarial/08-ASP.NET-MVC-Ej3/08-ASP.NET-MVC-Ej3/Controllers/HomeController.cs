using Entidades;
using Microsoft.AspNetCore.Mvc;

namespace _08_ASP.NET_MVC_Ej3.Controllers
{
    public class HomeController : Controller
    {

        public static clsPersona personaEditar = new clsPersona(1, "Manuel", "Tirado");


        public IActionResult Index()
        {
            return View();
        }

        public IActionResult Editar()
        {
            return View(personaEditar);
        }

        [HttpPost]
        public IActionResult Editar(clsPersona personaEditada)
        {
            personaEditar = personaEditada;

            return View("PersonaModificada", personaEditar);
        }
    }
}