window.onload = inicializarEventos;

function inicializarEventos() {
    var btnBorrar = document.getElementById("btnBorrar");
    btnBorrar.addEventListener("click", borrarPersona);
}

function borrarPersona() {
    var idPersona = document.getElementById("txtIdPersona").value;

    var miLlamada = new XMLHttpRequest();
    miLlamada.open("DELETE", `https://apipersonaspaco.azurewebsites.net/api/personas/${idPersona}`);
    miLlamada.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    //Definicion estados
    miLlamada.onreadystatechange = function () {
        var div = document.getElementById("divMensaje");
        if (miLlamada.readyState < 4) {
            //aquí se puede poner una imagen de un reloj o un texto “Cargando"
            div.innerHTML = "Buscando ID...";
        }
        else if (miLlamada.readyState == 4 && miLlamada.status == 200) {
            div.innerHTML = "Borrado Correctamente";
        }
    };
    miLlamada.send();
}