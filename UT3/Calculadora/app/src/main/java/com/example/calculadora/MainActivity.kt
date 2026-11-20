package com.example.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.tooling.preview.Preview
import com.example.calculadora.ui.theme.CalculadoraTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraTheme {
                    SumadoraScreen()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SumadoraScreen() {
    // Estado: los campos trabajan con String y convertimos al sumar
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Sumadora") })
        }
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)   // respeta la barra superior
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = num1,
                // onValueChange: (String) -> Unit,
                onValueChange = { nuevo ->
                    if (nuevo.all { it.isDigit() }) num1 = nuevo
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Primer número") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number, // teclado numérico
                    imeAction = ImeAction.Next      // pasa al siguiente campo
                )
            )

            OutlinedTextField(
                value = num2,
                onValueChange = { nuevo ->
                    if (
                        nuevo.all {
                            // it es el parámetro de la función
                            // fun CharSequence.all(predicate: (Char) -> Boolean): Boolean
                            // comprueba para cada carácter (it) si es un dígito
                            it.isDigit() }) num2 = nuevo
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Segundo número") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done      // cierra el teclado
                )
            )

            Button(
                onClick = {
                    // toIntOrNull() devuelve null si el campo está vacío
                    // como se usó by, num1 se comporta como un String

                    val a = num1.toIntOrNull()
                    val b = num2.toIntOrNull()

                    // a  y b no son estado, son  simples variable locales.
                    // Pero cse  lee num1  y num2 dentro de la composición,
                    // Compose se suscribe al estado.
                    // Por ejemplo, cada vez que num1 cambia, la función se recompone y a se recalcula
                    // con el valor nuevo.
                    resultado = if (a != null && b != null) {
                        (a + b).toString()
                    } else {
                        "Introduce los dos números"
                    }
                }
            ) {
                Text("Sumar")
            }

            // Campo de solo lectura: no se puede editar, solo muestra el resultado
            OutlinedTextField(
                value = resultado,
                onValueChange = { },    // obligatorio, pero vacío al ser readOnly
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Resultado") },
                readOnly = true
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculadoraTheme {
        SumadoraScreen()
    }
}