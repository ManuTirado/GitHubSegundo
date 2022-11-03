namespace U9_Ejrs;

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
			entry.Text = e.OldTextValue;
		}
    }
}

