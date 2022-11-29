using Microsoft.AspNetCore.Mvc;

namespace CRUD_Personas_ASP.Controllers
{
    public class PersonaController : Controller
    {
        public IActionResult ListadoPersona()
        {
            return View();
        }
    }
}
