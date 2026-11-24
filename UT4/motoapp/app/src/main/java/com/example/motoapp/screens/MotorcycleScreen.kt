package com.example.motoapp.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Motorcycle(
    val id: Int,
    val brand: String,
    val model: String,
    val year: Int
)

val motorcycles = listOf(
    Motorcycle(1, "Honda", "CB500F", 2022),
    Motorcycle(2, "Yamaha", "MT-07", 2023),
    Motorcycle(3, "Kawasaki", "Z650", 2021),
    Motorcycle(4, "BMW", "F900R", 2024)
)

@Composable
fun MotorcyclesScreen(
    onMotorcycleClick: (Int) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Mis motocicletas",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {

            items(motorcycles) { motorcycle ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable {
                            onMotorcycleClick(motorcycle.id)
                        }
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = "${motorcycle.brand} ${motorcycle.model}",
                            style = MaterialTheme.typography.titleLarge
                        )

                        Text(
                            text = "Año: ${motorcycle.year}"
                        )
                    }
                }
            }
        }
    }
}