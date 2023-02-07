using Examen_Entidades;
using Microsoft.AspNetCore.Mvc;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace ExamenAPI.Controllers.API
{
    [Route("api/[controller]")]
    [ApiController]
    public class DepartamentoController : ControllerBase
    {
        // GET: api/<DepartamentoController>
        [HttpGet]
        public List<clsDepartamento> Get()
        {
            return Examen_BL.Listados.clsListadosDepartamentosBL.obtenerListadoCompletoBL();
        }
    }
}
