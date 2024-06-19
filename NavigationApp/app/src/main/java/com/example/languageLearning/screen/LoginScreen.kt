package com.example.languageLearning.screen

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
import com.example.languageLearning.HomeRoute
import com.example.languageLearning.SignInRoute


/*
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
*/

@Composable
fun LoginScreen(navController: NavController) {
    val phoneNumber = remember { mutableStateOf("") }
    var isValidPhoneNumber by remember { mutableStateOf(true) }

    val otp = remember { mutableStateOf("") }
    var isValidOtp by remember { mutableStateOf(true) }
    var otpErrorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = phoneNumber.value,
            onValueChange = {
                phoneNumber.value = it
                isValidPhoneNumber = it.length == 10 && it.all { char -> char.isDigit() }
            },
            label = { Text("Phone Number") },
            isError = !isValidPhoneNumber,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            keyboardActions = KeyboardActions(KeyboardActions.Default.onNext),
            shape = MaterialTheme.shapes.medium,
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = otp.value,
            onValueChange = {
                otp.value = it
            },
            label = { Text("OTP") },
            isError = !isValidOtp,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            keyboardActions = KeyboardActions(KeyboardActions.Default.onDone),
            shape = MaterialTheme.shapes.medium,
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = buildAnnotatedString {
                append("If already have an account ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Black)) {
                    append("SignIn")
                }
            },
            modifier = Modifier.clickable {
                navController.navigate(SignInRoute)
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                isValidPhoneNumber =
                    phoneNumber.value.length == 10 && phoneNumber.value.all { char -> char.isDigit() }
                isValidOtp = validateOtp(otp.value)

                if (isValidPhoneNumber && isValidOtp) {
                    navController.navigate(
                        HomeRoute(
                            email = phoneNumber.value, password = otp.value
                        )
                    )
                } else if (!isValidOtp) {
                    otpErrorMessage =
                        "Invalid OTP. Please enter the correct OTP sent to your phone number."
                }
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp, start = 50.dp, end = 50.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFF4755A3))
        ) {
            Text(text = "Go to Home Screen", color = Color.White)
        }

        if (!isValidPhoneNumber && phoneNumber.value.isNotEmpty()) {
            Text(
                text = "Invalid phone number",
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        if (!isValidOtp && otp.value.isNotEmpty()) {
            Text(
                text = otpErrorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

fun validateOtp(otp: String): Boolean {
    return otp.length == 6 && otp.all { char -> char.isDigit() }
}

