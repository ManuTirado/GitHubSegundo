using _08_Layouts.Pages;

namespace _08_Layouts;

public partial class App : Application
{
	public App()
	{
		InitializeComponent();

		MainPage = new NavigationPage(new MainPage());
	}
}
