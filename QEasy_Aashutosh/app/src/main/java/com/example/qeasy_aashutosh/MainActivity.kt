package com.example.qeasy_aashutosh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.qeasy_aashutosh.ui.theme.QEasy_AashutoshTheme

sealed class Screen {
    object ForgetPassword : Screen()
    object OTP : Screen()
    object HomePage : Screen()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QEasy_AashutoshTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.ForgetPassword) }

    when (currentScreen) {
        is Screen.ForgetPassword -> {
            ForgetPasswordBody(
                onSendOtpClick = {
                    currentScreen = Screen.OTP
                },
                onBackToLoginClick = {
                    currentScreen = Screen.HomePage
                }
            )
        }
        is Screen.OTP -> {
            OTPBody(
                onVerifyClick = {
                    currentScreen = Screen.HomePage
                },
                onBackClick = {
                    currentScreen = Screen.ForgetPassword
                }
            )
        }
        is Screen.HomePage -> {
            HomePageBody()
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_7
)
@Composable
fun MainAppPreview() {
    QEasy_AashutoshTheme {
        MainApp()
    }
}
