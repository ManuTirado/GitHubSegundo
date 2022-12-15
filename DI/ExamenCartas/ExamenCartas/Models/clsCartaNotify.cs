using Entidades;
using System;
using System.Collections.Generic;
using System.ComponentModel;

namespace ExamenCartas.Models
{
    public class clsCartaNotify : clsCarta, INotifyPropertyChanged
    {
        public new string rutaImagenMostrada
        {
            get { return base.rutaImagenMostrada; }
            set
            {
                base.rutaImagenMostrada = value;
                NotifyPropertyChanged(nameof(rutaImagenMostrada));
            }
        }

        public clsCartaNotify()
        {

        }
        public clsCartaNotify(int id, string imagenAnverso, string imagenReverso, bool isAmigo)
        {
            base.id = id;
            base.rutaImagenAnverso = imagenAnverso;
            base.rutaImagenReverso = imagenReverso;
            base.isAmigo = isAmigo;
            base.rutaImagenMostrada = rutaImagenReverso;
        }

        public clsCartaNotify(clsCartaNotify clsCartaNotify)
        {
            base.id = clsCartaNotify.id;
            base.rutaImagenAnverso = clsCartaNotify.rutaImagenAnverso;
            base.rutaImagenReverso = clsCartaNotify.rutaImagenReverso;
            base.isAmigo = clsCartaNotify.isAmigo;
            base.rutaImagenMostrada = clsCartaNotify.rutaImagenReverso;
        }

        public event PropertyChangedEventHandler PropertyChanged;

        protected virtual void NotifyPropertyChanged(string propertyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
    }
}
