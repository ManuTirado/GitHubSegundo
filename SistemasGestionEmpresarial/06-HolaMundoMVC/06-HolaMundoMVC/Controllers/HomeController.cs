using Microsoft.AspNetCore.Mvc;

namespace _06_HolaMundoMVC.Controllers
{
    public class HomeController : Controller
    {
        public String Index()
        {
            return "Hola Mundo desde el action index del controlador home";
        }
        public String Salva()
        {
            return "Hola Salva, ¿Caramelo? 🥵🔥🔥, desde el action salva del controlador home";
        }
        public ViewResult Kiko()
        {
            return View();
        }
    }
}
