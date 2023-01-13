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

        public IActionResult Editar()
        {
            clsPersona personaEditar = new clsPersona(1, "Manuel", "Tirado");
            return View(personaEditar);
        }

        [HttpPost]
        public IActionResult Editar(clsPersona personaEditada)
        {
            if (!ModelState.IsValid)
            {
                return View(personaEditada);
            }
            else
            {
                return View("PersonaModificada", personaEditada);
            } 
        }
    }
}