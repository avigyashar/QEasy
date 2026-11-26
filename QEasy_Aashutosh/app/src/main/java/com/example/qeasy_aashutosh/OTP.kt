package com.example.qeasy_aashutosh

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class OTP : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OTPBody()
        }
    }
}

@Composable
fun OTPBody(
    onVerifyClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    val context = LocalContext.current

    var otpCode by remember {
        mutableStateOf("")
    }

    var secondsRemaining by remember {
        mutableIntStateOf(30)
    }

    var resendKey by remember {
        mutableIntStateOf(0)
    }

    val canResend = secondsRemaining == 0

    // Resend Countdown
    LaunchedEffect(resendKey) {
        secondsRemaining = 30
        while (secondsRemaining > 0) {
            delay(1000)
            secondsRemaining--
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFAFAFC))
            .statusBarsPadding()
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // Back Arrow Button
        Box(
            modifier = Modifier
                .size(44.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFFE5E7EB),
                    shape = RoundedCornerShape(12.dp)
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable {
                    onBackClick()
                }
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "Back",
                modifier = Modifier.fillMaxSize(),
                tint = Color(0xFF111827)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Message Icon Box
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(
                    color = Color(0xFFEBF3FE),
                    shape = RoundedCornerShape(14.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_message),
                contentDescription = "Message",
                tint = Color(0xFF3B82F6),
                modifier = Modifier.size(26.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Title
        Text(
            text = "Verify OTP",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827)
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Subtitle Description
        Text(
            text = "Enter the 6-digit code we sent to",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF6B7280)
            )
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "98XXXXXXXX ",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF111827)
                )
            )

            Text(
                text = "Change",
                modifier = Modifier.clickable {
                    Toast.makeText(
                        context,
                        "Change phone number clicked",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3B82F6)
                )
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        // 6-digit OTP Display Row
        OtpInputRow(
            otpValue = otpCode,
            onOtpChange = { index, value ->
                if (value.isNotEmpty()) {
                    otpCode = otpCode.take(index) + value
                } else {
                    otpCode = otpCode.take(index)
                }
            },
            onBackspace = { index ->
                if (index > 0) {
                    otpCode = otpCode.dropLast(1)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Didn't get code / Resend in row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Didn't get the code?",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF6B7280)
                )
            )

            Text(
                text = if (canResend) {
                    "Resend now"
                } else {
                    "Resend in 00:${secondsRemaining.toString().padStart(2, '0')}"
                },
                style = TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF4B5563)
                )
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Verify Button
        Button(
            onClick = {
                if (otpCode.length == 6) {
                    onVerifyClick()
                } else {
                    Toast.makeText(
                        context,
                        "Please enter the complete 6-digit OTP",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4385F5)
            )
        ) {
            Text(
                text = "Verify",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // "or" Divider Text
        Text(
            text = "or",
            style = TextStyle(
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF9CA3AF)
            ),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Resend OTP Button
        OutlinedButton(
            onClick = {
                if (canResend) {
                    otpCode = ""
                    resendKey++
                    Toast.makeText(
                        context,
                        "New OTP requested",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            enabled = canResend,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White,
                contentColor = Color(0xFF4385F5),
                disabledContentColor = Color(0xFF9CA3AF)
            ),
            border = BorderStroke(
                1.dp,
                Color(0xFFE5E7EB)
            )
        ) {
            Text(
                text = "Resend OTP",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

// OTP INPUT ROW
@Composable
fun OtpInputRow(
    otpValue: String,
    onOtpChange: (Int, String) -> Unit,
    onBackspace: (Int) -> Unit
) {
    val focusRequesters = remember {
        List(6) { FocusRequester() }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(6) { index ->
            OtpSingleBox(
                modifier = Modifier.weight(1f),
                otpValue = otpValue,
                index = index,
                isFocused = otpValue.length == index,
                focusRequester = focusRequesters[index],
                onOtpChange = onOtpChange,
                onNext = {
                    if (index < 5) {
                        focusRequesters[index + 1].requestFocus()
                    }
                },
                onBackspace = {
                    if (index > 0) {
                        onBackspace(index)
                        focusRequesters[index - 1].requestFocus()
                    }
                }
            )
        }
    }
}

// INDIVIDUAL OTP BOX
@Composable
fun OtpSingleBox(
    modifier: Modifier = Modifier,
    otpValue: String,
    index: Int,
    isFocused: Boolean,
    focusRequester: FocusRequester,
    onOtpChange: (Int, String) -> Unit,
    onNext: () -> Unit,
    onBackspace: () -> Unit
) {
    val char = if (index < otpValue.length) {
        otpValue[index].toString()
    } else {
        ""
    }

    Box(
        modifier = modifier
            .height(56.dp)
            .background(
                Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                width = if (isFocused) 2.dp else 1.dp,
                color = if (isFocused) {
                    Color(0xFF3B82F6)
                } else {
                    Color(0xFFE5E7EB)
                },
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = char,
            onValueChange = { newValue ->
                val digit = newValue
                    .filter { it.isDigit() }
                    .take(1)

                onOtpChange(index, digit)

                if (digit.isNotEmpty()) {
                    onNext()
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .focusRequester(focusRequester)
                .onKeyEvent { event ->
                    if (
                        event.key == Key.Backspace &&
                        event.type == KeyEventType.KeyDown &&
                        char.isEmpty()
                    ) {
                        onBackspace()
                        true
                    } else {
                        false
                    }
                },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            textStyle = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827),
                textAlign = TextAlign.Center
            ),
            cursorBrush = SolidColor(Color(0xFF3B82F6)),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    innerTextField()
                }
            }
        )
    }
}

// PREVIEW
@Preview(showBackground = true)
@Composable
fun OTPPreview() {
    OTPBody()
}