package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.config.BusinessConfig
import com.example.ui.theme.BrandBodyText
import com.example.ui.theme.BrandBorder
import com.example.ui.theme.BrandCorporateBlue
import com.example.ui.theme.BrandLightBg
import com.example.ui.theme.BrandNavy
import com.example.ui.theme.BrandWhite

@Composable
fun LegalScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Privacy Policy", "Terms & Conditions")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BrandLightBg)
            .testTag("legal_screen_lazy_column")
    ) {
        // Hero Header
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BrandNavy)
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                Column {
                    Text(
                        text = "Legal & Compliance",
                        color = BrandWhite,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "MADLY SERVICES | Customer Trust & Operational Policies",
                        color = Color(0xFFBACDE8),
                        fontSize = 12.sp
                    )
                }
            }
        }

        // Tab Selector
        item {
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = BrandWhite,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                        color = BrandCorporateBlue
                    )
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = {
                            Text(
                                text = title,
                                fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                                color = if (selectedTab == index) BrandNavy else BrandBodyText
                            )
                        }
                    )
                }
            }
        }

        // Content
        item {
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = BrandWhite),
                border = BorderStroke(1.dp, BrandBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    if (selectedTab == 0) {
                        PrivacyPolicyContent()
                    } else {
                        TermsConditionsContent()
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
private fun PrivacyPolicyContent() {
    Column {
        Text(text = "Privacy Policy", fontSize = 17.sp, fontWeight = FontWeight.Bold, color = BrandNavy)
        Text(text = "Last Updated: 2026 | MADLY SERVICES (Hyderabad, India)", fontSize = 11.sp, color = Color(0xFF667085))

        Spacer(modifier = Modifier.height(14.dp))

        SectionParagraph(
            title = "1. Information We Collect",
            body = "When you submit an enquiry through the MADLY SERVICES application, we collect your name, contact phone number, email address (optional), service category of interest, customer category, and area location in Hyderabad/Telangana. We do not sell or lease your personal information to third-party marketers."
        )

        SectionParagraph(
            title = "2. How We Use Your Details",
            body = "We use your submitted details exclusively to: (a) Respond to your quotation requests, technical enquiries, and demo bookings; (b) Coordinate on-site visits, CCTV surveys, or network diagnostics; (c) Provide customer service and technical support regarding your active engagements."
        )

        SectionParagraph(
            title = "3. Communication Channels",
            body = "By checking the consent box, you authorize MADLY SERVICES (Ravi Biyyala and appointed technicians) to contact you via telephone call or WhatsApp messaging regarding your specified requirement."
        )

        SectionParagraph(
            title = "4. Data Storage & Security",
            body = "Enquiries are securely recorded and managed within our authorized internal systems. Access to lead archives is restricted strictly to designated operational managers through secure credential verification."
        )

        SectionParagraph(
            title = "5. Contacting Our Data Custodian",
            body = "If you wish to update or delete your enquiry history from our database, you may contact Ravi Biyyala directly at 8499922598 or via WhatsApp at 8500321299."
        )
    }
}

@Composable
private fun TermsConditionsContent() {
    Column {
        Text(text = "Terms & Conditions", fontSize = 17.sp, fontWeight = FontWeight.Bold, color = BrandNavy)
        Text(text = "Last Updated: 2026 | MADLY SERVICES", fontSize = 11.sp, color = Color(0xFF667085))

        Spacer(modifier = Modifier.height(14.dp))

        SectionParagraph(
            title = "1. Service Facilitation & Scope",
            body = "MADLY SERVICES provides consultation, configuration, installation, and procurement assistance for networking, CCTV, router hardware, restaurant billing software, and insurance guidance. Quotes provided are based on initial user specifications and are subject to final on-site verification."
        )

        SectionParagraph(
            title = "2. Insurance Regulatory Notice",
            body = "MADLY SERVICES operates as an independent facilitation and guidance partner. Policy terms, claim approvals, coverage limits, copays, and exclusions are determined strictly by the licensed underwriting insurance companies in accordance with applicable IRDAI regulations. No guaranteed claim approvals or investment yields are represented."
        )

        SectionParagraph(
            title = "3. Hardware & Manufacturer Warranties",
            body = "All supplied CCTV cameras, DVR/NVRs, switches, routers, and POS hardware carry the standard manufacturer warranty provided by the respective Original Equipment Manufacturer (OEM). MADLY SERVICES supports warranty claims according to OEM guidelines."
        )

        SectionParagraph(
            title = "4. CCTV Surveillance & Network Operation",
            body = "Continuous operation of CCTV recording and network equipment depends upon uninterrupted electrical power supplies and functional Internet Service Provider (ISP) lines at customer premises. Customers are responsible for legal compliance regarding camera positioning in private spaces."
        )

        SectionParagraph(
            title = "5. Governing Jurisdiction",
            body = "All transactions, service engagements, and agreements facilitated by MADLY SERVICES are governed under the laws of Telangana, India, with jurisdiction in Hyderabad."
        )
    }
}

@Composable
private fun SectionParagraph(title: String, body: String) {
    Column(modifier = Modifier.padding(bottom = 12.dp)) {
        Text(text = title, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = BrandNavy)
        Spacer(modifier = Modifier.height(3.dp))
        Text(text = body, fontSize = 12.sp, color = BrandBodyText, lineHeight = 16.sp)
    }
}
