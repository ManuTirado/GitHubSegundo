using Entidades;
using Entidades.DAL;
using Microsoft.Maui.Controls.Internals;

namespace Solares.Pages;

public partial class Citas : ContentPage
{
	public Citas()
	{
		InitializeComponent();
		List<clsCita> listadoCitas = clsListadoCitas.obtenerListadoCompletoCitas();
		listadoCitas.Sort((x, y) => x.Hora.CompareTo(y.Hora));
		ListViewCitas.ItemsSource = listadoCitas;
	}

    async void OnItemSelected(object sender, SelectedItemChangedEventArgs args)
    {
        clsCita cita = args.SelectedItem as clsCita;
        await Navigation.PushAsync(new Pages.DetelleCita(cita));
    }
}