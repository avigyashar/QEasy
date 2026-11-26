package com.example.qeasy_aashutosh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class HomePage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomePageBody()
        }
    }
}

@Composable
fun HomePageBody() {
    var selectedBottomTab by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC)),
        containerColor = Color(0xFFF8FAFC),
        bottomBar = {
            BottomNavigationBar(
                selectedTab = selectedBottomTab,
                onTabSelected = { selectedBottomTab = it }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Header Section: Greeting & Profile Avatar
            HeaderSection()

            Spacer(modifier = Modifier.height(24.dp))

            // Active Queue Hero Card
            ActiveQueueCard()

            Spacer(modifier = Modifier.height(28.dp))

            // Section 2: What would you like to do?
            Text(
                text = "What would you like to do?",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Action Cards Grid (Find a queue & Scan QR)
            ActionCardsGrid()

            Spacer(modifier = Modifier.height(28.dp))

            // Section 3: Nearby services Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Nearby services",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A)
                )

                Text(
                    text = "See all",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF3B82F6),
                    modifier = Modifier.clickable { /* TODO: See all services */ }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nearby Service Card (Everest Bank)
            NearbyServiceCard()

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Good afternoon, Maya",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Friday, 6 November",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF64748B)
            )
        }

        // Avatar Circle with initials "MK"
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(Color(0xFFE0F2FE)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "MK",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0284C7)
            )
        }
    }
}

@Composable
fun ActiveQueueCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFF3B82F6))
            .clickable { /* TODO: Queue details */ }
            .padding(20.dp)
    ) {
        Column {
            Text(
                text = "YOUR ACTIVE QUEUE",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White.copy(alpha = 0.8f),
                letterSpacing = 0.5.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "CityCare Clinic",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "Token A-042 · General OPD",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White.copy(alpha = 0.9f)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Text(
                        text = "18 min",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "estimated time remaining",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White.copy(alpha = 0.85f)
                    )
                }

                ChevronRightIcon(
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun ActionCardsGrid() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Card 1: Find a queue
        Box(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp))
                .clickable { /* TODO: Find a queue */ }
                .padding(16.dp)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFEFF6FF)),
                    contentAlignment = Alignment.Center
                ) {
                    SearchIcon(
                        tint = Color(0xFF3B82F6),
                        modifier = Modifier.size(18.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Find a queue",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A)
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Join remotely",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF64748B)
                )
            }
        }

        // Card 2: Scan QR
        Box(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp))
                .clickable { /* TODO: Scan QR */ }
                .padding(16.dp)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFEFF6FF)),
                    contentAlignment = Alignment.Center
                ) {
                    QrGridIcon(
                        tint = Color(0xFF3B82F6),
                        modifier = Modifier.size(18.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Scan QR",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A)
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Join on-site",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF64748B)
                )
            }
        }
    }
}

@Composable
fun NearbyServiceCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp))
            .clickable { /* TODO: Service click */ }
            .padding(16.dp)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFEFF6FF)),
                    contentAlignment = Alignment.Center
                ) {
                    PlusIcon(
                        tint = Color(0xFF3B82F6),
                        modifier = Modifier.size(18.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "Everest Bank",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F172A)
                    )
                    Spacer(modifier = Modifier.height(1.dp))
                    Text(
                        text = "Customer service",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF64748B)
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 14.dp),
                color = Color(0xFFF1F5F9)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Now serving",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF64748B)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "B-018",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F172A)
                    )
                }

                Column {
                    Text(
                        text = "Est. wait",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF64748B)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "12 min",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2563EB)
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    Surface(
        color = Color.White,
        shadowElevation = 8.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Color(0xFFE2E8F0))
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val navItems = listOf("Home", "Explore", "My queues", "Profile")

            navItems.forEachIndexed { index, label ->
                val isSelected = selectedTab == index
                val color = if (isSelected) Color(0xFF2563EB) else Color(0xFF94A3B8)

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable { onTabSelected(index) }
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    when (index) {
                        0 -> HomeIcon(tint = color, modifier = Modifier.size(22.dp))
                        1 -> SearchIcon(tint = color, modifier = Modifier.size(22.dp))
                        2 -> ListIcon(tint = color, modifier = Modifier.size(22.dp))
                        else -> ProfileIcon(tint = color, modifier = Modifier.size(22.dp))
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = label,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                        color = color
                    )
                }
            }
        }
    }
}

