package com.galindo.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

data class Tarea(
    val id: Int,
    val texto: String,
    val completada: Boolean = false
)

@Composable
fun PantallaTareas() {
    val listaTareas = remember { mutableStateListOf<Tarea>() }
    var textoTarea by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. Título principal
        Text(
            text = "Lista de tareas - Tecsup",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 2. Campo de texto mejorado
        OutlinedTextField(
            value = textoTarea,
            onValueChange = { textoTarea = it },
            label = { Text("¿Qué tarea tienes pendiente?") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 3. Botón de ancho completo y redondeado
        Button(
            onClick = {
                if (textoTarea.isNotBlank()) {
                    val nuevoId = (listaTareas.maxOfOrNull { it.id } ?: 0) + 1
                    listaTareas.add(Tarea(id = nuevoId, texto = textoTarea))
                    textoTarea = ""
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = CircleShape
        ) {
            Text("Agregar tarea")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 4. Contador centrado
        Text(
            text = "Total de tareas: ${listaTareas.size}",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 5. Lista de tareas con TareaItem estilizado
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(listaTareas, key = { it.id }) { tarea ->
                TareaItem(
                    tarea = tarea,
                    onToggleCompletada = { id ->
                        val index = listaTareas.indexOfFirst { it.id == id }
                        if (index != -1) {
                            listaTareas[index] = listaTareas[index].copy(completada = !listaTareas[index].completada)
                        }
                    },
                    onEliminar = { id ->
                        listaTareas.removeAll { it.id == id }
                    }
                )
            }
        }
    }
}

@Composable
fun TareaItem(tarea: Tarea, onToggleCompletada: (Int) -> Unit, onEliminar: (Int) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Checkbox a la izquierda
            Checkbox(
                checked = tarea.completada,
                onCheckedChange = { onToggleCompletada(tarea.id) }
            )

            // Texto de la tarea al centro
            Text(
                text = tarea.texto,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                style = MaterialTheme.typography.bodyLarge
            )

            // Icono de eliminación a la derecha
            IconButton(onClick = { onEliminar(tarea.id) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar tarea",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
