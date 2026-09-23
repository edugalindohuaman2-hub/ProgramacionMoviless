package com.tecsupfit.app.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tecsupfit.app.navigation.Screen

private data class BottomItem(val route: String, val label: String, val icon: ImageVector)

private val items = listOf(
    BottomItem(Screen.Inicio.route, "Inicio", Icons.Default.Home),
    BottomItem(Screen.Reservas.route, "Reservas", Icons.Default.DateRange),
    BottomItem(Screen.Rutinas.route, "Rutinas", Icons.Default.List),
    BottomItem(Screen.Perfil.route, "Perfil", Icons.Default.Person)
)

@Composable
fun TecsupFitBottomBar(navController: NavController, currentRoute: String?) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(Screen.Inicio.route) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}
