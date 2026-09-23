package com.tecsupfit.app.model

data class Clase(
    val id: Int,
    val nombre: String,
    val categoria: String,       // "Hoy" o "Esta semana"
    val instructor: String,
    val horarios: List<String>,
    val sala: String = "Sala 2",
    val duracion: String = "45 min",
    val cuposDisponibles: String = "8 de 12 cupos disponibles",
    val descripcion: String = "Entrenamiento físico especializado de alta calidad institucional."
)
