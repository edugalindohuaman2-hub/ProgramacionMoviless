package com.tecsupfit.app.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tecsupfit.app.model.Clase

@Composable
fun InicioScreen(
    clases: List<Clase>,
    onClaseClick: (Clase) -> Unit
) {
    var filtroSeleccionado by remember { mutableStateOf("Hoy") }
    val filtros = listOf("Hoy", "Esta semana")
    var searchQuery by remember { mutableStateOf("") }

    val clasesFiltradas = clases.filter { clase ->
        val coincideFiltro = clase.categoria.equals(filtroSeleccionado, ignoreCase = true)
        val coincideBusqueda = clase.nombre.contains(searchQuery, ignoreCase = true) ||
                clase.instructor.contains(searchQuery, ignoreCase = true)
        coincideFiltro && coincideBusqueda
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 28.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Cabecera institucional (Edu Galindo)
        item {
            HeaderVectorialSection()
        }

        // Buscador M3 Estilizado
        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Buscar clase o instructor...", fontSize = 14.sp) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar", tint = MaterialTheme.colorScheme.primary) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface
                ),
                singleLine = true
            )
        }

        // Banner Hero Vectorial Destacado
        if (searchQuery.isEmpty() && clases.isNotEmpty()) {
            item {
                BannerVectorialDestacadoCard(clase = clases.first(), onClick = { onClaseClick(clases.first()) })
            }
        }

        // Sección de Filtros (FilterChips) y Conteo
        item {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Clases Disponibles",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    AssistChip(
                        onClick = {},
                        label = { Text("${clasesFiltradas.size} disponibles", fontSize = 12.sp, fontWeight = FontWeight.Bold) },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            labelColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        border = null
                    )
                }

                Spacer(Modifier.height(12.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(filtros) { filtro ->
                        val isSelected = filtroSeleccionado == filtro
                        FilterChip(
                            selected = isSelected,
                            onClick = { filtroSeleccionado = filtro },
                            label = { Text(filtro, fontSize = 13.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                                containerColor = MaterialTheme.colorScheme.surface,
                                labelColor = MaterialTheme.colorScheme.onSurface
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                enabled = true,
                                selected = isSelected,
                                borderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f),
                                selectedBorderColor = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                }
            }
        }

        // Lista de Tarjetas Vectoriales (OutlinedCard / ElevatedCard con 16.dp)
        items(clasesFiltradas) { clase ->
            ClaseCardVectorial(clase = clase, onClick = { onClaseClick(clase) })
        }
    }
}

@Composable
private fun HeaderVectorialSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Verified,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = "PLAN PREMIUM • TECSUP",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        letterSpacing = 0.8.sp
                    )
                }
            }

            Spacer(Modifier.height(6.dp))

            Text(
                text = "Hola, Edu Galindo ⚡",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Reserva tu clase institucional favorita",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Avatar tonal con iniciales EG
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(48.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = "EG",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
private fun BannerVectorialDestacadoCard(clase: Clase, onClick: () -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.secondary
                        )
                    )
                )
                .padding(20.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AssistChip(
                        onClick = {},
                        label = { Text("DESTACADO DE HOY", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                        leadingIcon = {
                            Icon(Icons.Default.Star, contentDescription = null, modifier = Modifier.size(14.dp), tint = MaterialTheme.colorScheme.onPrimary)
                        },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f),
                            labelColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        border = null
                    )

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.15f)
                    ) {
                        Text(
                            text = clase.duracion,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = clase.nombre,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = "Instructor: ${clase.instructor} • ${clase.sala}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.9f)
                        )
                    }

                    Spacer(Modifier.width(12.dp))

                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(44.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = "Ver detalle",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ClaseCardVectorial(clase: Clase, onClick: () -> Unit) {
    val iconoClase = when (clase.nombre.lowercase()) {
        "yoga funcional" -> Icons.Default.SelfImprovement
        "cross training" -> Icons.Default.FitnessCenter
        "spinning" -> Icons.Default.Sports
        else -> Icons.Default.SportsGymnastics
    }

    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
        elevation = CardDefaults.outlinedCardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier.size(48.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = iconoClase,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(26.dp)
                            )
                        }
                    }

                    Column {
                        Text(
                            text = clase.nombre,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(Modifier.height(2.dp))
                        Text(
                            text = "Instructor: ${clase.instructor}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Etiquetas tipo Pill (AssistChip) para Sala y Cupos
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AssistChip(
                    onClick = {},
                    label = { Text(clase.sala, fontSize = 11.sp, fontWeight = FontWeight.Medium) },
                    leadingIcon = {
                        Icon(Icons.Default.LocationOn, contentDescription = null, modifier = Modifier.size(14.dp), tint = MaterialTheme.colorScheme.primary)
                    },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        labelColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    border = null
                )

                AssistChip(
                    onClick = {},
                    label = { Text(clase.cuposDisponibles, fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                    leadingIcon = {
                        Icon(Icons.Default.Group, contentDescription = null, modifier = Modifier.size(14.dp), tint = MaterialTheme.colorScheme.primary)
                    },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        labelColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    border = null
                )
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))

            // Horario principal y botón de acción
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = clase.horarios.firstOrNull() ?: "7:00 am",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                FilledTonalButton(
                    onClick = onClick,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ) {
                    Text("Ver Detalle", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    Spacer(Modifier.width(4.dp))
                    Icon(Icons.Default.ChevronRight, contentDescription = null, modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}
