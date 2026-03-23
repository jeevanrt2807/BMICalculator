package com.example.bmicalculator

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


sealed class Screen(val route: String) {
    object Home : Screen("home")
    object BMICalculator : Screen("bmi_calculator")
    object BMICategory : Screen("bmi_category")
    object Profile : Screen("profile")
    object About : Screen("about")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.BMICalculator.route) {
            BMICalculatorScreen()
        }
        composable(Screen.BMICategory.route) {
            BMICategoryScreen()
        }
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
        composable(Screen.About.route) {
            AboutScreen()
        }
    }
}

data class HomeItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val items = listOf(
        HomeItem("BMI Calculator", Icons.Default.Home, Screen.BMICalculator.route),
        HomeItem("Category Guide", Icons.Default.Info, Screen.BMICategory.route),
        HomeItem("Profile", Icons.Default.Person, Screen.Profile.route),
        HomeItem("About Us", Icons.Default.Home, Screen.About.route)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("BMI Health App") }
            )
        }
    ) { padding ->


        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                HomeCard(
                    item = items[0],
                    modifier = Modifier.weight(1f),
                    onClick = {
                        navController.navigate(items[0].route)
                    }
                )

                HomeCard(
                    item = items[1],
                    modifier = Modifier.weight(1f),
                    onClick = {
                        navController.navigate(items[1].route)
                    }
                )
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                HomeCard(
                    item = items[2],
                    modifier = Modifier.weight(1f),
                    onClick = {
                        navController.navigate(items[0].route)
                    }
                )

                HomeCard(
                    item = items[3],
                    modifier = Modifier.weight(1f),
                    onClick = {
                        navController.navigate(items[1].route)
                    }
                )
            }
        }
    }
}

@Composable
fun HomeCard(item: HomeItem,modifier: Modifier, onClick: () -> Unit) {

    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(150.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = item.icon,
                contentDescription = item.title,
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = item.title,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun BMICalculatorScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("BMI Calculator Screen")
    }
}

@Composable
fun BMICategoryScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("BMI Category Guide Screen")
    }
}

@Composable
fun ProfileScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Profile Screen")
    }
}

@Composable
fun AboutScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("About Us Screen")
    }
}