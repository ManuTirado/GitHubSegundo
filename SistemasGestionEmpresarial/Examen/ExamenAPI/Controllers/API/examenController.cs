using Microsoft.AspNetCore.Mvc;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace ExamenAPI.Controllers.API
{
    [Route("api/[controller]")]
    [ApiController]
    public class examenController : ControllerBase
    {
        // GET: api/<examenController>
        [HttpGet]
        public IEnumerable<string> Get()
        {
            return new string[] { "value1", "value2" };
        }

        // GET api/<examenController>/5
        [HttpGet("{id}")]
        public string Get(int id)
        {
            return "value";
        }

        // POST api/<examenController>
        [HttpPost]
        public void Post([FromBody] string value)
        {
        }

        // PUT api/<examenController>/5
        [HttpPut("{id}")]
        public void Put(int id, [FromBody] string value)
        {
        }

        // DELETE api/<examenController>/5
        [HttpDelete("{id}")]
        public void Delete(int id)
        {
        }
    }
}
