package com.tecsupfit.app.ui.screens

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tecsupfit.app.model.Clase

@Composable
fun DetalleClaseScreen(
    clase: Clase,
    onBackClick: (() -> Unit)? = null,
    onReservar: (horarioElegido: String) -> Unit
) {
    var horarioSeleccionado by remember { mutableStateOf<String?>(null) }
    val scrollState = rememberScrollState()

    val iconoClase = when (clase.nombre.lowercase()) {
        "yoga funcional" -> Icons.Default.SelfImprovement
        "cross training" -> Icons.Default.FitnessCenter
        "spinning" -> Icons.Default.Sports
        else -> Icons.Default.SportsGymnastics
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = 100.dp)
        ) {
            // Cabecera con Caja Contenedora Vectorial (Box) con esquinas redondeadas y fondo secundario/tonal suave
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                                MaterialTheme.colorScheme.background
                            )
                        )
                    )
                    .statusBarsPadding()
                    .padding(20.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    if (onBackClick != null) {
                        Surface(
                            onClick = onBackClick,
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier.size(40.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Volver",
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }

                    // Caja contenedora vectorial principal (Box) con esquinas redondeadas (24.dp) y fondo secundario suave
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .background(
                                color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f),
                                shape = RoundedCornerShape(24.dp)
                            )
                            .padding(20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(64.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = iconoClase,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onPrimary,
                                        modifier = Modifier.size(34.dp)
                                    )
                                }
                            }
                            Spacer(Modifier.height(12.dp))
                            Text(
                                text = clase.nombre,
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                            Spacer(Modifier.height(4.dp))
                            AssistChip(
                                onClick = {},
                                label = { Text("TECSUP FIT • ${clase.categoria.uppercase()}", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                                colors = AssistChipDefaults.assistChipColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    labelColor = MaterialTheme.colorScheme.onPrimaryContainer
                                ),
                                border = null
                            )
                        }
                    }
                }
            }

            // Cuerpo del detalle con OutlinedCard (16.dp)
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                // Tarjeta de Instructor
                OutlinedCard(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.outlinedCardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = MaterialTheme.colorScheme.primaryContainer,
                                modifier = Modifier.size(48.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = Icons.Default.Person,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }

                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = clase.instructor,
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Spacer(Modifier.width(4.dp))
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = "Verificado",
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                                Text(
                                    text = "Instructor Senior TECSUP Fit",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        AssistChip(
                            onClick = {},
                            label = { Text("4.9 ★", fontSize = 12.sp, fontWeight = FontWeight.Bold) },
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                labelColor = MaterialTheme.colorScheme.onPrimaryContainer
                            ),
                            border = null
                        )
                    }
                }

                // Micro-Tarjetas de Información (Duración, Sala, Cupos)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    DetalleInfoCardVector(
                        icon = Icons.Default.Schedule,
                        label = "DURACIÓN",
                        value = clase.duracion,
                        modifier = Modifier.weight(1f)
                    )
                    DetalleInfoCardVector(
                        icon = Icons.Default.LocationOn,
                        label = "SALA",
                        value = clase.sala,
                        modifier = Modifier.weight(1f)
                    )
                    DetalleInfoCardVector(
                        icon = Icons.Default.Group,
                        label = "CUPO",
                        value = clase.cuposDisponibles,
                        modifier = Modifier.weight(1f)
                    )
                }

                // Descripción
                OutlinedCard(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.outlinedCardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Descripción de la sesión",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = clase.descripcion,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 22.sp
                        )
                    }
                }

                HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))

                // Selección de Horarios
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Elige tu horario institucional",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        AssistChip(
                            onClick = {},
                            label = { Text("Paso 1 de 2", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                labelColor = MaterialTheme.colorScheme.onPrimaryContainer
                            ),
                            border = null
                        )
                    }

                    clase.horarios.forEach { horario ->
                        val isSelected = horarioSeleccionado == horario

                        OutlinedCard(
                            onClick = { horarioSeleccionado = horario },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.outlinedCardColors(
                                containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                                else MaterialTheme.colorScheme.surface
                            ),
                            border = BorderStroke(
                                width = if (isSelected) 2.dp else 1.dp,
                                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Icon(
                                        imageVector = if (isSelected) Icons.Default.RadioButtonChecked else Icons.Default.RadioButtonUnchecked,
                                        contentDescription = null,
                                        tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                                    )

                                    Column {
                                        Text(
                                            text = horario,
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Spacer(Modifier.height(2.dp))
                                        Text(
                                            text = "${clase.sala} • Instructor ${clase.instructor}",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }

                                AssistChip(
                                    onClick = {},
                                    label = { Text(clase.cuposDisponibles, fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                                    leadingIcon = {
                                        Icon(Icons.Default.EventSeat, contentDescription = null, modifier = Modifier.size(12.dp), tint = MaterialTheme.colorScheme.primary)
                                    },
                                    colors = AssistChipDefaults.assistChipColors(
                                        containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                                        labelColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                                    ),
                                    border = null
                                )
                            }
                        }
                    }
                }
            }
        }

        // Botón Fijo Inferior M3
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp,
            shadowElevation = 16.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                val buttonScale by animateFloatAsState(
                    targetValue = if (horarioSeleccionado != null) 1.0f else 0.98f,
                    animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
                    label = "btnScale"
                )

                Button(
                    onClick = { horarioSeleccionado?.let(onReservar) },
                    enabled = horarioSeleccionado != null,
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .scale(buttonScale)
                ) {
                    Text(
                        text = if (horarioSeleccionado != null) "CONFIRMAR RESERVA" else "SELECCIONA UN HORARIO",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun DetalleInfoCardVector(
    icon: ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.outlinedCardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(22.dp)
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = label,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                letterSpacing = 0.5.sp
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = value,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
