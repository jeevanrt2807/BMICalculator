package com.example.bmicalculator


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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

            // HEIGHT
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

            // WEIGHT + AGE
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

            // GENDER
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

            // BUTTON
            Button(
                onClick = {
                    val hFeet = feet.toFloatOrNull() ?: 0f
                    val hInches = inches.toFloatOrNull() ?: 0f
                    val w = weight.toFloatOrNull() ?: 0f

                    val totalInches = (hFeet * 12) + hInches

                    if (totalInches > 0 && w > 0) {
                        val bmi = (w / totalInches.pow(2)) * 703
                        bmiResult = String.format("%.2f", bmi)

                        bmiCategory = when {
                            bmi < 18.5 -> "Underweight"
                            bmi < 24.9 -> "Normal"
                            bmi < 29.9 -> "Overweight"
                            else -> "Obese"
                        }
                    } else {
                        bmiResult = "Invalid"
                        bmiCategory = ""
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large
            ) {
                Text("Calculate BMI")
            }

            // RESULT CARD
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