package com.example.languageLearning.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.languageLearning.HomeRoute
import com.example.languageLearning.LoginRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController) {
    val email = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = email.value, onValueChange = {
            email.value = it
        })
        Spacer(modifier = Modifier.height(10.dp))
        TextField(value = password.value, onValueChange = {
            password.value = it
        })
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = buildAnnotatedString {
                append("If already signIn")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Black)) {
                    append("Login")
                }
            },
            modifier = Modifier.clickable {
                navController.navigate(LoginRoute)
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = {
            navController.navigate(
                HomeRoute(
                    email = email.value, password = password.value
                )
            )
        }, modifier = Modifier.padding(bottom = 100.dp)) {
            Text(text = "Go to Home Screen")
        }
    }
}