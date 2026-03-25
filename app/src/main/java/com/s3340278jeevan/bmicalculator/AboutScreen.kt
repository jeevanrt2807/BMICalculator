package com.s3340278jeevan.bmicalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("About Us") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
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
                shape = MaterialTheme.shapes.extraLarge,
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE3F2FD)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Info,
                            contentDescription = null,
                            tint = Color(0xFF1976D2)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "About the App",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "BMI Health App helps users calculate their Body Mass Index (BMI) and understand their health condition. It provides useful guidance, category details, and health tips to maintain a healthy lifestyle.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Card(
                shape = MaterialTheme.shapes.extraLarge
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = "Our Goal 🎯",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "The goal of this application is to promote awareness about health and fitness. It helps users track their BMI and provides suggestions to improve their lifestyle through proper diet and exercise.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Card(
                shape = MaterialTheme.shapes.extraLarge,
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE8F5E9)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = null,
                            tint = Color(0xFF2E7D32)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Developer",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Developed by Jeevan Reddy",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "This app is built as part of a mobile application project using Jetpack Compose.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}