window.onload = inicializarEventos;


function inicializarEventos() {
    var btnLLamada = document.getElementById("btnLLamada");
    btnLLamada.addEventListener("click", pedirDatos, false);
}

function pedirDatos() {
    var miLlamada = new XMLHttpRequest();
    var divNombre = document.getElementById("divNombre");

    miLlamada.open("GET", "https://xn--crudmuoa-i3a.azurewebsites.net/api/personas");

    //Definicion estados

    miLlamada.onreadystatechange = function () {
        if (miLlamada.readyState < 4) {
            
            //aquí se puede poner una imagen de un reloj o un texto “Cargando”
            divNombre.innerHTML = "Cargando...";
        }
        else

            if (miLlamada.readyState == 4 && miLlamada.status == 200) {

                var datosRecibidos = JSON.parse(miLlamada.responseText);
                divNombre.innerHTML = datosRecibidos[7].apellidos;

            }

    };

    miLlamada.send();

}
