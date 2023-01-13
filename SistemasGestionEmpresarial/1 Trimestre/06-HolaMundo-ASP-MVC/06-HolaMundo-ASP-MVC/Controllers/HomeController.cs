using Microsoft.AspNetCore.Mvc;

namespace _06_HolaMundo_ASP_MVC.Controllers
{
    public class HomeController : Controller
    {
        public string Index()
        {
            return "Hola mundo, desde el action Index del controlador";
        }

        public string Salva()
        {
            return "Hola Salva, desde el action Salva del controlador";
        }

        public ViewResult vistaHolaMundo()
        {
            return View();
        }
    }
}