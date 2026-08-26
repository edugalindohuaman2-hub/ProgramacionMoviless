#!/usr/bin/env kotlin

import java.util.Locale

// Modelado de datos
data class Producto(
    val nombre: String,
    val precio: Double,
    var cantidad: Int
)

// Funciones independientes de cálculo
fun calcularSubtotal(carrito: List<Producto>): Double {
    return carrito.sumOf { it.precio * it.cantidad }
}

fun calcularDescuento(subtotal: Double): Double {
    val porcentaje = when {
        subtotal > 5000.0 -> 0.10
        subtotal > 3000.0 -> 0.05
        else -> 0.00
    }
    return subtotal * porcentaje
}

fun calcularIGV(montoAfecto: Double): Double {
    return montoAfecto * 0.18
}

fun calcularTotal(subtotal: Double, descuento: Double, igv: Double): Double {
    return subtotal - descuento + igv
}
// Código para declarar y mostrar la información del vendedor

val vendedor = "Carlos Ramirez"
val numeroOrden = "OC-2026-001"
val fechaEmision = "26/08/2026"

fun main() {
    // Garantizar formato de números con punto (.) para decimales en String.format
    Locale.setDefault(Locale.US)

    val cliente = "Juan Leon"
    val carrito: MutableList<Producto> = mutableListOf()
    println("Vendedor asignado: $vendedor")
    println("==================================================")
    println("              TIENDA TECSUP - CARRITO             ")
    println("==================================================")
    println("Vendedor asignado: $vendedor")
    println("Cliente: $cliente\n")
    println("              ORDEN DE COMPRA: $numeroOrden")
    println("              Fecha: $fechaEmision")

    // Agregar productos y mostrar mensajes de confirmación
    fun agregarProducto(nombre: String, precio: Double, cantidad: Int) {
        carrito.add(Producto(nombre, precio, cantidad))
        println("[+] Se agregó al carrito: $nombre (x$cantidad)")
    }

    agregarProducto("Laptop HP", 2500.0, 1)
    agregarProducto("Mouse Logitech", 45.5, 2)
    agregarProducto("Audifonos Sony", 120.0, 1)
    agregarProducto("USB Kingston 64GB", 25.0, 3)
    carrito.add(Producto("Teclado Mecanico RGB", 280.0, 1))
    carrito.add(Producto("Monitor Gamer 24", 750.0, 2))

    println("\n--------------------------------------------------")
    println("DETALLE DE COMPRA")
    println("--------------------------------------------------")

    // Imprimir detalle con columnas alineadas mediante String.format
    for (prod in carrito) {
        val totalProducto = prod.precio * prod.cantidad
        println(String.format("%-20s x%-3d S/ %8.2f", prod.nombre, prod.cantidad, totalProducto))
    }

    println("--------------------------------------------------")

    // Cálculos
    val subtotal = calcularSubtotal(carrito)
    val descuento = calcularDescuento(subtotal)
    val montoConDescuento = subtotal - descuento
    val igv = calcularIGV(montoConDescuento)
    val total = calcularTotal(subtotal, descuento, igv)

    val totalItems = carrito.sumOf { it.cantidad }
    val productoMasCaro = carrito.maxByOrNull { it.precio }

    // Porcentaje de descuento para visualización
    val porcentajeTexto = when {
        subtotal > 10000.0 -> "20"
        subtotal > 5000.0 -> "10%"
        subtotal > 3000.0 -> "5%"
        else -> "0%"
    }

    // Resumen
    println(String.format("Cantidad total de productos : %d", totalItems))
    println(String.format("Subtotal                    : S/ %8.2f", subtotal))
    println(String.format("Descuento (%-3s)            : S/ %8.2f", porcentajeTexto, descuento))
    println(String.format("IGV (18%%)                   : S/ %8.2f", igv))
    println(String.format("Total a pagar               : S/ %8.2f", total))
    println("--------------------------------------------------")

    productoMasCaro?.let {
        println("Producto más caro           : ${it.nombre} (S/ ${String.format("%.2f", it.precio)})")
    }

    println("\n¡Gracias por su compra en TIENDA TECSUP, $cliente!")
    println("==================================================")
}