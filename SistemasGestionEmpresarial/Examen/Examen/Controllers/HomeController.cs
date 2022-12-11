using Microsoft.AspNetCore.Mvc;
using System.Diagnostics;

namespace Examen.Controllers
{
    public class HomeController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }
    }
}