using Microsoft.AspNetCore.SignalR.Client;
using Microsoft.Maui.Controls.Shapes;

namespace MauiSignalRSample;

public partial class MainPage : ContentPage
{
    private readonly HubConnection _connection;

    public MainPage()
    {
        InitializeComponent();

        _connection = new HubConnectionBuilder()
            .WithUrl("https://siganlrchat.azurewebsites.net/chatHub")
            .Build();

        _connection.On<string, string>("ReceiveMessage", (user, message) =>
        {
            chatMessages.Add(new Label { Text = user + " => " + message });
        });

        Task.Run(() =>
        {
            Dispatcher.Dispatch(async () =>
            await _connection.StartAsync());
        });
    }

    private async void OnSendMessage(object sender, EventArgs e)
    {
        await _connection.InvokeCoreAsync("SendMessage", args: new[] { myChatUser.Text, myChatMessage.Text });

        myChatMessage.Text = String.Empty;
    }
}

