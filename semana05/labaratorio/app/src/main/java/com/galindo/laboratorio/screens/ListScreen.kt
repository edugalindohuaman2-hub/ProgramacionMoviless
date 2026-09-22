package com.galindo.laboratorio.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.galindo.laboratorio.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    onNavigate: (String) -> Unit
) {

    val items = listOf(
        1 to "Producto 1",
        2 to "Producto 2",
        3 to "Producto 3",
        4 to "Producto 4",
        5 to "Producto 5"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Lista")
                }
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            items(items) { item ->

                Button(
                    onClick = {
                        onNavigate(
                            Screen.Detail.createRoute(item.first)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(item.second)
                }
            }
        }
    }
}