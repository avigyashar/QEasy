package com.example.sandy.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val ScreenBackground = Color(0xFFF8FAFD)
private val PrimaryText = Color(0xFF172B43)
private val SecondaryText = Color(0xFF607289)
private val PrimaryBlue = Color(0xFF3D85F1)
private val BorderColor = Color(0xFFE2E9F1)

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginClick: (mobile: String, password: String) -> Unit = { _, _ -> },
    onGuestClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    onCreateAccountClick: () -> Unit = {}
) {
    var mobileNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ScreenBackground)
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(55.dp))

        // Title
        Text(
            text = "Welcome back",
            fontSize = 25.sp,
            fontWeight = FontWeight.W800,
            color = PrimaryText
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Subtitle
        Text(
            text = "Get updates for your queue, in one place.",
            fontSize = 13.sp,
            fontWeight = FontWeight.W400,
            color = SecondaryText
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Mobile Number Field Label
        Text(
            text = "Mobile number",
            fontSize = 12.sp,
            fontWeight = FontWeight.W700,
            color = PrimaryText
        )

        Spacer(modifier = Modifier.height(8.dp))

        CustomInputField(
            value = mobileNumber,
            onValueChange = { mobileNumber = it },
            placeholder = "98XXXXXXXX",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Password Field Label
        Text(
            text = "Password",
            fontSize = 12.sp,
            fontWeight = FontWeight.W700,
            color = PrimaryText
        )

        Spacer(modifier = Modifier.height(8.dp))

        CustomInputField(
            value = password,
            onValueChange = { password = it },
            placeholder = "Enter your password",
            isPassword = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Forgot password
        Text(
            text = "Forgot password?",
            fontSize = 13.sp,
            fontWeight = FontWeight.W700,
            color = PrimaryBlue,
            modifier = Modifier
                .align(Alignment.End)
                .clickable { onForgotPasswordClick() }
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Log in Button
        Button(
            onClick = { onLoginClick(mobileNumber, password) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryBlue,
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
        ) {
            Text(
                text = "Log in",
                fontSize = 15.sp,
                fontWeight = FontWeight.W700
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Or continue with text
        Text(
            text = "or continue with",
            fontSize = 13.sp,
            fontWeight = FontWeight.W400,
            color = SecondaryText,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Continue as guest Button
        OutlinedButton(
            onClick = onGuestClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, BorderColor),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White,
                contentColor = PrimaryText
            )
        ) {
            Text(
                text = "Continue as guest",
                fontSize = 14.sp,
                fontWeight = FontWeight.W700
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // New to QEasy? Create account
        Text(
            text = "New to QEasy? Create account",
            fontSize = 14.sp,
            fontWeight = FontWeight.W700,
            color = PrimaryBlue,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable { onCreateAccountClick() }
        )

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
private fun CustomInputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .border(1.dp, BorderColor, RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.CenterStart
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = {
                Text(
                    text = placeholder,
                    color = SecondaryText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            },
            textStyle = TextStyle(
                fontSize = 14.sp,
                color = PrimaryText,
                fontWeight = FontWeight.Normal
            ),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = keyboardOptions,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = PrimaryBlue
            )
        )
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun LoginScreenPreview() {
    MaterialTheme {
        LoginScreen()
    }
}
