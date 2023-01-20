using Entidades;
using Microsoft.AspNetCore.SignalR;

namespace CirculitosJuguetones_ASP.Hubs
{
    public class CirculitosJuguetones_HUB : Hub
    {
        /// <summary>
        /// Llama al método 'DibujarCirculo' del cliente, pasándole un objeto círculo como parámetro
        /// </summary>
        /// <param name="circulo">objeto de la clase clsCirculo</param>
        /// <returns></returns>
        public async Task EnviarCirculo(clsCirculo circulo)
        {
            await Clients.Others.SendAsync("DibujarCirculo", circulo);
        }
    }
}
