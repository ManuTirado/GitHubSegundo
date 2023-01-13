using _08_ASP.NET_MVC_Ej3.Models.VM;
using DAL;
using Entidades;
using Microsoft.AspNetCore.Mvc;

namespace _08_ASP.NET_MVC_Ej3.Controllers
{
    public class HomeController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }

        public IActionResult Editar()
        {
            List<clsDepartamento> departamentos = clsListadosDepartamentos.obtenerListadoCompleto();
            clsPersona persona = new clsPersona(1, "Manuel", "Tirado", "666554433", 1);
            clsEditarVM personaEditar = new clsEditarVM(persona, departamentos);
            return View(personaEditar);
        }

        [HttpPost]
        public IActionResult Editar(clsEditarVM personaEditada)
        {
            string nombreDept = clsManejadoraDepartamento.obtenerDepartamentoPorId(personaEditada.Persona.IdDepartamento).NombreDepartamento;
            clsPersonaEditadaVM personaEditadaVM = new clsPersonaEditadaVM(personaEditada.Persona, nombreDept);
            return View("PersonaModificada", personaEditadaVM);
        }
    }
}