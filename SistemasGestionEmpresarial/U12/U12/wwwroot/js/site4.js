window.onload = inicializarEventos;


function inicializarEventos() {
    var btnRecorrerCeldas = document.getElementById("btnRecorrerCeldas");
    var btnAnadirFila = document.getElementById("btnAnadirFila");
    var btnBorrarFila = document.getElementById("btnBorrarFila");

    btnRecorrerCeldas.addEventListener("click", RecorrerCeldas);
    btnAnadirFila.addEventListener("click", AnadirFila);
    btnBorrarFila.addEventListener("click", BorrarFila);
}

function RecorrerCeldas() {
    var tabla = document.getElementById("tabla");
    var mensaje = "";
    for (var i = 0, row; row = tabla.rows[i]; i++) {    // Recorro las filas
        for (var j = 0, col; col = row.cells[j]; j++) {     // Recorro las columnas
            mensaje += `${col.innerText} [${i}][${j}]`;
            if (j < tabla.rows[i].cells.length  - 1) {
                mensaje += `, `;
            }
        }
        mensaje += "\n";
    }
    mensaje += "\n Fresquísimo 😎";
    alert(mensaje);
}

function AnadirFila() {
    var tabla = document.getElementById("tabla");
    var fila = tabla.insertRow(-1);
    for (var i = 0; i < 2; i++) {
        var celda = fila.insertCell(i);
        celda.innerText = `Celda${tabla.rows.length}${i+1}`;
    }
}

function BorrarFila() {
    var tabla = document.getElementById("tabla");
    tabla.rows[tabla.rows.length-1].remove();
}