package com.example.carrocompras_kotlin

//import android.media.Image
import android.widget.ImageView
import java.io.Serializable

data class Producto(
    var idProducto: String,
    var fotoProducto: Int,
    var nomProducto: String,
    var precio: Double
): Serializable
