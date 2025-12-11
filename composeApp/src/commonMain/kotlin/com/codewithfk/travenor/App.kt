package com.codewithfk.travenor

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.codewithfk.travenor.ui.signin.LoginScreen
import com.codewithfk.travenor.ui.signup.SignUpScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        SignUpScreen()
    }
}

