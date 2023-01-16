window.onload = inicializarEventos;

function inicializarEventos() {
    setInterval('dibujarReloj()', 1000);  //Llama al método cada segundo
}

function dibujarReloj() {
    var hora = new Date().toLocaleTimeString("es-ES");

    for (var i = 0, valor; valor = hora.split("")[i]; i++) {
        dibujarElemento(valor, i);
    }
}

function dibujarElemento(valor, indice) {
    var src = "";
    switch (valor) {
        case "0": src = "../res/0.gif"; break;
        case "1": src = "../res/1.gif"; break;
        case "2": src = "../res/2.gif"; break;
        case "3": src = "../res/3.gif"; break;
        case "4": src = "../res/4.gif"; break;
        case "5": src = "../res/5.gif"; break;
        case "6": src = "../res/6.gif"; break;
        case "7": src = "../res/7.gif"; break;
        case "8": src = "../res/8.gif"; break;
        case "9": src = "../res/9.gif"; break;
        case ":": src = "../res/dosPuntos.gif"; break;
        default: src = "../res/0.gif"; break;
    }

    var img = document.getElementById("hora" + indice);
    img.src = src;
}