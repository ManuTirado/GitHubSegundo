using Microsoft.AspNetCore.Mvc;

namespace _06_HolaMundo_ASP_MVC_Ej4.Controllers
{
    public class HomeController : Controller
    {
        public ViewResult Index()
        {
            return View();
        }
    }
}