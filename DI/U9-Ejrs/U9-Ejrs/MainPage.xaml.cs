namespace U9_Ejrs_2;

public partial class MainPage : ContentPage
{

	public MainPage()
	{
		InitializeComponent();
	}

	private void onTxtChanged(object sender, TextChangedEventArgs e)
	{
		if (!int.TryParse(e.NewTextValue, out int numericValue))
		{
			txtTamano.Text = e.OldTextValue;
		}
    }
}

