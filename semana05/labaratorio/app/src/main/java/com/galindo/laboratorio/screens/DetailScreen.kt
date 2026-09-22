package com.galindo.laboratorio.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailScreen(
    itemId: Int,
    onBack: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Card {

            Column(
                modifier = Modifier.padding(24.dp)
            ) {

                Text(
                    text = "Detalle del producto"
                )

                Text(
                    text = "ID: $itemId"
                )
            }
        }

        Button(
            onClick = onBack
        ) {
            Text("Regresar")
        }
    }
}