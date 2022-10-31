using _03_Hello_World_Entidades_Estrandar;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI.Popups;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;

// La plantilla de elemento Página en blanco está documentada en https://go.microsoft.com/fwlink/?LinkId=402352&clcid=0xc0a

namespace _03_Hello_World_UWP
{
    /// <summary>
    /// Página vacía que se puede usar de forma independiente o a la que se puede navegar dentro de un objeto Frame.
    /// </summary>
    public sealed partial class MainPage : Page
    {
        public MainPage()
        {
            this.InitializeComponent();
        }

        /// <summary>
        /// Evento asociado al click del btnSaludar que mostrará un saludo al nombre que se introduzca en el textBox
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnSaludar_Click(object sender, RoutedEventArgs e)
        {
            MessageDialog msg;
            clsPersona persona;

            if (txtNombre.Text.Length != 0)
            {
                persona = new clsPersona(txtNombre.Text);
                msg = new MessageDialog("Hola " + persona.Nombre);
                msg.ShowAsync();
            }
        }

        private void txtNombre_TextChanged(object sender, TextChangedEventArgs e)
        {

        }
    }
}
