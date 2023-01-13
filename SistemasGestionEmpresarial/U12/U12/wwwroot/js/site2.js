// Please see documentation at https://docs.microsoft.com/aspnet/core/client-side/bundling-and-minification
// for details on configuring this project to bundle and minify static web assets.

// Write your JavaScript code.

window.onload = inicializarEventos

class clsPersona {
    constructor(nombre, apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }
}

function inicializarEventos() {
    var btnSaludar = document.getElementById("btnSaludar");
    btnSaludar.addEventListener("click", saludar, false);
}

function saludar() {
    var txtNombre = document.getElementById("txtNombre").value;
    var txtApellidos = document.getElementById("txtApellidos").value;
    persona = new clsPersona(txtNombre, txtApellidos)
    alert("Hola " + persona.nombre + " " + persona.apellidos)
}
