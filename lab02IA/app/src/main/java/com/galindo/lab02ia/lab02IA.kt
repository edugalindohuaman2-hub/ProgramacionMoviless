package com.galindo.lab02ia

data class Producto(val nombre: String, val precio: Double, var cantidad: Int)

fun calcularSubtotal(productos: List<Producto>): Double {
    return productos.sumOf { it.precio * it.cantidad }
}

fun calcularIGV(subtotal: Double): Double {
    return subtotal * 0.18
}

fun calcularTotal(subtotal: Double, igv: Double): Double {
    return subtotal + igv
}

fun calcularDescuento(total: Double): Double {
    return when {
        total > 5000 -> total * 0.10
        total > 3000 -> total * 0.05
        else -> 0.0
    }
}

fun mostrarDetalle(productos: List<Producto>) {
    println(String.format("%-20s %-12s %-10s %-12s", "Producto", "Precio U.", "Cantidad", "Subtotal"))
    println("-".repeat(56))
    for (prod in productos) {
        val subtotalItem = prod.precio * prod.cantidad
        println(String.format("%-20s S/ %-9.2f %-10d S/ %-9.2f", prod.nombre, prod.precio, prod.cantidad, subtotalItem))
    }
    println("-".repeat(56))
}

fun main() {
    print("Ingrese el nombre del cliente: ")
    val cliente = readln()

    print("¿Cuántos productos desea ingresar?: ")
    val cantidadProductos = readln().toIntOrNull() ?: 0

    val productos = mutableListOf<Producto>()

    for (i in 1..cantidadProductos) {
        println("\n--- Producto $i ---")
        print("Nombre: ")
        val nombre = readln()

        print("Precio unitario: ")
        val precio = readln().toDoubleOrNull() ?: 0.0

        print("Cantidad: ")
        val cantidad = readln().toIntOrNull() ?: 0

        productos.add(Producto(nombre, precio, cantidad))
    }

    println("\n========================================================")
    println("              RESUMEN DE COMPRA - CLIENTE: $cliente")
    println("========================================================")

    mostrarDetalle(productos)

    val subtotal = calcularSubtotal(productos)
    val igv = calcularIGV(subtotal)
    val totalSinDescuento = calcularTotal(subtotal, igv)
    val descuento = calcularDescuento(totalSinDescuento)
    val totalFinal = totalSinDescuento - descuento

    val productoMasCaro = productos.maxByOrNull { it.precio }

    println(String.format("%-40s S/ %9.2f", "Subtotal:", subtotal))
    println(String.format("%-40s S/ %9.2f", "IGV (18%):", igv))
    println(String.format("%-40s S/ %9.2f", "Total sin descuento:", totalSinDescuento))
    println(String.format("%-40s S/ %9.2f", "Descuento aplicado:", descuento))
    println(String.format("%-40s S/ %9.2f", "TOTAL FINAL A PAGAR:", totalFinal))
    println("--------------------------------------------------------")

    if (productoMasCaro != null) {
        println("Producto más caro: ${productoMasCaro.nombre} (S/ ${String.format("%.2f", productoMasCaro.precio)})")
    } else {
        println("No se ingresaron productos.")
    }
}
