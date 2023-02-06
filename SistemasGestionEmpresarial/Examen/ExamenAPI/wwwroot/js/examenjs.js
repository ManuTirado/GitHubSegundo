window.onload = inicializarEventos;

function inicializarEventos() {
    var prueba = document.getElementById("Prueba");
    var element = document.createElement(HTMLParagraphElement.name);
    var node = document.createTextNode("Prueba js");
    element.appendChild(node);
    prueba.appendChild(element);
}