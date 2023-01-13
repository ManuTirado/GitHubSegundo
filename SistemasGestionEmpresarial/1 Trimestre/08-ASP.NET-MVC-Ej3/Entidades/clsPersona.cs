﻿using System.ComponentModel.DataAnnotations;

namespace Entidades
{
    public class clsPersona
    {
        public int Id { get; set; }
        [Required(ErrorMessage = "Campo obligatorio")]
        public string? Nombre { get; set; }
        public string? Apellido { get; set; }

        public clsPersona (int id, string nombre, string apellido)
        {
            Id = id;
            Nombre = nombre;
            Apellido = apellido;
        }
        public clsPersona ()
        {
            
        }
    }
}