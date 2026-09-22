package com.galindo.laboratorio.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.galindo.laboratorio.navigation.Screen
@Composable
fun HomeScreen(
    onNavigate: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Pantalla Principal"
        )

        Button(
            onClick = {
                onNavigate(Screen.List.route)
            }
        ) {
            Text("Ver lista")
        }

        Button(
            onClick = {
                onNavigate(Screen.Profile.route)
            }
        ) {
            Text("Ver perfil")
        }
    }
}