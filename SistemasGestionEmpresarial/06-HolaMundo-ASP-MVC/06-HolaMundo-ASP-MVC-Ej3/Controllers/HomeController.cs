using _06_HolaMundo_ASP_MVC_Ej3.Models;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics;

namespace _06_HolaMundo_ASP_MVC_Ej3.Controllers
{
    public class HomeController : Controller
    {
        public ViewResult Index()
        {
            return View();
        }
    }
}