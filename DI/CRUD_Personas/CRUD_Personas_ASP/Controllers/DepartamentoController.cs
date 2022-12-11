using CRUD_Personas_ASP.Models.VM;
using CRUD_Personas_Entidades;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;

namespace CRUD_Personas_ASP.Controllers
{
    public class DepartamentoController : Controller
    {

        public IActionResult ListadoDepartamento()
        {
            try
            {
                List<clsDepartamento> departamentos = CRUD_Personas_BL.Listados.clsListadosDepartamentosBL.ListadoCompletoDepartamentosBL();
                return View(departamentos);
            }
            catch (Exception e)
            {
                return View("Error", "Error al intentar obtener el listado de departamentos, inténtelo de nuevo más tarde");
            }
        }

        public IActionResult EditarDepartamento(clsDepartamento departamento)
        {
            return View(departamento);
        }

        [HttpPost]
        [ActionName("EditarDepartamento")]
        public IActionResult EditarDepartamentoGuardar(clsDepartamento departamento)
        {
            if(ModelState.IsValid)
            {
                try
                {
                    CRUD_Personas_BL.Manejadoras.clsManejadoraDepartamentosBL.EditarDepartamentoBL(departamento.ID, departamento);
                    return RedirectToAction("ListadoDepartamento");
                }
                catch (Exception e)
                {
                    return View("Error", "Error al intentar editar el departamento, inténtelo de nuevo más tarde");
                }
            } else
            {
                return View(departamento);
            }
        }


        public IActionResult InsertarDepartamento()
        {
            clsDepartamento departamento = new clsDepartamento();
            return View(departamento);
        }
        [HttpPost]
        public IActionResult InsertarDepartamento(clsDepartamento departamento)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    CRUD_Personas_BL.Manejadoras.clsManejadoraDepartamentosBL.InsertarDepartamentoBL(departamento);
                    return RedirectToAction("ListadoDepartamento");
                }
                catch (Exception e)
                {
                    return View("Error", "Error al intentar insertar el departamento, inténtelo de nuevo más tarde");
                }
            } else
            {
                return View(departamento);
            }
        }


        public IActionResult BorrarDepartamento(clsDepartamento departamento)
        {
            try
            {
                CRUD_Personas_BL.Manejadoras.clsManejadoraDepartamentosBL.BorrarDepartamentoBL(departamento.ID);
                return RedirectToAction("ListadoDepartamento");
            }
            catch (Exception e)
            {
                return View("Error", "Error al intentar borrar el departamento, inténtelo de nuevo más tarde");
            }
        }

    }
}
