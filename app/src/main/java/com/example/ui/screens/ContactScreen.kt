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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.ui.components.EnquiryFormCard
import com.example.ui.theme.BrandAccentRed
import com.example.ui.theme.BrandBodyText
import com.example.ui.theme.BrandBorder
import com.example.ui.theme.BrandCorporateBlue
import com.example.ui.theme.BrandLightBg
import com.example.ui.theme.BrandNavy
import com.example.ui.theme.BrandSkyTint
import com.example.ui.theme.BrandWhatsAppGreen
import com.example.ui.theme.BrandWhite
import com.example.ui.viewmodel.MainViewModel

@Composable
fun ContactScreen(
    viewModel: MainViewModel
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BrandLightBg)
            .testTag("contact_screen_lazy_column")
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
                    Text(
                        text = "Contact MADLY SERVICES",
                        color = BrandWhite,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Connect with Ravi Biyyala and our technical team in Hyderabad for consultations, quotes, or emergency support.",
                        color = Color(0xFFBACDE8),
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )
                }
            }
        }

        // Quick Direct Contact Cards (Call & WhatsApp)
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Call Card
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { BusinessConfig.openDialer(context, BusinessConfig.PHONE_NUMBER) }
                            .testTag("contact_call_card"),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = BrandWhite),
                        border = BorderStroke(1.dp, BrandBorder)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(BrandSkyTint),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Call, contentDescription = null, tint = BrandCorporateBlue)
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Direct Call", fontSize = 12.sp, color = BrandBodyText)
                            Text(text = BusinessConfig.PHONE_NUMBER, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = BrandNavy)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "Tap to Dial", fontSize = 11.sp, color = BrandCorporateBlue, fontWeight = FontWeight.SemiBold)
                        }
                    }

                    // WhatsApp Card
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { BusinessConfig.openWhatsApp(context) }
                            .testTag("contact_whatsapp_card"),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = BrandWhite),
                        border = BorderStroke(1.dp, BrandBorder)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color(0xFFE8F9EF)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(painterResource(id = R.drawable.ic_whatsapp), contentDescription = null, tint = BrandWhatsAppGreen)
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "WhatsApp Chat", fontSize = 12.sp, color = BrandBodyText)
                            Text(text = BusinessConfig.WHATSAPP_NUMBER, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = BrandNavy)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "Tap to Chat", fontSize = 11.sp, color = BrandWhatsAppGreen, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
        }

        // Business Hours & Location Info
        item {
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = BrandWhite),
                border = BorderStroke(1.dp, BrandBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Operational Details",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = BrandNavy
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Person, contentDescription = null, tint = BrandCorporateBlue, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(text = "Business Representative", fontSize = 11.sp, color = BrandBodyText)
                            Text(text = BusinessConfig.CONTACT_PERSON, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = BrandNavy)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.LocationOn, contentDescription = null, tint = BrandCorporateBlue, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(text = "Primary Service Coverage Area", fontSize = 11.sp, color = BrandBodyText)
                            Text(text = BusinessConfig.PRIMARY_SERVICE_AREA, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = BrandNavy)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(verticalAlignment = Alignment.Top) {
                        Icon(Icons.Default.AccessTime, contentDescription = null, tint = BrandCorporateBlue, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(text = "Working Hours", fontSize = 11.sp, color = BrandBodyText)
                            Text(text = BusinessConfig.BUSINESS_HOURS, fontSize = 12.sp, color = BrandNavy, lineHeight = 16.sp)
                        }
                    }
                }
            }
        }

        // Embedded Enquiry Form
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                EnquiryFormCard(
                    viewModel = viewModel,
                    title = "Send an Enquiry or Service Request",
                    subtitle = "Provide your requirements and preferred contact method. We will reach back promptly."
                )
            }
        }

        // Service Area Neighborhoods Chip Grid
        item {
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = BrandSkyTint),
                border = BorderStroke(1.dp, Color(0xFFBED5F3)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Active Hyderabad Service Clusters",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = BrandNavy
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Our technicians actively service commercial and residential premises across:",
                        fontSize = 12.sp,
                        color = BrandBodyText
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    val areas = listOf("Madhapur", "Gachibowli", "Hitec City", "Kondapur", "Banjara Hills", "Jubilee Hills", "Secunderabad", "Kukatpally", "Begumpet", "Ameerpet", "Dilsukhnagar", "Uppal")

                    Column {
                        areas.chunked(3).forEach { row ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 3.dp),
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                row.forEach { area ->
                                    Surface(
                                        shape = RoundedCornerShape(12.dp),
                                        color = BrandWhite,
                                        border = BorderStroke(1.dp, Color(0xFFD0D5DD)),
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Text(
                                            text = area,
                                            fontSize = 11.sp,
                                            color = BrandNavy,
                                            fontWeight = FontWeight.Medium,
                                            modifier = Modifier.padding(vertical = 4.dp, horizontal = 6.dp),
                                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                        )
                                    }
                                }
                            }
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
