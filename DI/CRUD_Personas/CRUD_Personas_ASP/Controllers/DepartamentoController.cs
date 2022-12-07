using CRUD_Personas_Entidades;
using Microsoft.AspNetCore.Mvc;

namespace CRUD_Personas_ASP.Controllers
{
    public class DepartamentoController : Controller
    {
        public IActionResult ListadoDepartamento()
        {
            return View(/*vmListadoDepartamentos*/);
        }
        [HttpPost]
        public IActionResult ListadoDepartamento(string busquedaUsuario)
        {
            return View(/*vmListadoDepartamentos*/);
        }

        public IActionResult EditarDepartamento(clsDepartamento departamento)
        {
            return View(departamento);
        }
        /*
        [HttpPost]
        public IActionResult EditarDepartamento(clsDepartamento departamento)
        {
            return View(departamento);
        }
        */

        public IActionResult InsertarDepartamento()
        {
            return View();
        }
        /*
        [HttpPost]
        public IActionResult InsertarDepartamento()
        {
            return View();
        }
        */

        public IActionResult BorrarDepartamento(clsDepartamento)
        {
            return View();
        }
    }
}
