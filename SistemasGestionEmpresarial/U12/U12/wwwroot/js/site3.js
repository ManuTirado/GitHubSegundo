window.onload = inicializarEventos;

var modelos_0 = new Array("-");
var modelos_bmw = new Array("-", "Serie 1", "X3", "Serie 5");
var modelos_audi = new Array("-", "A4", "Q5", "R8");
var modelos_mazda = new Array("-", "MX-5", "CX-30");

function inicializarEventos() {
    var selectMarca = document.getElementById("slcMarca");
    selectMarca.addEventListener("change", LlenarSelect)
}

function LlenarSelect() {
    var marcaSeleccionada = document.getElementById("slcMarca").value;
    var selectModelo = document.getElementById("slcModelo");

    var modelos
    switch (marcaSeleccionada) {
        case "bmw": modelos = modelos_bmw; break;
        case "audi": modelos = modelos_audi; break;
        case "mazda": modelos = modelos_mazda; break;
        default: modelos = modelos_0; break;
    }
    
    for (var i = 0; i < selectModelo.options.length; i++) {
        selectModelo.remove(i);
    }
    

    for (var i = 0; i < modelos.length; i++) {
        selectModelo.options[i] = new Option(modelos[i], modelos[i]);
    }

}