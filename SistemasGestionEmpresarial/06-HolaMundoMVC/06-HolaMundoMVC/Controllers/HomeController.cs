using Microsoft.AspNetCore.Mvc;

namespace _06_HolaMundoMVC.Controllers
{
    public class HomeController : Controller
    {
        public String Index()
        {
            return "Hola Mundo desde el controlador";
        }
        public String Salva()
        {
            return "Hola Salva 🥵🔥🔥, desde el controlador";
        }
        public ViewResult Kiko()
        {
            return View();
        }
    }
}
