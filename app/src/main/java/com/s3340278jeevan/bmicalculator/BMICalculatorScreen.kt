package com.s3340278jeevan.bmicalculator


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.pow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMICalculatorScreen(onBack: () -> Unit) {

    var feet by remember { mutableStateOf("") }
    var inches by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Male") }

    var bmiResult by remember { mutableStateOf("") }
    var bmiCategory by remember { mutableStateOf("") }

    var errorMessage by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("BMI Calculator") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1976D2),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "Check Your Health",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Card(shape = MaterialTheme.shapes.large) {
                Column(modifier = Modifier.padding(16.dp)) {

                    Text("Height", fontWeight = FontWeight.SemiBold)

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {

                        OutlinedTextField(
                            value = feet,
                            onValueChange = { feet = it },
                            label = { Text("Feet") },
                            modifier = Modifier.weight(1f)
                        )

                        OutlinedTextField(
                            value = inches,
                            onValueChange = { inches = it },
                            label = { Text("Inches") },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            Card(shape = MaterialTheme.shapes.large) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    OutlinedTextField(
                        value = weight,
                        onValueChange = { weight = it },
                        label = { Text("Weight (lbs)") },
                        modifier = Modifier.weight(1f)
                    )

                    OutlinedTextField(
                        value = age,
                        onValueChange = { age = it },
                        label = { Text("Age") },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    Text("Gender", fontWeight = FontWeight.SemiBold)

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {

                        listOf("Male", "Female").forEach { option ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.selectable(
                                    selected = (gender == option),
                                    onClick = { gender = option }
                                )
                            ) {
                                RadioButton(
                                    selected = (gender == option),
                                    onClick = { gender = option }
                                )
                                Text(option)
                            }
                        }
                    }
                }
            }

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }

            Button(
                onClick = {     val hFeet = feet.toIntOrNull()
                    val hInches = inches.toIntOrNull()
                    val w = weight.toFloatOrNull()
                    val a = age.toIntOrNull()

                    if (hFeet == null || hFeet !in 1..8) {
                        errorMessage = "Feet should be between 1 and 8"
                        return@Button
                    }

                    if (hInches == null || hInches !in 0..11) {
                        errorMessage = "Inches should be between 0 and 11"
                        return@Button
                    }

                    if (w == null || w !in 50f..500f) {
                        errorMessage = "Weight should be between 50 and 500 lbs"
                        return@Button
                    }

                    if (a == null || a !in 5..120) {
                        errorMessage = "Age should be between 5 and 120"
                        return@Button
                    }

                    errorMessage = ""

                    val totalInches = (hFeet * 12) + hInches
                    val bmi = (w / totalInches.toFloat().pow(2)) * 703

                    bmiResult = String.format("%.2f", bmi)

                    bmiCategory = when {
                        bmi < 18.5 -> "Underweight"
                        bmi < 24.9 -> "Normal"
                        bmi < 29.9 -> "Overweight"
                        else -> "Obese"
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large
            ) {
                Text("Calculate BMI")
            }

            if (bmiResult.isNotEmpty()) {

                val (color, icon) = when (bmiCategory) {
                    "Underweight" -> Color(0xFF64B5F6) to Icons.Default.Warning
                    "Normal" -> Color(0xFF4CAF50) to Icons.Default.CheckCircle
                    "Overweight" -> Color(0xFFFFA726) to Icons.Default.Info
                    else -> Color(0xFFE53935) to Icons.Default.Close
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f)),
                    shape = MaterialTheme.shapes.extraLarge
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Icon(
                            icon,
                            contentDescription = null,
                            tint = color,
                            modifier = Modifier.size(60.dp)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = bmiResult,
                            style = MaterialTheme.typography.displaySmall,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = bmiCategory,
                            style = MaterialTheme.typography.titleLarge,
                            color = color,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}