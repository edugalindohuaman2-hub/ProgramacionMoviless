package com.galindo.actividad_a.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.galindo.actividad_a.data.listaMedicos
import com.galindo.actividad_a.model.Cita
import com.galindo.actividad_a.navigation.screens.AgendarCitaScreen
import com.galindo.actividad_a.navigation.screens.ConfirmacionScreen
import com.galindo.actividad_a.navigation.screens.HistorialScreen
import com.galindo.actividad_a.navigation.screens.InicioScreen
import kotlinx.coroutines.launch
import com.galindo.actividad_a.navigation.screens.PerfilMedicoScreen
import com.galindo.actividad_a.navigation.screens.MisCitasScreen

// El Drawer envuelve al Scaffold porque necesita dibujarse ENCIMA de toda
// la pantalla (incluida la topBar), mientras que Scaffold solo organiza
// topBar/content dentro de su propio marco. Por eso van en ese orden.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppRoot() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    // Lista de citas compartida por toda la app. No usamos ViewModel (eso es
    // Semana 7), así que este remember vive en el composable más alto y se
    // pasa hacia abajo a las pantallas que lo necesitan.
    val listaCitas = remember { mutableStateListOf<Cita>() }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Menú", modifier = Modifier.padding(16.dp))
                NavigationDrawerItem(
                    label = { Text("Inicio") },
                    selected = false,
                    onClick = {
                        navController.navigate("inicio")
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Mis citas") },
                    selected = false,
                    onClick = {
                        navController.navigate("misCitas")
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Historial médico") },
                    selected = false,
                    onClick = {
                        navController.navigate("historial")
                        scope.launch { drawerState.close() }
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Clínica Salud+") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Abrir menú")
                        }
                    }
                )
            }
        ) { padding ->
            NavHost(
                navController = navController,
                startDestination = "inicio",
                modifier = Modifier.padding(padding)
            ) {
                composable("inicio") {
                    InicioScreen(navController = navController)
                }

                composable(
                    route = "perfil/{medicoId}",
                    arguments = listOf(navArgument("medicoId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val medicoId = backStackEntry.arguments?.getString("medicoId")
                    val medico = listaMedicos.find { it.id == medicoId }
                    if (medico != null) {
                        PerfilMedicoScreen(medico = medico, navController = navController)
                    }
                }

                composable(
                    route = "agendar/{medicoId}",
                    arguments = listOf(navArgument("medicoId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val medicoId = backStackEntry.arguments?.getString("medicoId")
                    val medico = listaMedicos.find { it.id == medicoId }
                    if (medico != null) {
                        AgendarCitaScreen(
                            medico = medico,
                            navController = navController,
                            onCitaConfirmada = { cita -> listaCitas.add(cita) }
                        )
                    }
                }

                composable(
                    route = "confirmacion/{medicoNombre}/{fecha}/{hora}",
                    arguments = listOf(
                        navArgument("medicoNombre") { type = NavType.StringType },
                        navArgument("fecha") { type = NavType.StringType },
                        navArgument("hora") { type = NavType.StringType }
                    )
                ) { backStackEntry ->
                    val nombre = backStackEntry.arguments?.getString("medicoNombre") ?: ""
                    val fecha = backStackEntry.arguments?.getString("fecha") ?: ""
                    val hora = backStackEntry.arguments?.getString("hora") ?: ""
                    ConfirmacionScreen(
                        medicoNombre = nombre,
                        fecha = fecha,
                        hora = hora,
                        navController = navController
                    )
                }

                composable("misCitas") {
                    MisCitasScreen(listaCitas = listaCitas)
                }

                composable("historial") {
                    HistorialScreen()
                }
            }
        }
    }
}