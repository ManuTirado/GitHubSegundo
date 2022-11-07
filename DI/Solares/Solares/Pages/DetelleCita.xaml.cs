using Entidades;

namespace Solares.Pages;

public partial class DetelleCita : ContentPage
{
	public DetelleCita(clsCita cita)
	{
		InitializeComponent();
		lblDetalleCita.Text = cita.NombreCliente;

    }
}