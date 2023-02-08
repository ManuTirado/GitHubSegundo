
window.onload = inicializarEventos;

var departamentos;
var listaPersonas;

function inicializarEventos() {
    obtenerListaPersonas();

    var btnActualizar = document.getElementById("btnActualizar");
    btnActualizar.addEventListener("click", actualizarDatos);
}

/**
 * Comprueba 
 * */
function actualizarDatos() {
    listaPersonas.forEach(p => {
        var IdDept = document.getElementById(`select${p.id}`).value;
        console.log(`Departamento antigüo: ${p.idDepartamento}, nuevo departamento = ${IdDept}`);
        if (p.idDepartamento != IdDept) {
            actualizaPersona(p, IdDept);
        }
    });
}

function actualizaPersona(persona, idDeptNuevo) {
    console.log("Actualizando persona " + persona.nombre);
    ajax({
        method: "PUT",
        url: `http://localhost:5153/api/Persona/${persona.id}`,
        succes: (res) => {
            alert("Se ha actualizado correctamente: " + res)
        },
        error: (err) => {
            { alert("Ocurrió un error: " + err) }
        },
        data: {
            id: persona.id,
            nombre: persona.nombre,
            apellidos: persona.apellidos,
            telefono: persona.telefono,
            direccion: persona.direccion,
            foto: persona.foto,
            fechaNacimiento: persona.fechaNacimiento,
            idDepartamento: idDeptNuevo,
        }
    })
}

function pintarPersonas() {
    var tbody = document.getElementById("tbodyPersonas");
    listaPersonas.forEach(function (p, index) {
        var tr = document.createElement("tr");
        var colId = document.createElement("td");
        colId.className = "celdaId";
        colId.innerHTML = p.id;
        tr.appendChild(colId);
        var colNombre = document.createElement("td");
        colNombre.innerHTML = p.nombre;
        tr.appendChild(colNombre);
        var colApellidos = document.createElement("td");
        colApellidos.innerHTML = p.apellidos;
        tr.appendChild(colApellidos);

        var colIdDept = document.createElement("td");
        var select = document.createElement("select");
        select.id = `select${p.id}`;
        departamentos.forEach(d => {
            var option = document.createElement("option");
            option.innerHTML = d.nombre;
            option.value = d.id;
            option.innerHTML = d.nombre;
            if (d.id === p.idDepartamento) {
                option.selected = true;
            }
            select.appendChild(option);
        });
        colIdDept.appendChild(select);
        tr.appendChild(colIdDept);
        tbody.appendChild(tr);
    })
}

function obtenerListaPersonas() {
    ajax({
        method: "GET",
        url: "http://localhost:5153/api/Persona",
        succes: (res) => {
            console.log("Lectura correcta de personas");
            listaPersonas = res;
            obtenerListaDepartamentos();
        },
        error: (err) => { console.error("Ocurrió un error: " + err) },
        data: null
    })
}

function obtenerListaDepartamentos() {
    ajax({
        method: "GET",
        url: "http://localhost:5153/api/Departamento",
        succes: (res) => {
            console.log("Lectura correcta de departamentos");
            departamentos = res;
            pintarPersonas();
        },
        error: (err) => { console.error("Ocurrió un error: " + err) },
        data: null
    })
}


function ajax(options) {
    let { url, method, succes, error, data } = options;
    const xhr = new XMLHttpRequest();

    xhr.addEventListener("readystatechange", e => {
        if (xhr.readyState !== 4) return;

        if (xhr.status >= 200 & xhr.status <= 300) {
            let json = JSON.parse(xhr.responseText);
            succes(json);
        } else {
            let message = xhr.statusText || "Ocurrió un error";
            error(`Error ${xhr.status} ${message}`);
        }
    });

    xhr.open(method || "GET", url);
    xhr.setRequestHeader("Content-Type", "application/json; charset=utf-8");
    xhr.send(JSON.stringify(data));
}