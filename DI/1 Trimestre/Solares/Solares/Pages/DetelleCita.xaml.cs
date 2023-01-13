using Entidades;

namespace Solares.Pages;

public partial class DetelleCita : ContentPage
{
    private clsCita detalleCita;
	public DetelleCita(clsCita cita)
	{
		InitializeComponent();
        detalleCita = cita;

        lblTitleView.Text = detalleCita.NombreCliente;
        lblHora.Text = detalleCita.getHora;
        lblDireccion.Text = detalleCita.Direccion;
        lblTelefono.Text = detalleCita.Telefono;
        lblDistancia.Text = string.Format("{0:f2}Km", detalleCita.Distancia);

        txtObservaciones.Text = detalleCita.Observaciones;
        actualizarColorApta();
    }

	public async void onImgBtnGaleria (object sender, EventArgs e)
	{
        await Navigation.PushAsync(new Pages.Galeria());
	}

    public void onImgBtnApta(object sender, EventArgs e)
    {
        detalleCita.IsApta = (detalleCita.IsApta ? false : true);
        actualizarColorApta();
    }

    private void actualizarColorApta()
    {
        ImgBtnApta.BackgroundColor = (detalleCita.IsApta ? Colors.ForestGreen : Colors.IndianRed);
    }

    public async void onImgBtnGuardar(object sender, EventArgs e)
    {
        detalleCita.Observaciones = txtObservaciones.Text;
        await DisplayAlert("Guardado", "Datos guardados correctamente", "OK");
        await Navigation.PopAsync();
    }
}