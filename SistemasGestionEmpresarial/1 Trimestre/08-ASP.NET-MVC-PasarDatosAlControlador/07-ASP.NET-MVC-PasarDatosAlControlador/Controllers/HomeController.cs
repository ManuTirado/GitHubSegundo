using _07_ASP.NET_MVC_PasarDatosAlControlador.Models;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics;

namespace _07_ASP.NET_MVC_PasarDatosAlControlador.Controllers
{
    public class HomeController : Controller
    {
        #region Ej1
        public IActionResult Index()
        {
            return View();
        }

        public IActionResult Saludo(String nombre)
        {
            ViewBag.nombre = nombre;
            return View();
        }
        #endregion

        #region Ej2
        public IActionResult Index2()
        {
            return View();
        }

        [HttpPost]
        public IActionResult Index2(String nombre)
        {
            ViewBag.nombre = nombre;
            return View("Saludo");
        }
        #endregion

        #region Ej3

        #endregion
    }
}