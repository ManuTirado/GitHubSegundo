using CRUD_Personas_ASP.Models;
using CRUD_Personas_ASP.Models.VM;
using CRUD_Personas_Entidades;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Data.SqlClient;
using System.Collections.ObjectModel;

namespace CRUD_Personas_ASP.Controllers
{
    public class PersonaController : Controller
    {
        public IActionResult ListadoPersona(string busquedaUsuario)
        {
            try
            {
                List<clsPersonaNombreDepartmento> personasConNombreDept = new List<clsPersonaNombreDepartmento>();
                List<clsPersona> personas = CRUD_Personas_BL.Listados.clsListadosPersonasBL.ListadoCompletoPersonasBL();
                List<clsDepartamento> departamentos = CRUD_Personas_BL.Listados.clsListadosDepartamentosBL.ListadoCompletoDepartamentosBL();
                foreach (var persona in personas)
                {
                    if (!String.IsNullOrEmpty(busquedaUsuario))
                    {
                        if (persona.Nombre.ToLower().Contains(busquedaUsuario.ToLower()))
                        {
                            clsPersonaNombreDepartmento personaNombreDepartamento = new clsPersonaNombreDepartmento(persona);
                            personaNombreDepartamento.nombreDepartamento = departamentos.Find(x => x.ID == persona.IDDepartamento).Nombre;
                            personasConNombreDept.Add(personaNombreDepartamento);
                        }
                    } else
                    {
                        clsPersonaNombreDepartmento personaNombreDepartamento = new clsPersonaNombreDepartmento(persona);
                        personaNombreDepartamento.nombreDepartamento = departamentos.Find(x => x.ID == persona.IDDepartamento).Nombre;
                        personasConNombreDept.Add(personaNombreDepartamento);
                    }
                      
                }
                return View(personasConNombreDept);
            }
            catch (SqlException e)
            {
                return View("Error", "Error al intentar obtener el listado de personas, inténtelo de nuevo más tarde");
            }
        }


        public IActionResult EditarPersona(clsPersona persona)
        {
            try
            {
                vmEditarInsertarPersona vmEditarInsertarPersona = new vmEditarInsertarPersona(persona);
                return View(vmEditarInsertarPersona);
            }
            catch (SqlException e)
            {
                return View("Error", "Error al intentar obtener los departamentos, inténtelo de nuevo más tarde");
            }
        }
        [HttpPost]
        [ActionName("EditarPersona")]
        public IActionResult EditarPersonaGuardar(clsPersona Persona)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    CRUD_Personas_BL.Manejadoras.clsManejadoraPersonasBL.EditarPersonaBL(Persona.ID, Persona);
                    return RedirectToAction("ListadoPersona");
                }
                catch (Exception e)
                {
                    return View("Error", "Error al intentar editar la persona, inténtelo de nuevo más tarde");
                }
            }
            else
            {
                return View(new vmEditarInsertarPersona(Persona));
            }
        }

        public IActionResult InsertarPersona()
        {
            vmEditarInsertarPersona vmEditarInsertarPersona = new vmEditarInsertarPersona();
            return View(vmEditarInsertarPersona);
        }
        [HttpPost]
        public IActionResult InsertarPersona(clsPersona Persona)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    CRUD_Personas_BL.Manejadoras.clsManejadoraPersonasBL.InsertarPersonaBL(Persona);
                    return RedirectToAction("ListadoPersona");
                }
                catch (Exception e)
                {
                    return View("Error", "Error al intentar insertar la persona, inténtelo de nuevo más tarde");
                }
            }
            else
            {
                return View(new vmEditarInsertarPersona(Persona));
            }
        }

        public IActionResult BorrarPersona(clsPersona persona)
        {
            try
            {
                CRUD_Personas_BL.Manejadoras.clsManejadoraPersonasBL.BorrarPersonaBL(persona.ID);
                return RedirectToAction("ListadoPersona");
            }
            catch (Exception e)
            {
                return View("Error", "Error al intentar borrar la persona, inténtelo de nuevo más tarde");
            }
        }

    }
}
