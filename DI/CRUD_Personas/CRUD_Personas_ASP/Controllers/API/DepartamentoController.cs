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
    public class DepartamentoController : ControllerBase
    {
        // GET: api/<DepartamentoController>
        [HttpGet]
        public List<clsDepartamento> Get()
        {
            return CRUD_Personas_BL.Listados.clsListadosDepartamentosBL.ListadoCompletoDepartamentosBL();
        }

        // GET api/<DepartamentoController>/5
        [HttpGet("{id}")]
        public clsDepartamento Get(int id)
        {
            return CRUD_Personas_BL.Manejadoras.clsManejadoraDepartamentosBL.LeerDepartamentoBL(id);
        }

        // POST api/<DepartamentoController>
        [HttpPost]
        public IActionResult Post([FromBody] clsDepartamento value)
        {
            IActionResult result = null;
            int numeroFilasAfectadas = 0;
            try
            {
                numeroFilasAfectadas = clsManejadoraDepartamentosBL.InsertarDepartamentoBL(value);
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

        // PUT api/<DepartamentoController>/5
        [HttpPut("{id}")]
        public IActionResult Put(int id, [FromBody] clsDepartamento value)
        {
            IActionResult result = null;
            int numeroFilasAfectadas = 0;
            try
            {
                numeroFilasAfectadas = clsManejadoraDepartamentosBL.EditarDepartamentoBL(id, value);
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

        // DELETE api/<DepartamentoController>/5
        [HttpDelete("{id}")]
        public IActionResult Delete(int id)
        {
            IActionResult result = null;
            int numeroFilasAfectadas = 0;
            try
            {
                numeroFilasAfectadas = clsManejadoraDepartamentosBL.BorrarDepartamentoBL(id);
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
