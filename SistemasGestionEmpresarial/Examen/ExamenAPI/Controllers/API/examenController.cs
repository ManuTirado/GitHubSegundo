using Examen_Entidades;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Data.SqlClient;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace ExamenAPI.Controllers.API
{
    [Route("api/[controller]")]
    [ApiController]
    public class examenController : ControllerBase
    {
        // Leer
        // GET: api/<examenController>
        [HttpGet]
        public List<clsPersona> Get()
        {
            return Examen_BL.Listados.clsListadosPersonasBL.obtenerListadoCompletoBL();
        }

        // Leer (id)
        // GET api/<examenController>/5
        [HttpGet("{id}")]
        public clsPersona Get(int id)
        {
            return Examen_BL.Manejadoras.clsManejadoraPersonaBL.leerPersonaBL(id);
        }

        // Insertar
        // POST api/<examenController>
        [HttpPost]
        public IActionResult Post(clsPersona persona)
        {
            IActionResult result = null;
            int numeroFilasAfectadas = 0;
            try
            {
                numeroFilasAfectadas = Examen_BL.Manejadoras.clsManejadoraPersonaBL.insertarPersonaBL(persona);
            }
            catch (SqlException)
            {
                result = BadRequest();
            }
            if (numeroFilasAfectadas == 0)
            {
                result = NoContent();
            }
            else
            {
                result = Ok();
            }
            return result;

        }

        // Actualizar (id)
        // PUT api/<examenController>/5
        [HttpPut("{id}")]
        public void Put(int id, [FromBody] string value)
        {
        }

        // Borrar (id)
        // DELETE api/<examenController>/5
        [HttpDelete("{id}")]
        public void Delete(int id)
        {
        }
    }
}
