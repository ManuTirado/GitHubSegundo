using DAL;
using Entidades;
using Mandaloriano.Models.VM;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics;

namespace Mandaloriano.Controllers
{
    public class HomeController : Controller
    {
        public IActionResult Index()
        {
            List<clsMision> listadoMisiones = clsListadosMisiones.obtenerListadoCompleto();
            return View(new clsListadoMisionesVM(listadoMisiones, new clsMision()));
        }

        [HttpPost]
        public IActionResult Index(int misionSeleccionada)
        {
            List<clsMision> listadoMisiones = clsListadosMisiones.obtenerListadoCompleto();
            clsMision misionAmostrar = clsManejadoraMision.obtenerMisionPorId(listadoMisiones, misionSeleccionada); //Obtengo la misión correspondiente al Id seleccionado en la vista
            return View(new clsListadoMisionesVM(listadoMisiones, misionAmostrar));
        }
    }
}