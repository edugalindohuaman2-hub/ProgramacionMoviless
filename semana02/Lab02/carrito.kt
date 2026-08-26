data class Producto(
    val nombre: String,
    val precio: Double,
    var cantidad: Int
)

fun calcularSubtotal(productos: List<Producto>): Double {
    var subtotal = 0.0
    for (p in productos) {
        subtotal += p.precio * p.cantidad
    }
    return subtotal
}

fun calcularIGV(subtotal: Double): Double {
    return subtotal * 0.18
}

fun calcularTotal(subtotal: Double, igv: Double): Double {
    return subtotal + igv
}

fun mostrarDetalle(productos: List<Producto>) {
    println("----------------- DETALLE DEL CARRITO -----------------")
    var i = 1
    for (p in productos) {
        val importe = p.precio * p.cantidad
        println(String.format("%d. %-20s x%d S/ %8.2f", i, p.nombre, p.cantidad, importe))
        i++
    }
    println("------------------------------------------------------")
}

fun main() {
    println("==========================================")
    println("      Carrito de compras - Tecsup        ")
    println("==========================================")

    val nombreCliente = "Edu Edward"
    val vendedor = "Juan Leon"
    val carrito = mutableListOf<Producto>()

    println("Atendido por: $vendedor")
    println("Cliente: $nombreCliente\n")

    carrito.add(Producto("Laptop HP", 2500.0, 1))
    carrito.add(Producto("Mouse Logitech", 45.5, 2))
    carrito.add(Producto("Audifonos Sony", 120.0, 1))
    carrito.add(Producto("USB Kingston 64GB", 25.0, 3))

    for (producto in carrito) {
        val importe = producto.precio * producto.cantidad
        println("Producto agregado: ${producto.nombre} x${producto.cantidad} - S/ $importe")
    }
    println()

    mostrarDetalle(carrito)

    val subtotal = calcularSubtotal(carrito)
    val igv = calcularIGV(subtotal)
    val totalSinDescuento = calcularTotal(subtotal, igv)
    val descuento = if (subtotal > 1000) subtotal * 0.10 else 0.0
    val totalFinal = totalSinDescuento - descuento

    println(String.format("%-25s : %d", "Cantidad de productos", carrito.size))
    println(String.format("%-25s : S/ %8.2f", "Subtotal", subtotal))
    println(String.format("%-25s : S/ %8.2f", "IGV (18%)", igv))
    println(String.format("%-25s : S/ %8.2f", "Descuento (10%)", descuento))
    println(String.format("%-25s : S/ %8.2f", "TOTAL A PAGAR", totalFinal))
}