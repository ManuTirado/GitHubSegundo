using Entidades;
using ExamenCartas.Models.Utilidades;
using System.Collections.ObjectModel;
using System.Collections.Specialized;

namespace ExamenCartas.Models.VM
{
    public class vmJuegoCartas : clsVMBase
    {
        #region Atributos
        private int cartasBuenasReveladas;
        private int cartasMalasReveladas;
        private List<clsCartaNotify> cartas; // longitud = 7, las diferentes cartas que hay
        private ObservableCollection<clsCartaNotify> barajaCartas = new ObservableCollection<clsCartaNotify>(); // longitud = 9, baraja generada aleatoriamente
        private clsCartaNotify cartaSeleccionada;
        #endregion
        #region Propiedades
        public ObservableCollection<clsCartaNotify> BarajaCartas { get { return barajaCartas; } }
        public clsCartaNotify CartaSeleccionada
        {
            get { return cartaSeleccionada; }
            set
            {
                if (cartaSeleccionada != value)
                {
                    cartaSeleccionada = value;
                    NotifyPropertyChanged(nameof(CartaSeleccionada));
                    if (cartaSeleccionada.rutaImagenMostrada == cartaSeleccionada.rutaImagenReverso) // xd
                    {
                        if (cartaSeleccionada.isAmigo)
                        {
                            cartasBuenasReveladas++;
                        }
                        else
                        {
                            cartasMalasReveladas++;
                        }
                        if (cartasBuenasReveladas == 3)
                        {
                            mostrarMensaje("Ha ganado!!!");
                        }
                        else if (cartasMalasReveladas == 3)
                        {
                            mostrarMensaje("Ha perdido ;(");
                        }
                        cartaSeleccionada.rutaImagenMostrada = cartaSeleccionada.rutaImagenAnverso;
                        NotifyPropertyChanged(nameof(CartaSeleccionada.rutaImagenMostrada));
                    }
                }
            }
        }
        #endregion

        #region Constructores
        public vmJuegoCartas()
        {
            cargarCartas();
            iniciarJuego();
        }
        #endregion

        #region Métodos
        private void iniciarJuego()
        {
            foreach (var carta in barajaCartas)
            {
                cartaSeleccionada = carta;
                NotifyPropertyChanged(nameof(CartaSeleccionada));
                cartaSeleccionada.rutaImagenMostrada = cartaSeleccionada.rutaImagenAnverso;
                NotifyPropertyChanged(nameof(CartaSeleccionada.rutaImagenMostrada));
            }
            NotifyPropertyChanged(nameof(CartaSeleccionada.rutaImagenMostrada));
            cartasBuenasReveladas = 0;
            cartasMalasReveladas = 0;
            generarBaraja();
        }
        private void cargarCartas()
        {
            cartas = new List<clsCartaNotify>()
            {
                new clsCartaNotify(1,"mandalorian.jpg", "reverso.jpg", true),
                new clsCartaNotify(2,"babyjoda.jpg", "reverso.jpg", true),
                new clsCartaNotify(3,"kraytdragon.jpg", "reverso.jpg", false),
                new clsCartaNotify(4,"atst.jpg","reverso.jpg", false),
                new clsCartaNotify(5,"moffgideon.jpg", "reverso.jpg", false),
                new clsCartaNotify(6,"spider.jpg", "reverso.jpg", false),
                new clsCartaNotify(7,"tradoshan.jpg", "reverso.jpg", false)
            };
        }
        private void generarBaraja() // jijijaja
        {
            barajaCartas = new ObservableCollection<clsCartaNotify>();
            int cartasBabyJoda = 0;
            int cartasMando = 0;
            int cartasEnemigo1 = 0, cartasEnemigo2 = 0, cartasEnemigo3 = 0, cartasEnemigo4 = 0, cartasEnemigo5 = 0;
            for (int i = 0; i < 9; i++)
            {
                clsCartaNotify carta;
                bool cartaValida = false;
                do
                {
                    carta = new clsCartaNotify(cartas[new Random().Next(0, cartas.Count)]);
                    if (carta.id == cartas[0].id)      // BabyJoda
                    {
                        cartasMando++;
                        if (cartasMando <= 2)
                        {
                            cartaValida = true;
                        }
                    }
                    else if (carta.id == cartas[1].id)
                    {     // Mando
                        cartasBabyJoda++;
                        if (cartasBabyJoda <= 2)
                        {
                            cartaValida = true;
                        }
                    }
                    else if (carta.id == cartas[2].id)                                         //Enemigos
                    {
                        cartasEnemigo1++;
                        if (cartasEnemigo1 <= 1)
                        {
                            cartaValida = true;
                        }
                    }
                    else if (carta.id == cartas[3].id)
                    {
                        cartasEnemigo2++;
                        if (cartasEnemigo2 <= 1)
                        {
                            cartaValida = true;
                        }
                    }
                    else if (carta.id == cartas[4].id)
                    {
                        cartasEnemigo3++;
                        if (cartasEnemigo3 <= 1)
                        {
                            cartaValida = true;
                        }
                    }
                    else if (carta.id == cartas[5].id)
                    {
                        cartasEnemigo4++;
                        if (cartasEnemigo4 <= 1)
                        {
                            cartaValida = true;
                        }
                    }
                    else if (carta.id == cartas[6].id)
                    {
                        cartasEnemigo5++;
                        if (cartasEnemigo5 <= 1)
                        {
                            cartaValida = true;
                        }
                    }

                } while (!cartaValida);
                barajaCartas.Add(new clsCartaNotify(carta));
            }
            NotifyPropertyChanged(nameof(BarajaCartas));
        }

        private async void mostrarMensaje(string mensaje)
        {
            bool volverAjugar = await Application.Current.MainPage.DisplayAlert(mensaje, "¿Desea jugar otra partida?", "Sí", "No");
            if (volverAjugar)
            {
                iniciarJuego();
            }
            else
            {
                Application.Current.Quit();
            }
        }
        #endregion
    }
}
