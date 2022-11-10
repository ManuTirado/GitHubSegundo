using Entidades.DAL;
namespace Solares.Pages;

public partial class Galeria : ContentPage
{
	public Galeria()
	{
        InitializeComponent();

        List<string> fotos = clsObtenerFotosCita.obtenerFotosCita();
        foreach (string foto in fotos)
        {
            ImageButton image = new ImageButton();
            image.Source = foto;
            image.HeightRequest = 150;
            image.WidthRequest = 150;
            image.Clicked += async (o, e) => { await Navigation.PushAsync(new Pages.FotoAmpliada(foto)); };
            flexLayoutGaleria.Children.Add(image);
        }
    }

    public async void onImgBtnCamara (object sender, EventArgs e)
    {
        if (MediaPicker.Default.IsCaptureSupported)
        {
            FileResult photo = await MediaPicker.Default.CapturePhotoAsync();

            if (photo != null)
            {
                // save the file into local storage
                string localFilePath = Path.Combine(FileSystem.CacheDirectory, photo.FileName);

                using Stream sourceStream = await photo.OpenReadAsync();
                using FileStream localFileStream = File.OpenWrite(localFilePath);

                await sourceStream.CopyToAsync(localFileStream);
            }
        }
    }
}