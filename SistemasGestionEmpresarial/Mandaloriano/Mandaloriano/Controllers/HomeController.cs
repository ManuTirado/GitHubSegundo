using DAL;
using Entidades;
using Mandaloriano.Models.VM;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics;

namespace Mandaloriano.Controllers
{
    public class HomeController : Controller
    {
        public static List<clsMision> listadoMisiones = clsListadosMisiones.obtenerListadoCompleto();


        public IActionResult Index()
        {
            return View(new clsListadoMisionesVM(listadoMisiones, new clsMision()));
        }

        [HttpPost]
        public IActionResult Index(int mision)
        {
            clsMision misionAmostrar = clsManejadoraMision.obtenerMisionPorId(listadoMisiones, mision); //Obtengo la misión correspondiente al Id seleccionado en la vista
            return View(new clsListadoMisionesVM(listadoMisiones, misionAmostrar));
        }
    }
}