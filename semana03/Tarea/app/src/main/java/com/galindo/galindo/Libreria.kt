package com.galindo.galindo.ui.theme


import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class Prestamo(
    val tituloLibro: String,
    val tipoUsuario: String,
    val fechaPrestamo: LocalDate,
    val fechaDevolucion: LocalDate,
    val fechaEntrega: LocalDate
)

fun calcularDiasRetraso(
    fechaDevolucion: LocalDate,
    fechaEntrega: LocalDate
): Long {

    return if (fechaEntrega.isAfter(fechaDevolucion)) {
        ChronoUnit.DAYS.between(
            fechaDevolucion,
            fechaEntrega
        )
    } else {
        0
    }
}

fun calcularMulta(
    tipoUsuario: String,
    diasRetraso: Long
): Double {

    return when (tipoUsuario.lowercase()) {

        "alumno" -> diasRetraso * 1.50

        "maestro" -> diasRetraso * 3.00

        else -> 0.0
    }
}

fun mostrarResultado(prestamo: Prestamo) {

    val diasRetraso = calcularDiasRetraso(
        prestamo.fechaDevolucion,
        prestamo.fechaEntrega
    )

    val multa = calcularMulta(
        prestamo.tipoUsuario,
        diasRetraso
    )

    println("=========================================")
    println("             BIBLIOTECA")
    println("=========================================")

    println("Titulo del libro: ${prestamo.tituloLibro}")
    println("Tipo de usuario: ${prestamo.tipoUsuario}")
    println("Fecha de prestamo: ${prestamo.fechaPrestamo}")
    println("Fecha de devolucion: ${prestamo.fechaDevolucion}")
    println("Fecha de entrega: ${prestamo.fechaEntrega}")

    if (diasRetraso > 0) {

        println(
            "Estado: Devuelto con $diasRetraso dia(s) de retraso"
        )

        println(
            String.format("Multa: S/ %.2f", multa)
        )

    } else {

        println("Estado: Devuelto a tiempo")
        println("Multa: S/ 0.00")
    }
    println("-----------------------------------------")

    println(
        String.format("Total S/ %.2f deuda", multa)
    )

    println("=========================================")
}

fun main() {

    print("Ingrese el título del libro: ")
    val tituloLibro = readln()

    print("Ingrese el tipo de usuario (Alumno/Maestro): ")
    val tipoUsuario = readln()

    print("Ingrese fecha de préstamo (AAAA-MM-DD): ")
    val fechaPrestamo = LocalDate.parse(readln())

    print("Ingrese fecha de devolución (AAAA-MM-DD): ")
    val fechaDevolucion = LocalDate.parse(readln())

    print("Ingrese fecha de entrega (AAAA-MM-DD): ")
    val fechaEntrega = LocalDate.parse(readln())

    val prestamo = Prestamo(
        tituloLibro,
        tipoUsuario,
        fechaPrestamo,
        fechaDevolucion,
        fechaEntrega
    )

    mostrarResultado(prestamo)
}
