using CRUD_Personas_Entidades;
using Newtonsoft.Json;
using System.Net.Http;

namespace ApiAsincrona_DAL
{
    public class clsListadosPersonasDAL
    {
        public static async Task<List<clsPersona>> ListadoCompletoPersonasDAL()
        {
            //Pido la cadena de la Uri al método estático
            string miCadenaUrl = apiURI.Uri_Base;
            Uri miUri = new Uri($"{miCadenaUrl}Personas");
            List<clsPersona> listaPersonas = new List<clsPersona>();
            HttpClient miHttpClient;
            HttpResponseMessage miCodigoRespuesta;
            string textoJsonRespuesta;
            //Instanciamos el cliente Http
            miHttpClient = new HttpClient();
            try
            {
                miCodigoRespuesta = await miHttpClient.GetAsync(miUri);
                if (miCodigoRespuesta.IsSuccessStatusCode)
                {
                    textoJsonRespuesta = await miHttpClient.GetStringAsync(miUri);
                    miHttpClient.Dispose();
                    //JsonConvert necesita using Newtonsoft.Json;
                    //Es el paquete Nuget de Newtonsoft
                    listaPersonas = JsonConvert.DeserializeObject<List<clsPersona>>(textoJsonRespuesta);
                }
            }
            catch (Exception ex)
            {
                throw ex;
            }
            return listaPersonas;
        }
    }
}
