using Microsoft.AspNetCore.SignalR;

namespace TresEnRayaSignalR.Hubs
{
    public class JuegoHub : Hub
    {
        public async Task EnviarMensaje(string user, string message)
        {
            await Clients.All.SendAsync("RecivirMensaje", user, message);
        }
    }
}
