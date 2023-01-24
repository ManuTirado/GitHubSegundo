window.onload = inicializarEventos;

function inicializarEventos() {
    pedirDatos();
}

function pedirDatos() {
    var miLlamada = new XMLHttpRequest();
    miLlamada.open("GET", "https://swapi.dev/api/people");
    //Definicion estados
    miLlamada.onreadystatechange = function () {
        if (miLlamada.readyState < 4) {
            //aquí se puede poner una imagen de un reloj o un texto “Cargando"
        }
        else if (miLlamada.readyState == 4 && miLlamada.status == 200) {
            var arrayPersonas = JSON.parse(miLlamada.responseText);
            anadirPersonasAtabla(arrayPersonas);
        }
    };
    miLlamada.send();
}

function anadirPersonasAtabla(arrayPersonas) {
    var cuerpoTabla = document.getElementById("cuerpoTabla");
    arrayPersonas.results.forEach(function (p, index) {
        cuerpoTabla.innerHTML += `<tr> <td>${p.name}</td><td>${p.gender}</td><td>${p.mass}</td> </tr>`;
    });
}