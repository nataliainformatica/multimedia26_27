package com.example.motoapp.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MotorcycleDetailScreen(
    motorcycleId: Int
) {

    val motorcycle = motorcycles.firstOrNull {
        // firstOrNull ==>Es una función que busca el primer elemento de una colección que cumple una condición.
        it.id == motorcycleId
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "Detalle de motocicleta",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (motorcycle != null) {

            Text("Identificador: ${motorcycle.id}")

            Text("Marca: ${motorcycle.brand}")

            Text("Modelo: ${motorcycle.model}")

            Text("Año: ${motorcycle.year}")

        } else {

            Text("No se ha encontrado la motocicleta")
        }
    }
}