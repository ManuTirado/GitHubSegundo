
window.onload = inicializarEventos;

var departamentos;
var listaPersonas;
var personasListas = false;
var departamentosListos = false;

function inicializarEventos() {
    obtenerListaDepartamentos();
    obtenerListaPersonas();

    var btnActualizar = document.getElementById("btnActualizar");
    btnActualizar.addEventListener("click", actualizarDatos);
}

/**
 * Comprueba 
 * */
function actualizarDatos() {
    var tbody = document.getElementById("tbodyPersonas");
    tbody.childNodes.forEach(row => {
        var id = row.childNodes.item(0);
        var IdDept;

        if (id !== null) {
            console.log(id.textContent);
            var DepSeleccionado = document.getElementById(`select${id.textContent}`)
            console.log(DepSeleccionado);
            DepSeleccionado.childNodes.forEach(opt => {
                opt.getAttributeNames().forEach(atr => {
                    if (atr === "selected") {
                        IdDept = opt.getAttributeNode("value").textContent;
                        console.log(IdDept);
                    }
                });
            });
            var persona = listaPersonas.find(p => p.id == id.textContent);
            if (persona.idDepartamento != IdDept) {
                actualizaPersona(persona, IdDept);
            }
        }
    });
}

function actualizaPersona(persona, idDeptNuevo) {
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
    if (personasListas == true && departamentosListos == true) {
        console.log(departamentos);
        console.log(listaPersonas);
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
            colIdDept.className = "celdaForm";
            var str;
            str = `<select id="select${p.id}">`;
            console.log(p.nombre)
            departamentos.forEach(d => {
                if (d.id === p.idDepartamento) {
                    str += `<option value="${d.id}" selected> ${d.nombre} </option>`;
                    console.log("Seleccionado ->" + d.id + " " + d.nombre)
                } else {
                    str += `<option value="${d.id}"> ${d.nombre} </option>`;
                    console.log(d.id + " " + d.nombre)
                }
            });
            str += `</select >`;
            colIdDept.innerHTML = str;
            tr.appendChild(colIdDept);
            tbody.appendChild(tr);
        })
    }
}

function obtenerListaPersonas() {
    ajax({
        method: "GET",
        url: "http://localhost:5153/api/Persona",
        succes: (res) => {
            console.log("Lectura correcta de personas");
            console.log(res);
            listaPersonas = res;
            personasListas = true;
            pintarPersonas();
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
            console.log(res);
            departamentos = res;
            departamentosListos = true;
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