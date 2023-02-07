using Examen_Entidades;
using Microsoft.AspNetCore.Mvc;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace ExamenAPI.Controllers.API
{
    [Route("api/[controller]")]
    [ApiController]
    public class PersonaController : ControllerBase
    {
        // GET: api/<PersonaController>
        [HttpGet]
        public List<clsPersona> Get()
        {
            return Examen_BL.Listados.clsListadosPersonasBL.obtenerListadoCompletoBL();
        }

        // PUT api/<PersonaController>/5
        [HttpPut("{id}")]
        public void Put(int id, [FromBody] clsPersona persona)
        {
            Examen_BL.Manejadoras.clsManejadoraPersonaBL.actualizarPersonaBL(id, persona);
        }
    }
}
