using Microsoft.AspNetCore.Mvc;

namespace _06_HolaMundo_ASP_MVC.Controllers
{
    public class ProductosController : Controller
    {
        public ViewResult ListadoProductos()
        {
            return View();
        }
    }
}
