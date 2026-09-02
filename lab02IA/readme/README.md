El pront que se uso para este proyecto es Actúa como un desarrollador senior en Kotlin. Necesito un código simple, limpio y fácil de entender para un ejercicio universitario de consola en Android Studio sobre un "Carrito de compras".

Requisitos del programa:

1. Lenguaje: Kotlin.
2. Formato: Un solo archivo ejecutable (.kt) que contenga la data class, las funciones helper y la función main.
3. Sin comentarios en el código.
4. Interactividad (Ingreso de datos por teclado):
   - Solicitar al usuario el nombre del cliente mediante eadln().
   - Preguntar cuántos productos desea ingresar.
   - Mediante un bucle, pedir el nombre, precio unitario y cantidad para cada producto, agregándolos a una lista modificable (mutableListOf<Producto>).
5. Estructura y Funciones requeridas:
   - data class Producto(val nombre: String, val precio: Double, var cantidad: Int)
   - calcularSubtotal(productos: List<Producto>): Double: Calcula el subtotal acumulado.
   - calcularIGV(subtotal: Double): Double: Retorna el 18% del subtotal.
   - calcularTotal(subtotal: Double, igv: Double): Double: Suma el subtotal e IGV.
   - mostrarDetalle(productos: List<Producto>): Imprime el reporte formateado usando String.format para alinear columnas y mostrar 2 decimales.
   - calcularDescuento(total: Double): Double: Usa la estructura when (si es mayor a 5000 da 10%, si es mayor a 3000 da 5%, de lo contrario 0%).
   - En main: Imprimir los totales con formato, el producto más caro obtenido con maxByOrNull { it.precio } y el total final con descuento aplicado.

Dame únicamente el código listo para copiar y pegar.
