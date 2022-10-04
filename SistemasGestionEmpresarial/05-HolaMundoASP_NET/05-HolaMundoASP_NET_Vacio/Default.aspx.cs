using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Entidad_Persona;

public partial class _Default : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }

    protected void btnSaludar_Click(object sender, EventArgs e)
    {
        if (txtNombre.Text != null)
        {
            clsPersona persona = new clsPersona();
            persona.Nombre = txtNombre.Text;
            
        }
        else
        {

        }
    }
}