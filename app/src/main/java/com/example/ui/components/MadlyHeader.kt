package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MiscellaneousServices
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.config.BusinessConfig
import com.example.ui.theme.BrandAccentRed
import com.example.ui.theme.BrandCorporateBlue
import com.example.ui.theme.BrandNavy
import com.example.ui.theme.BrandWhite

@Composable
fun MadlyHeader(
    currentScreen: String,
    onNavigate: (String, String?) -> Unit,
    onOpenQuoteDialog: () -> Unit
) {
    var isMenuOpen by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BrandNavy)
    ) {
        // A. Announcement Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF061B40))
                .padding(horizontal = 12.dp, vertical = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Professional Solutions for Homes, Shops, Offices & Businesses | Contact MADLY SERVICES Today",
                color = BrandWhite.copy(alpha = 0.92f),
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        // B. Main Corporate Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // MADLY Logo + Title (Clicking navigates to Home)
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onNavigate("home", null) }
                    .padding(4.dp)
                    .testTag("logo_header_button"),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_madly_badge),
                    contentDescription = "MADLY SERVICES Logo",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(36.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "MADLY",
                            color = BrandWhite,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 17.sp,
                            letterSpacing = 0.5.sp
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "SERVICES",
                            color = BrandAccentRed,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp,
                            letterSpacing = 0.5.sp
                        )
                    }
                    Text(
                        text = "Your One-Stop Solution Partner",
                        color = Color(0xFFBACDE8),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }

            // Right Action Buttons: "Free Quote" CTA + Menu toggle
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                    onClick = onOpenQuoteDialog,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrandAccentRed,
                        contentColor = BrandWhite
                    ),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(
                        horizontal = 10.dp,
                        vertical = 6.dp
                    ),
                    modifier = Modifier
                        .height(34.dp)
                        .testTag("get_quote_header_button")
                ) {
                    Text(
                        text = "Get a Free Quote",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))

                IconButton(
                    onClick = { isMenuOpen = !isMenuOpen },
                    modifier = Modifier.testTag("menu_toggle_button")
                ) {
                    Icon(
                        imageVector = if (isMenuOpen) Icons.Default.Close else Icons.Default.Menu,
                        contentDescription = "Toggle Menu",
                        tint = BrandWhite
                    )
                }
            }
        }

        // Mobile Dropdown / Expandable Navigation Drawer
        AnimatedVisibility(visible = isMenuOpen) {
            Surface(
                color = Color(0xFF0C2E6B),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    NavItem(
                        title = "Home",
                        icon = Icons.Outlined.Home,
                        isSelected = currentScreen == "home",
                        onClick = {
                            isMenuOpen = false
                            onNavigate("home", null)
                        }
                    )
                    NavItem(
                        title = "Services Overview",
                        icon = Icons.Default.MiscellaneousServices,
                        isSelected = currentScreen == "services_overview",
                        onClick = {
                            isMenuOpen = false
                            onNavigate("services_overview", null)
                        }
                    )

                    // 5 Dedicated Services Sub-links
                    Column(modifier = Modifier.padding(start = 24.dp)) {
                        SubServiceItem("1. All Types of Insurance") {
                            isMenuOpen = false
                            onNavigate("service_detail", "insurance")
                        }
                        SubServiceItem("2. Network Maintenance") {
                            isMenuOpen = false
                            onNavigate("service_detail", "network")
                        }
                        SubServiceItem("3. CCTV Service & Maintenance") {
                            isMenuOpen = false
                            onNavigate("service_detail", "cctv")
                        }
                        SubServiceItem("4. Router Sales & Services") {
                            isMenuOpen = false
                            onNavigate("service_detail", "router")
                        }
                        SubServiceItem("5. Restaurant Billing Software") {
                            isMenuOpen = false
                            onNavigate("service_detail", "billing")
                        }
                    }

                    HorizontalDivider(
                        color = Color(0xFF1D4794),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    NavItem(
                        title = "About Us",
                        icon = Icons.Default.Info,
                        isSelected = currentScreen == "about",
                        onClick = {
                            isMenuOpen = false
                            onNavigate("about", null)
                        }
                    )
                    NavItem(
                        title = "Contact Us",
                        icon = Icons.Default.Phone,
                        isSelected = currentScreen == "contact",
                        onClick = {
                            isMenuOpen = false
                            onNavigate("contact", null)
                        }
                    )
                    NavItem(
                        title = "Privacy & Legal Terms",
                        icon = Icons.Default.Policy,
                        isSelected = currentScreen == "legal",
                        onClick = {
                            isMenuOpen = false
                            onNavigate("legal", null)
                        }
                    )
                    NavItem(
                        title = "Admin Lead Dashboard",
                        icon = Icons.Default.AdminPanelSettings,
                        isSelected = currentScreen == "admin",
                        onClick = {
                            isMenuOpen = false
                            onNavigate("admin", null)
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Contact preview chip
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFF071F49))
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF20B965))
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Hyderabad Support: ${BusinessConfig.PHONE_NUMBER}",
                            color = Color(0xFFE2E8F0),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun NavItem(
    title: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(if (isSelected) Color(0xFF16479C) else Color.Transparent)
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isSelected) BrandWhite else Color(0xFFB5CDF2),
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = title,
            color = if (isSelected) BrandWhite else Color(0xFFD4E2F8),
            fontSize = 14.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
        )
    }
}

@Composable
private fun SubServiceItem(
    title: String,
    onClick: () -> Unit
) {
    Text(
        text = title,
        color = Color(0xFFB0C9F0),
        fontSize = 13.sp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 6.dp)
    )
}
