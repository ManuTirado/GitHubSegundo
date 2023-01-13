using Entidades;
using Microsoft.AspNetCore.SignalR;

namespace CirculitosJuguetones_ASP.Hubs
{
    public class CirculitosJuguetones_HUB : Hub
    {
        public async Task EnviarCirculo(clsCirculo circulo)
        {
            await Clients.All.SendAsync("DibujarCirculo", circulo);
        }
    }
}
