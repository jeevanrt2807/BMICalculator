package com.s3340278jeevan.bmicalculator

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.firebase.database.FirebaseDatabase


@Composable
fun RegisterScreen( onBackToLogin: () -> Unit) {
    var eventGuestError by remember { mutableStateOf("") }

    var eventGuestName by remember { mutableStateOf("") }
    var eventGuestMail by remember { mutableStateOf("") }
    var eventGuestPin by remember { mutableStateOf("") }
    var confirmEventGuestPin by remember { mutableStateOf("") }

    val genderOptions = listOf("Male", "Female", "Other")

    var selectedGender by remember { mutableStateOf("Male") }

    val currentActivity = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.weight(1f))

        Image(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.bmi_logo),
            contentDescription = "BMI Calculator"
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Register",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 32.dp).align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = eventGuestName,
            onValueChange = { eventGuestName = it },
            label = { Text("Enter Full Name") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle, // Replace with desired icon
                    contentDescription = "Email Icon"
                )
            },
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(text = "Select Gender", style = MaterialTheme.typography.bodyMedium)

        // Gender options as radio buttons

        Row(
            Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            genderOptions.forEach { gender ->
                RadioButton(
                    selected = (gender == selectedGender),
                    onClick = { selectedGender = gender }
                )
                Text(
                    text = gender,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(end = 6.dp)
                )
            }
        }


        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = eventGuestMail,
            onValueChange = { eventGuestMail = it },
            label = { Text("Enter E-Mail") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email, // Replace with desired icon
                    contentDescription = "Email Icon"
                )
            },
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = eventGuestPin,
            onValueChange = { eventGuestPin = it },
            label = { Text("Enter Password") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock, // Replace with desired icon
                    contentDescription = "Password Icon"
                )
            },
        )


        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = confirmEventGuestPin,
            onValueChange = { confirmEventGuestPin = it },
            label = { Text("Confirm Password") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock, // Replace with desired icon
                    contentDescription = "Password Icon"
                )
            },
        )

        Spacer(modifier = Modifier.height(36.dp))
        if (eventGuestError.isNotEmpty()) {
            Text(
                text = eventGuestError,
                color = Color.Red,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }


        Button(
            onClick = {
                when {
                    eventGuestName.isBlank() -> {
                        eventGuestError = "Better enter your name"
                    }

//                    isValidUsername(eventGuestName) -> {
//                        eventGuestError = "Enter valid name"
//                    }

                    eventGuestMail.isBlank() -> {
                        eventGuestError = "Better enter your mail"
                    }

                    validateEmail(eventGuestMail) -> {
                        eventGuestError = "Enter Valid Mail"
                    }

                    eventGuestPin.isBlank() -> {
                        eventGuestError = "Enter Password"
                    }

                    confirmEventGuestPin.isBlank() -> {
                        eventGuestError = "Confirm Password"
                    }

                    eventGuestPin != confirmEventGuestPin -> {
                        eventGuestError = "Ouch! Password seems wrong"

                    }

                    else -> {
                        eventGuestError = ""
                        val eventGuestData = UserData(
                            fullName = eventGuestName,
                            gender = selectedGender,
                            email = eventGuestMail,
                            password = eventGuestPin
                        )
                        doesUserExits(eventGuestData, currentActivity,onBackToLogin)
                    }
                }


            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp, 0.dp),
            shape = RoundedCornerShape(8.dp)
        )
        {
            Text("Register")
        }

        Spacer(modifier = Modifier.height(12.dp))


        Row(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        ) {
            Text(
                text = "I have an account / ",
                style = MaterialTheme.typography.bodyLarge,
            )

            Text(
                text = "Login Now",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Black),
                modifier = Modifier.clickable {
                    // Intent to open RegisterActivity

                    onBackToLogin.invoke()

//                    currentActivity.startActivity(
//                        Intent(
//                            currentActivity,
//                            LoginActivity::class.java
//                        )
//                    )
//                    currentActivity.finish()
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

    }
}

private fun doesUserExits(userData1: UserData, currentActivity: Activity,onBackToLogin: () -> Unit) {
    val databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        .child(userData1.email.replace(".", ","))

    databaseReference.get().addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val userData = task.result?.getValue(UserData::class.java)
            if (userData != null) {
                Toast.makeText(currentActivity, "User already exists", Toast.LENGTH_SHORT).show()
            } else {
                saveUserData(userData1, currentActivity,onBackToLogin)
            }
        } else {
            // Data retrieval failed
            Toast.makeText(
                currentActivity,
                "Failed to retrieve user data: ${task.exception?.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

private fun saveUserData(userData: UserData, currentActivity: Activity,onBackToLogin: () -> Unit) {
    val db = FirebaseDatabase.getInstance()
    val ref = db.getReference("Users")

    ref.child(userData.email.replace(".", ",")).setValue(userData)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(currentActivity, "Registration Successful", Toast.LENGTH_SHORT)
                    .show()

                onBackToLogin.invoke()
//                currentActivity.startActivity(Intent(currentActivity, LoginActivity::class.java))
//                currentActivity.finish()

            } else {
                Toast.makeText(
                    currentActivity,
                    "Account Registration Failed",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        .addOnFailureListener { _ ->
            Toast.makeText(
                currentActivity,
                "Account Registration Failed",
                Toast.LENGTH_SHORT
            ).show()
        }
}

fun isValidUsername(username: String): Boolean {
    val regex =
        "^[a-zA-Z]*$".toRegex() // Matches only alphabets, excluding spaces and other characters
    return !regex.matches(username)
}


fun validateEmail(mail: String): Boolean {
    val mailRegexPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
    return !mailRegexPattern.matches(mail)
}
