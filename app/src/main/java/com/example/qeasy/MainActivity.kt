
package com.example.qeasy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.border
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Navy = Color(0xFF19365F)
private val Blue = Color(0xFF3478D4)
private val Background = Color(0xFFF3F6FB)
private val Muted = Color(0xFF718096)
private val LightBlue = Color(0xFFE8F0FE)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var showQueueDetails by remember {
                mutableStateOf(true)
            }

            MaterialTheme {
                if (showQueueDetails) {
                    QEasyQueueDetails(
                        onBackClick = {
                            showQueueDetails = false
                        }
                    )
                } else {
                    QEasyProfile()
                }
            }
        }
    }
}

@Composable
fun QEasyProfile() {
    var isEditing by remember { mutableStateOf(false) }

    var fullName by remember { mutableStateOf("Your Name") }
    var mobile by remember { mutableStateOf("9812345678") }
    var email by remember { mutableStateOf("you@example.com") }

    var draftName by remember { mutableStateOf(fullName) }
    var draftMobile by remember { mutableStateOf(mobile) }
    var draftEmail by remember { mutableStateOf(email) }

    var hasAttemptedSave by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }

    val validName = draftName.trim().length >= 2
    val validMobile = draftMobile.matches(Regex("^[0-9+() -]{7,15}$"))
    val validEmail = android.util.Patterns.EMAIL_ADDRESS
        .matcher(draftEmail.trim()).matches()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "QEasy",
                color = Blue,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "MY ACCOUNT",
                color = Muted,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = if (isEditing) "Edit Profile" else "My Profile",
            color = Navy,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(7.dp))

        Text(
            text = if (isEditing)
                "Update your personal information."
            else
                "View and manage your personal details.",
            color = Muted,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(25.dp))

        // Profile card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(3.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(contentAlignment = Alignment.BottomEnd) {

                    Surface(
                        modifier = Modifier.size(100.dp),
                        shape = CircleShape,
                        color = LightBlue
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = fullName.trim()
                                    .split(" ")
                                    .filter { it.isNotBlank() }
                                    .take(2)
                                    .joinToString("") {
                                        it.first().uppercase()
                                    },
                                color = Blue,
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Surface(
                        modifier = Modifier
                            .size(30.dp)
                            .border(2.dp, Color.White, CircleShape),
                        shape = CircleShape,
                        color = Blue
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = "✎",
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = fullName,
                    color = Navy,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "QEasy Member",
                    color = Muted,
                    fontSize = 13.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (!isEditing) {

            // Personal information
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Personal Information",
                        color = Navy,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(22.dp))

                    DetailRow("Full Name", fullName)
                    DetailRow("Mobile Number", mobile)
                    DetailRow("Email Address", email)
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = {
                    draftName = fullName
                    draftMobile = mobile
                    draftEmail = email
                    hasAttemptedSave = false
                    message = ""
                    isEditing = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue
                )
            ) {
                Text(
                    text = "Edit Profile",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

        } else {

            // Edit information form
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Update Information",
                        color = Navy,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = draftName,
                        onValueChange = { draftName = it },
                        label = { Text("Full Name") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        isError = hasAttemptedSave && !validName,
                        supportingText = {
                            if (hasAttemptedSave && !validName) {
                                Text("Enter at least 2 characters.")
                            }
                        },
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = draftMobile,
                        onValueChange = { draftMobile = it },
                        label = { Text("Mobile Number") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone
                        ),
                        isError = hasAttemptedSave && !validMobile,
                        supportingText = {
                            if (hasAttemptedSave && !validMobile) {
                                Text("Enter a valid mobile number.")
                            }
                        },
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = draftEmail,
                        onValueChange = { draftEmail = it },
                        label = { Text("Email Address") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),
                        isError = hasAttemptedSave && !validEmail,
                        supportingText = {
                            if (hasAttemptedSave && !validEmail) {
                                Text("Enter a valid email address.")
                            }
                        },
                        shape = RoundedCornerShape(12.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    hasAttemptedSave = true

                    if (validName && validMobile && validEmail) {
                        fullName = draftName.trim()
                        mobile = draftMobile.trim()
                        email = draftEmail.trim()
                        isEditing = false
                        message = "Profile updated successfully."
                        hasAttemptedSave = false
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue
                )
            ) {
                Text(
                    text = "Save Changes",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = {
                    draftName = fullName
                    draftMobile = mobile
                    draftEmail = email
                    hasAttemptedSave = false
                    isEditing = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text(
                    text = "Cancel",
                    color = Navy,
                    fontSize = 15.sp
                )
            }
        }

        if (message.isNotEmpty()) {
            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = message,
                color = Color(0xFF16804A),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth()) {

        Text(
            text = label,
            color = Muted,
            fontSize = 13.sp
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = value,
            color = Navy,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(19.dp))
    }
}

@Composable
fun QEasyQueueDetails(
    onBackClick: () -> Unit = {},
    onJoinQueueClick: () -> Unit = {}
) {
    val navy = Color(0xFF19365F)
    val blue = Color(0xFF3478D4)
    val muted = Color(0xFF718096)
    val background = Color(0xFFF8FAFD)
    val green = Color(0xFF16804A)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        // Header
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = onBackClick) {
                Text(
                    text = "‹",
                    fontSize = 32.sp,
                    color = navy
                )
            }

            Text(
                text = "Queue details",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                color = navy
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Clinic banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(
                    Color(0xFFEBF3FF),
                    RoundedCornerShape(18.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                modifier = Modifier.size(75.dp),
                shape = CircleShape,
                color = Color(0xFFD5E6FF)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = "+",
                        color = blue,
                        fontSize = 38.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "CityCare Clinic",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = navy
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "General outpatient department",
            fontSize = 14.sp,
            color = muted
        )

        Spacer(modifier = Modifier.height(14.dp))

        Surface(
            shape = RoundedCornerShape(20.dp),
            color = Color(0xFFE4F5EA)
        ) {
            Text(
                text = "Remote joining available",
                modifier = Modifier.padding(
                    horizontal = 14.dp,
                    vertical = 8.dp
                ),
                color = green,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(22.dp))
        HorizontalDivider(color = Color(0xFFE2E8F0))
        Spacer(modifier = Modifier.height(22.dp))

        // Queue status
        Text(
            text = "Currently serving",
            color = muted,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "A-042",
            color = navy,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Estimated wait",
            color = muted,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "18 min",
            color = blue,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(25.dp))
        HorizontalDivider(color = Color(0xFFE2E8F0))
        Spacer(modifier = Modifier.height(22.dp))

        // Service information
        Text(
            text = "Service information",
            color = navy,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(18.dp))

        QueueInfoRow("Open today", "9:00 AM – 5:00 PM")
        QueueInfoRow(
            "Waiting information",
            "14 people waiting · Approx. 6 min / turn"
        )
        QueueInfoRow("Location", "Baneshwor, Kathmandu")

        Spacer(modifier = Modifier.height(12.dp))

        // Priority assistance
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFEBF3FF)
            ),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Priority assistance available",
                    color = navy,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "For eligible visitors; staff will verify.",
                    color = muted,
                    fontSize = 13.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        // Join queue button
        Button(
            onClick = onJoinQueueClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = blue
            )
        ) {
            Text(
                text = "Join remote queue",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun QueueInfoRow(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 17.dp)
    ) {
        Text(
            text = label,
            color = Color(0xFF718096),
            fontSize = 13.sp
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = value,
            color = Color(0xFF19365F),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
