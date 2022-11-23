using CRUD_Personas_DAL.Manejadoras;
using CRUD_Personas_Entidades;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CRUD_Personas_BL.Manejadoras
{
    public static class clsManejadoraPersonasBL
    {
        public static int EditarPersonaBL(int idPersona, clsPersona personaEditada)
        {
            return (clsManejadoraPersonasDAL.EditarPersonaDAL(idPersona, personaEditada));
        }

        public static int InsertarPersonaBL(clsPersona personaInsertar)
        {
            return (clsManejadoraPersonasDAL.InsertarPersonaDAL(personaInsertar));
        }

        public static int BorrarPersonaBL(int id)
        {
            return (clsManejadoraPersonasDAL.BorrarPersonaDAL(id));
        }
    }
}
