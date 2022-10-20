using _07_ASP.NET_MVC.Models;
using _07_ASP.NET_MVC.Models.DAL;
using Microsoft.AspNetCore.Mvc;

namespace _07_ASP.NET_MVC.Controllers
{
    public class HomeController : Controller
    {


        public ActionResult Index()
        {
            clsPersona persona = new clsPersona("Manuel", "Tirado");

            int hora = DateTime.Now.Hour;
            string saludo;
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

        public ActionResult ListadoPersonas()
        {
            return View(clsListadoPersonasDAL.obtenerListadoCompletoPersonas());
        }

        public ActionResult EditarPersona()
        {
            return View();
        }

    }
}