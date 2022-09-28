using _03_Hello_World_Entidades_Estrandar;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace _03_Hello_World_WPF
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        /// <summary>
        /// Evento asociado al click del btnSaludar que mostrará un saludo al nombre que se introduzca en el textBox
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnSaludar_Click(object sender, RoutedEventArgs e)
        {
            clsPersona persona;

            if (txtNombre.Text.Length != 0)
            {
                persona = new clsPersona(txtNombre.Text);
                MessageBox.Show($"Hola {persona.Nombre}");
            }
        }

        private void txtNombre_TextChanged(object sender, TextChangedEventArgs e)
        {

        }
    }
}
