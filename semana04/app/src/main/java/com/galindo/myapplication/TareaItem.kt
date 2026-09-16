package com.galindo.myapplication
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

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