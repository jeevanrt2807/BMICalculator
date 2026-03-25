package com.s3340278jeevan.bmicalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.s3340278jeevan.bmicalculator.ui.theme.BMICalculatorTheme
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BMICalculatorTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigationMain()
                }
            }
        }
    }
}

@Composable
fun AppNavigationMain() {

    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigate = {
                    if (UserDetails.getUserLoginStatus(context)) {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    } else {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    }
                }
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {

                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onBackToLogin = {
                    navController.popBackStack()
                }
            )
        }

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
            ProfileScreen(navController)
        }
        composable(Screen.About.route) {
            AboutScreen(navController)
        }
    }
}

@Composable
fun SplashScreen(onNavigate: () -> Unit) {

    LaunchedEffect(Unit) {
        delay(3000)
        onNavigate()
    }

    SplashScreen()


}


@Composable
fun SplashScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color(0xFF1976D2),
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.weight(1f))



        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.white),
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                modifier = Modifier
                    .size(70.dp),
                painter = painterResource(id = R.drawable.bmi_logo),
                contentDescription = "BMI Calculator"
            )

            Text(
                text = "BMI Calculator App",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )

            Text(
                text = "By",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )

            Text(
                text = "Jeevan Reddy",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )

        }




        Spacer(modifier = Modifier.weight(1f))

    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}