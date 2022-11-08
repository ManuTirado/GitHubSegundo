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
    }

	public void onImgBtnGaleria (object sender, EventArgs e)
	{

	}

    public void onImgBtnApta(object sender, EventArgs e)
    {
        if (detalleCita.isApta)
        {
            detalleCita.isApta = false;
            ImgBtnApta.BackgroundColor = Colors.IndianRed;
        }
        else
        {
            detalleCita.isApta = true;
            ImgBtnApta.BackgroundColor = Colors.ForestGreen;
        }
    }

    public void onImgBtnGuardar(object sender, EventArgs e)
    {

    }
}