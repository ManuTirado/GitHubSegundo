using Examen_ASP.Models.VM;
using Examen_BL.Listados;
using Examen_Entidades;
using Microsoft.AspNetCore.Mvc;

namespace Examen_ASP.Controllers
{
    public class DepartamentosController : Controller
    {
        public IActionResult ListadoDepartamentos()
        {
            try
            {
                vmListadoDepartamentos vmListadoDepartamentos = new vmListadoDepartamentos();
                return View(vmListadoDepartamentos);
            }
            catch (Exception e)
            {
                ViewBag.Error = "Ha ocurrido un error, por favor inténtelo de nuevo más tarde.";
                return View(new vmListadoDepartamentos());
            }
        }

        public IActionResult ConfirmarBorrado(clsDepartamento departamento)
        {
            try
            {
                vmConfirmarBorrado vmConfirmarBorrado = new vmConfirmarBorrado(departamento);
                return View(vmConfirmarBorrado);
            }
            catch (Exception e)
            {
                ViewBag.Error = "Ha ocurrido un error ;(. Inténtelo de nuevo más tarde";
                return View(new vmConfirmarBorrado());
            }
        }
        [HttpPost]
        [ActionName("ConfirmarBorrado")]
        public IActionResult ConfirmarBorradoBorrar(clsDepartamento departamento)
        {
            vmConfirmarBorrado vmConfirmarBorrado = new vmConfirmarBorrado();
            try
            {
                List<clsPersona> personas = Examen_BL.Listados.clsListadosPersonasBL.obtenerPersonasDeDepartamentoBL(departamento.ID);
                int numPersonasEliminadas = 0;
                foreach (var persona in personas)
                {
                    if (Examen_BL.Manejadoras.clsManejadoraPersonaBL.eliminarPersonaBL(persona.ID) == 1)
                    {
                        numPersonasEliminadas++;
                    }
                }
                Examen_BL.Manejadoras.clsManejadoraDepartamentoBL.eliminarDepartamentoBL(departamento.ID);
                vmConfirmarBorrado.NumPersonasEnDept = numPersonasEliminadas;
                vmConfirmarBorrado.Departamento = departamento;
                vmConfirmarBorrado.Borrado = true;
                return View(vmConfirmarBorrado);
            }
            catch (Exception e)
            {
                ViewBag.Error = "Ha ocurrido un error ;(. Inténtelo de nuevo más tarde";
                return View(vmConfirmarBorrado);
            }
        }
    }
}
