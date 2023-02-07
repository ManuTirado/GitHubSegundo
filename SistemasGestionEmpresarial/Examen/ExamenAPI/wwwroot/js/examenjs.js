

window.onload = inicializarEventos;

function inicializarEventos() {
    // Cargar todas las personas
    cargarPersonas();
    // Leer Persona
    var btnLeerPersona = document.getElementById("btnLeerPersona");
    btnLeerPersona.addEventListener("click", leerPersona)
    // Insertar Persona

    // Actualizar Persona

    // Borrar Persona
}

function leerPersona() {
    var idPersona = document.getElementById("inpIdLeerPersona").value;
    ajax({
        method: "GET",
        url: `http://localhost:5153/api/examen/${idPersona}`,
        succes: (p) => {
            console.log(p);
            var tbody = document.getElementById("tbodyPersona");
            tbody.innerHTML = "";
            var tr = document.createElement("tr");
            var colId = document.createElement("td");
            colId.innerHTML = p.id;
            tr.appendChild(colId);
            var colNombre = document.createElement("td");
            colNombre.innerHTML = p.nombre;
            tr.appendChild(colNombre);
            var colApellidos = document.createElement("td");
            colApellidos.innerHTML = p.apellidos;
            tr.appendChild(colApellidos);
            var colTelefono = document.createElement("td");
            colTelefono.innerHTML = p.telefono;
            tr.appendChild(colTelefono);
            var colDireccion = document.createElement("td");
            colDireccion.innerHTML = p.direccion;
            tr.appendChild(colDireccion);
            var colFoto = document.createElement("td");
            colFoto.innerHTML = p.foto;
            tr.appendChild(colFoto);
            var colFNac = document.createElement("td");
            colFNac.innerHTML = p.fechaNacimiento;
            tr.appendChild(colFNac);
            var colIdDept = document.createElement("td");
            colIdDept.innerHTML = p.idDepartamento;
            tr.appendChild(colIdDept);
            tbody.appendChild(tr);
        },
        error: (err) => { alert("Ocurrió un error: " + err) },
        data: null
    })
}

function cargarPersonas() {
    ajax({
        method: "GET",
        url: "http://localhost:5153/api/examen",
        succes: (res) => {
            alert("Todo salió bien"); console.log(res)
            var tbody = document.getElementById("tbodyPersonas");

            res.forEach(function (p, index) {
                var tr = document.createElement("tr");
                var colId = document.createElement("td");
                colId.innerHTML = p.id;
                tr.appendChild(colId);
                var colNombre = document.createElement("td");
                colNombre.innerHTML = p.nombre;
                tr.appendChild(colNombre);
                var colApellidos = document.createElement("td");
                colApellidos.innerHTML = p.apellidos;
                tr.appendChild(colApellidos);
                var colTelefono = document.createElement("td");
                colTelefono.innerHTML = p.telefono;
                tr.appendChild(colTelefono);
                var colDireccion = document.createElement("td");
                colDireccion.innerHTML = p.direccion;
                tr.appendChild(colDireccion);
                var colFoto = document.createElement("td");
                colFoto.innerHTML = p.foto;
                tr.appendChild(colFoto);
                var colFNac = document.createElement("td");
                colFNac.innerHTML = p.fechaNacimiento;
                tr.appendChild(colFNac);
                var colIdDept = document.createElement("td");
                colIdDept.innerHTML = p.idDepartamento;
                tr.appendChild(colIdDept);
                tbody.appendChild(tr);
            });

        },
        error: (err) => { alert("Ocurrió un error: " + err) },
        data: null
    })
}

async function insertarPersona() {
    var id = 0;
    var nombre = document.getElementById("inpInsNombre").value;
    var apellidos = document.getElementById("inpInsApellidos").value;
    var telefono = document.getElementById("inpInsTelefono").value;
    var direccion = document.getElementById("inpInsDireccion").value;
    var foto = document.getElementById("inpInsFoto").value;
    var fechaNacimiento = document.getElementById("inpInsNacimiento").value;
    var idDepartamento = document.getElementById("inpInsIdDepartamento").value;
    if (fechaNacimiento == "") {
        fechaNacimiento = "01/01/2000"
    }
    if (idDepartamento == "") {
        idDepartamento = "0";
    }

    try {
        const response = await fetch("http://localhost:5153/api/examen", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                id,
                nombre,
                apellidos,
                telefono,
                direccion,
                foto,
                fechaNacimiento,
                idDepartamento,
            }),
        }).then(res => res.json())
            .catch(error => console.error('Error:', error))
            .then(response => console.log('Success:', response));

        if (!response.ok || response.status !== 200) {
            throw new Error("No se ha podido agregar a la persona");
        } else if (response.status === 200) {
            alert("Se ha agregado correctamente a la persona");
        }

    } catch (e) {
        alert(e);
    }
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

/*
 * var prueba = document.getElementById("Prueba");
    var element = document.createElement(HTMLParagraphElement.name);
    var node = document.createTextNode("Prueba js");
    element.appendChild(node);
    prueba.appendChild(element);
 */