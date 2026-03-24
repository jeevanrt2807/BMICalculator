package com.example.bmicalculator

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
            BMICalculatorScreen(onBack = {
                navController.popBackStack()
            })
        }
        composable(Screen.BMICategory.route) {
            BMICategoryScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
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
    val icon: Int,
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val items = listOf(
        HomeItem("BMI Calculator", R.drawable.ic_calculate_bmi, Screen.BMICalculator.route),
        HomeItem("Category Guide", R.drawable.ic_category_guide, Screen.BMICategory.route),
        HomeItem("Profile", R.drawable.ic_profile, Screen.Profile.route),
        HomeItem("About Us", R.drawable.ic_aboutus, Screen.About.route)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("BMI Health App") },
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
                        navController.navigate(items[2].route)
                    }
                )

                HomeCard(
                    item = items[3],
                    modifier = Modifier.weight(1f),
                    onClick = {
                        navController.navigate(items[3].route)
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

            Image(
                painter = painterResource(id = item.icon),
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