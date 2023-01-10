package com.reproductores_51

import android.graphics.drawable.Drawable

class multimedia : java.io.Serializable {
    var nombre: String
    var rutaContenido: String
    var rutaImagen: Int
    var isVideo: Boolean

    constructor(nombre: String, rutaContenido: String, rutaImagen: Int, isVideo: Boolean) {
        this.nombre = nombre
        this.rutaContenido = rutaContenido
        this.rutaImagen = rutaImagen
        this.isVideo = isVideo
    }
}