using CRUD_Personas_ASP.Models;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics;

namespace CRUD_Personas_ASP.Controllers
{
    public class HomeController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }
    }
}