using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class _Default : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }

    protected void btnSaludar_Click(object sender, EventArgs e)
    {
        //clsPersona persona;

        if (!string.IsNullOrEmpty(txtNombre.Text))
        {
            //persona = new clsPersona();
            lblError.Visible = false;
            lblSaludo.Text = "¡Hola " + txtNombre.Text + "!";
            lblSaludo.Visible = true;
            
        }
        else
        {
            lblSaludo.Visible = false;
            lblError.Visible = true;
        }
    }
}