package com.galindo.myapplication
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*

data class Tarea(
    val id: Int,
    val texto: String,
    val completada: Boolean = false
)

@Composable
fun TareaItem(
    tarea: Tarea,
    onToggleCompletada: (Int) -> Unit,
    onEliminar: (Int) -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = tarea.completada,
                onCheckedChange = { onToggleCompletada(tarea.id) }
            )
            Text(
                text = tarea.texto,
                modifier = Modifier.weight(1f),
                textDecoration = if (tarea.completada) TextDecoration.LineThrough else null
            )
            IconButton(onClick = { onEliminar(tarea.id) }) { Text("✕") }
        }
    }
}
@Composable
fun PantallaTareas() {
    val listaTareas = remember { mutableStateListOf<Tarea>() }
    var textoTarea by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Total de tareas: ${listaTareas.size}",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = textoTarea,
                onValueChange = { textoTarea = it },
                label = { Text("Nueva tarea") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (textoTarea.isNotBlank()) {
                    val nuevoId = (listaTareas.maxOfOrNull { it.id } ?: 0) + 1
                    listaTareas.add(Tarea(id = nuevoId, texto = textoTarea))
                    textoTarea = ""
                }
            }) {
                Text("Agregar")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listaTareas, key = { it.id }) { tarea ->
                TareaItem(
                    tarea = tarea,
                    onToggleCompletada = { id ->
                        val index = listaTareas.indexOfFirst { it.id == id }
                        if (index != -1) {
                            val itemActual = listaTareas[index]
                            listaTareas[index] = itemActual.copy(completada = !itemActual.completada)
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
fun ListaDeTareasScreen() {
    var tareas by remember { mutableStateOf(listOf<Tarea>()) }
    var textoNuevaTarea by remember { mutableStateOf("") }
    var siguienteId by remember { mutableStateOf(1) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Mis Tareas (${tareas.size})", style = MaterialTheme.typography.headlineSmall)

        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = textoNuevaTarea,
                onValueChange = { textoNuevaTarea = it },
                label = { Text("Nueva tarea") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (textoNuevaTarea.isNotBlank()) {
                    tareas = tareas + Tarea(id = siguienteId, texto = textoNuevaTarea)
                    siguienteId++
                    textoNuevaTarea = ""
                }
            }) { Text("Agregar") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(tareas, key = { it.id }) { tarea ->
                TareaItem(
                    tarea = tarea,
                    onToggleCompletada = {id ->
                        tareas = tareas.map {
                            if (it.id == id) it.copy(completada = !it.completada) else it
                        }
                    },
                    onEliminar = {id ->
                        tareas = tareas.filter { it.id != id}
                    }
                )
            }
        }
    }
}