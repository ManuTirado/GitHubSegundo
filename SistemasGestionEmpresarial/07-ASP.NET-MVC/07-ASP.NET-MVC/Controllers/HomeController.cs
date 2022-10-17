using _07_ASP.NET_MVC.Models;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics;

namespace _07_ASP.NET_MVC.Controllers
{
    public class HomeController : Controller
    {
        
        
        public ActionResult Index()
        {
            clsPersona persona = new clsPersona();
            persona.Nombre = "Manuel";
            persona.Apellido = "Tirado";

            int hora = DateTime.Now.Hour;
            String saludo;
            if (hora >= 6 && hora < 12)
            {
                saludo = "Buenos días";
            }
            else if (hora >= 12 && hora < 20)
            {
                saludo = "Buenas tardes";
            }
            else
            {
                saludo = "Buenas noches";
            }
            ViewData["Saludo"] = saludo;

            ViewBag.fecha = DateTime.Now.ToLongDateString();

            return View(persona);
        }

    }
}