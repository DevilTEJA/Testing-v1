package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.config.BusinessConfig
import com.example.data.model.ServiceCatalog
import com.example.ui.theme.BrandAccentRed
import com.example.ui.theme.BrandBodyText
import com.example.ui.theme.BrandBorder
import com.example.ui.theme.BrandCorporateBlue
import com.example.ui.theme.BrandLightBg
import com.example.ui.theme.BrandNavy
import com.example.ui.theme.BrandSkyTint
import com.example.ui.theme.BrandWhatsAppGreen
import com.example.ui.theme.BrandWhite

@Composable
fun AboutScreen(
    onNavigateToService: (String) -> Unit,
    onContact: () -> Unit
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BrandLightBg)
            .testTag("about_screen_lazy_column")
    ) {
        // Hero Header
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BrandNavy)
                    .padding(horizontal = 16.dp, vertical = 26.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_madly_badge),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(36.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "About MADLY SERVICES",
                            color = BrandWhite,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Your One-Stop Solution Partner | Reliable Services for a Better Tomorrow",
                        color = Color(0xFFBACDE8),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        // Company Introduction
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = BrandWhite),
                border = BorderStroke(1.dp, BrandBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "Company Introduction",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = BrandNavy
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "MADLY SERVICES brings together multiple service categories under one business, helping individuals and businesses explore practical solutions for insurance, connectivity, security, networking equipment, and restaurant billing technology.",
                        fontSize = 13.sp,
                        color = BrandBodyText,
                        lineHeight = 19.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Our goal is to simplify service discovery, provide clear information, and make it easier for customers to request the assistance they need.",
                        fontSize = 13.sp,
                        color = BrandBodyText,
                        lineHeight = 19.sp
                    )
                }
            }
        }

        // Brand Mission & Core Approach
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Our Core Principles",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrandNavy
                )
                Spacer(modifier = Modifier.height(10.dp))

                PrincipleCard(
                    icon = Icons.Default.Shield,
                    title = "Customer-Focused Approach",
                    desc = "Every home and business is distinct. We listen to your specific constraints, budget, and operational goals before suggesting any solution."
                )

                PrincipleCard(
                    icon = Icons.Default.CheckCircle,
                    title = "Practical & Reliable Service Delivery",
                    desc = "We prioritize stability, authentic components, tidy cable routing, and transparent guidance over superficial sales pitches."
                )

                PrincipleCard(
                    icon = Icons.Default.Visibility,
                    title = "Honest Clarity & Transparency",
                    desc = "Clear explanations of features, hardware warranties, renewal timelines, and regulatory insurance disclosures without hidden fine print."
                )
            }
        }

        // Service Categories Summary
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 18.dp)
            ) {
                Text(
                    text = "Our 5 Service Categories",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrandNavy
                )
                Spacer(modifier = Modifier.height(10.dp))

                ServiceCatalog.services.forEach { s ->
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = BrandWhite),
                        border = BorderStroke(1.dp, BrandBorder),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable { onNavigateToService(s.id) }
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = s.iconRes),
                                contentDescription = null,
                                modifier = Modifier.size(28.dp),
                                tint = Color.Unspecified
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = s.title,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = BrandNavy
                                )
                                Text(
                                    text = s.targetAudience,
                                    fontSize = 11.sp,
                                    color = BrandCorporateBlue
                                )
                            }
                        }
                    }
                }
            }
        }

        // Business Contact & Primary Service Area
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = BrandSkyTint),
                border = BorderStroke(1.dp, Color(0xFFBED5F3)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "Business Information & Area",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = BrandNavy
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Person, contentDescription = null, tint = BrandCorporateBlue, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Business Contact: ${BusinessConfig.CONTACT_PERSON}", fontSize = 13.sp, color = BrandNavy, fontWeight = FontWeight.SemiBold)
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.LocationOn, contentDescription = null, tint = BrandCorporateBlue, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Primary Service Area: ${BusinessConfig.PRIMARY_SERVICE_AREA}", fontSize = 13.sp, color = BrandNavy)
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Serving residential and commercial clients across Madhapur, Gachibowli, Banjara Hills, Jubilee Hills, Secunderabad, Kukatpally, Begumpet, Kondapur, and Greater Hyderabad.",
                        fontSize = 11.sp,
                        color = BrandBodyText,
                        lineHeight = 15.sp,
                        modifier = Modifier.padding(start = 26.dp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(
                            onClick = { BusinessConfig.openDialer(context) },
                            colors = ButtonDefaults.buttonColors(containerColor = BrandNavy, contentColor = BrandWhite),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Call Us", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }

                        Button(
                            onClick = { BusinessConfig.openWhatsApp(context) },
                            colors = ButtonDefaults.buttonColors(containerColor = BrandWhatsAppGreen, contentColor = BrandWhite),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(painterResource(id = R.drawable.ic_whatsapp), contentDescription = null, modifier = Modifier.size(14.dp), tint = BrandWhite)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("WhatsApp", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
private fun PrincipleCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    desc: String
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = BrandWhite),
        border = BorderStroke(1.dp, BrandBorder),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.Top) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(BrandSkyTint),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = BrandCorporateBlue, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = title, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = BrandNavy)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = desc, fontSize = 12.sp, color = BrandBodyText, lineHeight = 16.sp)
            }
        }
    }
}
