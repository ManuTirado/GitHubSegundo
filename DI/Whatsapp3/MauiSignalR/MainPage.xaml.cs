

using Microsoft.AspNetCore.SignalR.Client;

namespace MauiSignalR
{
    public partial class MainPage : ContentPage
    {
        private readonly HubConnection _connection;

        public MainPage()
        {
            InitializeComponent();

            _connection = new HubConnectionBuilder().withUrl("http://192.168.0.166:5046").Build();

            _connection.On<string>("MessageReceived", (message) =>
            {
                chatMessages.Text += $"{Environment.NewLine}{message}";
            });

            Task.Run(() =>
            {
                Dispatcher.Dispatch(async () =>
                await _connection.StartAsync());
            });
        }

        private async void OnCounterClicked(object sender, EventArgs e)
        {
            await _connection.InvokeCoreAsync("SendMessage", args: new[] { myChatMessage.Text });

            myChatMessage.Text = String.Empty;
        }
    }
}