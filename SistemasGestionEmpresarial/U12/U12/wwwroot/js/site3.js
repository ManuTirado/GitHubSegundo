window.onload = inicializarEventos;

var modelos_0 = new Array("-");
var modelos_bmw = new Array("-", "Serie 1", "X3", "Serie 5");
var modelos_audi = new Array("-", "A4", "Q5", "R8");
var modelos_mazda = new Array("-", "MX-5", "CX-30");

function inicializarEventos() {
    var slcMarca = document.getElementById("slcMarca")
    slcMarca.addEventListener("change", cambia, false)
}

function cambia() {
    var slcMarca = document.getElementById("slcMarca");
    var slcModelo = document.getElementById("slcModelo");
    /*
    //Compruebo si selecciona la opción vacía
    if (slcMarca.value != 0) {
        
        //selecionamos las cosas Correctas
        mis_opts = eval("modelos_" + marcaSeleccionada);
        //se calcula el numero de cosas
        num_opts = mis_opts.length;
        //marco el numero de opt en el select
        document.getElementById("slcMarca").length = num_opts;
        //para cada opt del array, la pongo en el select
        */
    var mis_opts = eval("modelos_" + marcaSeleccionada);
    for (i = 0; i < mis_opts.length; i++) {
        slcModelo.options[i].value = mis_opts[i];
        slcModelo.options[i].text = mis_opts[i];
    }
    /*
    } else {
        //si no habia ninguna opt seleccionada, elimino las cosas del select
        document.getElementById("slcModelo").length = 1;
        //ponemos un guion en la unica opt que he dejado
        document.getElementById("slcModelo").options[0].value = "-";
        document.getElementById("slcModelo").options[0].text = "-";
    }
    */
    //hacer un reset de las opts
    document.getElementById("slcModelo").options[0].selected = true;

}