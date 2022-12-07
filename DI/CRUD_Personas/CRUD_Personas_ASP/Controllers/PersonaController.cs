using CRUD_Personas_ASP.Models.VM;
using CRUD_Personas_Entidades;
using Microsoft.AspNetCore.Mvc;

namespace CRUD_Personas_ASP.Controllers
{
    public class PersonaController : Controller
    {
        public IActionResult ListadoPersona()
        {
            return View();
        }
        /*
        [HttpPost]
        public IActionResult ListadoPersona(string busquedaUsuario)
        {
            return View(vmListadoPersonas);
        }
       

        public IActionResult EditarPersona(clsPersona persona)
        {
            return View(new vmEditarInsertarPersona(new clsPersona()));
        }
        [HttpPost]
        public IActionResult EditarPersona(vmEditarInsertarPersona vmEditarInsertarPersona)
        {
            return View(vmEditarInsertarPersona);
        }

        public IActionResult InsertarPersona()
        {
            return View(vmEditarInsertarPersona);
        }
        [HttpPost]
        public IActionResult InsertarPersona(vmEditarInsertarPersona)
        {
            return View();
        }

        public IActionResult BorrarPersona(clsPersona)
        {
            return View();
        }
         */
    }
}
