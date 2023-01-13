using CRUD_Personas_BL.Manejadoras;
using CRUD_Personas_Entidades;
using Microsoft.AspNetCore.Mvc;
using System.Web.Http;
using FromBodyAttribute = Microsoft.AspNetCore.Mvc.FromBodyAttribute;
using HttpDeleteAttribute = Microsoft.AspNetCore.Mvc.HttpDeleteAttribute;
using HttpGetAttribute = Microsoft.AspNetCore.Mvc.HttpGetAttribute;
using HttpPostAttribute = Microsoft.AspNetCore.Mvc.HttpPostAttribute;
using HttpPutAttribute = Microsoft.AspNetCore.Mvc.HttpPutAttribute;
using RouteAttribute = Microsoft.AspNetCore.Mvc.RouteAttribute;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace CRUD_Personas_ASP.Controllers.API
{
    [Route("api/[controller]")]
    [ApiController]
    public class PersonaController : ControllerBase
    {
        // GET: api/<PersonaController>
        [HttpGet]
        public List<clsPersona> Get()
        {
            return CRUD_Personas_BL.Listados.clsListadosPersonasBL.ListadoCompletoPersonasBL();
        }

        // GET api/<PersonaController>/5
        [HttpGet("{id}")]
        public clsPersona Get(int id)
        {
            return CRUD_Personas_BL.Manejadoras.clsManejadoraPersonasBL.LeerPersonaBL(id);
        }

        // POST api/<PersonaController>
        [HttpPost]
        public IActionResult Post([FromBody] clsPersona value)
        {
            IActionResult result = null;
            int numeroFilasAfectadas = 0;
            try
            {
                numeroFilasAfectadas = clsManejadoraPersonasBL.InsertarPersonaBL(value);
            }
            catch (HttpResponseException e)
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

        // PUT api/<PersonaController>/5
        [HttpPut("{id}")]
        public IActionResult Put(int id, [FromBody] clsPersona value)
        {
            IActionResult result = null;
            int numeroFilasAfectadas = 0;
            try
            {
                numeroFilasAfectadas = clsManejadoraPersonasBL.EditarPersonaBL(id, value);
            }
            catch (HttpResponseException e)
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

        // DELETE api/<PersonaController>/5
        [HttpDelete("{id}")]
        public IActionResult Delete(int id)
        {
            IActionResult result = null;
            int numeroFilasAfectadas = 0;
            try
            {
                numeroFilasAfectadas = clsManejadoraPersonasBL.BorrarPersonaBL(id);
            }
            catch (HttpResponseException e)
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
    }
}
