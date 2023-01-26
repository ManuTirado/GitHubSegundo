window.onload = inicializarEventos;

function inicializarEventos() {
    var arrayDepartamentos;
    pedirDepartamentos();
    pedirPersonas();
}

function pedirDepartamentos() {
    var miLlamada = new XMLHttpRequest();
    miLlamada.open("GET", "https://apipersonaspaco.azurewebsites.net/api/departamentos");
    //Definicion estados
    miLlamada.onreadystatechange = function () {
        if (miLlamada.readyState < 4) {
            //aquí se puede poner una imagen de un reloj o un texto “Cargando"
        }
        else if (miLlamada.readyState == 4 && miLlamada.status == 200) {
             arrayDepartamentos = JSON.parse(miLlamada.responseText);
        }
    };
    miLlamada.send();
}

function pedirPersonas() {
    var miLlamada = new XMLHttpRequest();
    miLlamada.open("GET", "https://apipersonaspaco.azurewebsites.net/api/personas");
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
    arrayPersonas.forEach(function (p, index) {
        cuerpoTabla.innerHTML += `<tr> <td>${p.nombre}</td><td>${p.apellidos}</td><td>${arrayDepartamentos.find(d => d.id == p.idDepartamento).nombre}</td> </tr>`;
    });
}