// Custom Vector Canvas Icons
@Composable
fun ChevronRightIcon(tint: Color, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val path = Path().apply {
            moveTo(size.width * 0.35f, size.height * 0.2f)
            lineTo(size.width * 0.65f, size.height * 0.5f)
            lineTo(size.width * 0.35f, size.height * 0.8f)
        }
        drawPath(
            path = path,
            color = tint,
            style = Stroke(width = size.width * 0.12f, cap = StrokeCap.Round, join = StrokeJoin.Round)
        )
    }
}

@Composable
fun SearchIcon(tint: Color, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val radius = size.width * 0.32f
        val center = Offset(size.width * 0.42f, size.height * 0.42f)
        drawCircle(
            color = tint,
            radius = radius,
            center = center,
            style = Stroke(width = size.width * 0.12f)
        )
        drawLine(
            color = tint,
            start = Offset(center.x + radius * 0.7f, center.y + radius * 0.7f),
            end = Offset(size.width * 0.85f, size.height * 0.85f),
            strokeWidth = size.width * 0.12f,
            cap = StrokeCap.Round
        )
    }
}

@Composable
fun QrGridIcon(tint: Color, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val squareSize = size.width * 0.36f
        val gap = size.width * 0.14f

        drawRect(color = tint, topLeft = Offset(0f, 0f), size = Size(squareSize, squareSize))
        drawRect(color = tint, topLeft = Offset(squareSize + gap, 0f), size = Size(squareSize, squareSize))
        drawRect(color = tint, topLeft = Offset(0f, squareSize + gap), size = Size(squareSize, squareSize))
        drawRect(color = tint, topLeft = Offset(squareSize + gap, squareSize + gap), size = Size(squareSize, squareSize))
    }
}

@Composable
fun PlusIcon(tint: Color, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val strokeWidth = size.width * 0.14f
        drawLine(
            color = tint,
            start = Offset(size.width * 0.2f, size.height * 0.5f),
            end = Offset(size.width * 0.8f, size.height * 0.5f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.5f, size.height * 0.2f),
            end = Offset(size.width * 0.5f, size.height * 0.8f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
    }
}

@Composable
fun HomeIcon(tint: Color, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val path = Path().apply {
            moveTo(size.width * 0.5f, size.height * 0.15f)
            lineTo(size.width * 0.85f, size.height * 0.45f)
            lineTo(size.width * 0.85f, size.height * 0.85f)
            lineTo(size.width * 0.15f, size.height * 0.85f)
            lineTo(size.width * 0.15f, size.height * 0.45f)
            close()
        }
        drawPath(
            path = path,
            color = tint,
            style = Stroke(width = size.width * 0.11f, cap = StrokeCap.Round, join = StrokeJoin.Round)
        )
    }
}

@Composable
fun ListIcon(tint: Color, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val strokeWidth = size.width * 0.11f
        val lineStartY = size.height * 0.25f
        val lineSpacing = size.height * 0.25f

        for (i in 0..2) {
            val y = lineStartY + (i * lineSpacing)
            drawLine(
                color = tint,
                start = Offset(size.width * 0.2f, y),
                end = Offset(size.width * 0.8f, y),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )
        }
    }
}

@Composable
fun ProfileIcon(tint: Color, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val headRadius = size.width * 0.22f
        val headCenter = Offset(size.width * 0.5f, size.height * 0.32f)
        drawCircle(
            color = tint,
            radius = headRadius,
            center = headCenter,
            style = Stroke(width = size.width * 0.11f)
        )
        val bodyPath = Path().apply {
            moveTo(size.width * 0.2f, size.height * 0.85f)
            cubicTo(
                size.width * 0.2f, size.height * 0.62f,
                size.width * 0.8f, size.height * 0.62f,
                size.width * 0.8f, size.height * 0.85f
            )
        }
        drawPath(
            path = bodyPath,
            color = tint,
            style = Stroke(width = size.width * 0.11f, cap = StrokeCap.Round)
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_7
)
@Composable
fun HomePagePreview() {
    HomePageBody()
}
