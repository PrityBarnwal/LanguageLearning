package com.example.navigationapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.navigationapp.HomeRoute
import com.example.navigationapp.SignInRoute


@Composable
fun LoginScreen(navController: NavController) {
    val email = remember { mutableStateOf("") }
    var isValidEmail by remember { mutableStateOf(true) }

    val password = remember { mutableStateOf("") }
    var isValidPassword by remember { mutableStateOf(true) }
    var passwordErrorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email.value,
            onValueChange = {
                email.value = it
                isValidEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()
            },
            label = { Text("Email") },
            isError = !isValidEmail,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            keyboardActions = KeyboardActions(KeyboardActions.Default.onNext),
            shape = MaterialTheme.shapes.medium,
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = password.value,
            onValueChange = {
                password.value = it
            },
            label = { Text("Password") },
            isError = !isValidPassword,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions(KeyboardActions.Default.onDone),
            shape = MaterialTheme.shapes.medium, singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = buildAnnotatedString {
                append("If already login ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Black)) {
                    append("SignUp")
                }
            },
            modifier = Modifier.clickable {
                navController.navigate(SignInRoute)
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                isValidEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
                isValidPassword = validatePassword(password.value)

                if (isValidEmail && isValidPassword) {
                    navController.navigate(
                        HomeRoute(
                            email = email.value, password = password.value
                        )
                    )
                } else if (!isValidPassword) {
                    passwordErrorMessage =
                        "Password must be 8-20 characters long, start with a capital letter, and contain at least one special character and one number."
                }
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp, start = 50.dp, end = 50.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFF4755A3))
        ) {
            Text(text = "Go to Home Screen", color = Color.White)
        }

        if (!isValidEmail && email.value.isNotEmpty()) {
            Text(
                text = "Invalid email address",
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        if (!isValidPassword && password.value.isNotEmpty()) {
            Text(
                text = passwordErrorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

fun validatePassword(password: String): Boolean {
    val passwordPattern =
        Regex("(?=^.{8,}\$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*\$\"")

    return passwordPattern.matches(password)
}

