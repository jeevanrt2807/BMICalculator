package com.s3340278jeevan.bmicalculator


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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

data class BMICategory(
    val title: String,
    val range: String,
    val color: Color,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val foods: List<String>,
    val exercises: List<String>,
    val tips: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMICategoryScreen(onBack: () -> Unit) {

    var selectedCategory by remember { mutableStateOf<BMICategory?>(null) }

    val categories = listOf(
        BMICategory(
            "Underweight",
            "< 18.5",
            Color(0xFF64B5F6),
            Icons.Default.Warning,
            listOf("Nuts", "Milk", "Rice", "Peanut Butter"),
            listOf("Strength Training", "Yoga"),
            "Increase calorie intake and focus on protein-rich foods."
        ),
        BMICategory(
            "Normal",
            "18.5 - 24.9",
            Color(0xFF4CAF50),
            Icons.Default.CheckCircle,
            listOf("Fruits", "Vegetables", "Whole Grains"),
            listOf("Jogging", "Cycling", "Yoga"),
            "Maintain balanced diet and regular exercise."
        ),
        BMICategory(
            "Overweight",
            "25 - 29.9",
            Color(0xFFFFA726),
            Icons.Default.Info,
            listOf("Oats", "Green Veggies", "Fruits"),
            listOf("Walking", "Cardio", "Cycling"),
            "Reduce calorie intake and increase physical activity."
        ),
        BMICategory(
            "Obese",
            "30+",
            Color(0xFFE53935),
            Icons.Default.Close,
            listOf("Salads", "High Fiber", "Lean Protein"),
            listOf("Walking", "Swimming", "Light HIIT"),
            "Consult doctor if needed. Focus on gradual weight loss."
        )
    )

    if (selectedCategory == null) {

        Scaffold(
            topBar = {
                TopAppBar(title = { Text("BMI Category Guide") },
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


                categories.forEach { category ->
                    CategoryCard(category) {
                        selectedCategory = category
                    }
                }
            }
        }

    } else {
        CategoryDetailScreen(
            category = selectedCategory!!,
            onBack = { selectedCategory = null }
        )
    }
}

@Composable
fun CategoryCard(category: BMICategory, onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = MaterialTheme.shapes.extraLarge,
        colors = CardDefaults.cardColors(
            containerColor = category.color.copy(alpha = 0.08f)
        )
    ) {

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                category.icon,
                contentDescription = null,
                tint = category.color,
                modifier = Modifier.size(36.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {

                Text(
                    category.title,
                    fontWeight = FontWeight.Bold,
                    color = category.color
                )

                Text(
                    "BMI: ${category.range}",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Icon(Icons.Default.ArrowForward, contentDescription = null)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailScreen(category: BMICategory, onBack: () -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(category.title) },
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

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = category.color.copy(alpha = 0.1f)
                ),
                shape = MaterialTheme.shapes.extraLarge
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        category.icon,
                        contentDescription = null,
                        tint = category.color,
                        modifier = Modifier.size(60.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        category.title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = category.color
                    )

                    Text("BMI Range: ${category.range}")
                }
            }

            DetailSection("Recommended Foods 🍎", category.foods)

            DetailSection("Exercises 🏃", category.exercises)

            Card(shape = MaterialTheme.shapes.large) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)) {
                    Text("Tips 💡", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(category.tips)
                }
            }
        }
    }
}

@Composable
fun DetailSection(title: String, items: List<String>) {

    Card(shape = MaterialTheme.shapes.large) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {

            Text(title, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(8.dp))

            items.forEach {
                Text("• $it")
            }
        }
    }
}