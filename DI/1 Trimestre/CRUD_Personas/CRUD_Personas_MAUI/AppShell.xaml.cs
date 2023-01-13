using CRUD_Personas_MAUI.Paginas;

namespace CRUD_Personas_MAUI;

public partial class AppShell : Shell
{
	public AppShell()
	{
		InitializeComponent();
		Routing.RegisterRoute("DetallesPersona", typeof(DetallesPersonasPage));
		Routing.RegisterRoute("DetallesDepartamento", typeof(DetallesDepartamentoPage));
	}
}
