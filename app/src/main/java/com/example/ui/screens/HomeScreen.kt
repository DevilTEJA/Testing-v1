package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.PriceCheck
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Warehouse
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.config.BusinessConfig
import com.example.data.model.ServiceCatalog
import com.example.data.model.ServiceItem
import com.example.ui.components.EnquiryFormCard
import com.example.ui.theme.BrandAccentRed
import com.example.ui.theme.BrandBodyText
import com.example.ui.theme.BrandBorder
import com.example.ui.theme.BrandCorporateBlue
import com.example.ui.theme.BrandLightBg
import com.example.ui.theme.BrandNavy
import com.example.ui.theme.BrandSkyTint
import com.example.ui.theme.BrandSurfaceVariant
import com.example.ui.theme.BrandWhatsAppGreen
import com.example.ui.theme.BrandWhite
import com.example.ui.viewmodel.MainViewModel

@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    onNavigateToService: (String) -> Unit,
    onNavigateToSection: (String) -> Unit,
    onOpenQuote: (String?) -> Unit
) {
    val context = LocalContext.current
    val services = ServiceCatalog.services

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BrandLightBg)
            .testTag("home_screen_lazy_column")
    ) {
        // 1. HERO SECTION (Split Executive Banner)
        item {
            HeroSection(
                onExploreServices = { onNavigateToSection("services_overview") },
                onChatWhatsApp = {
                    BusinessConfig.openWhatsApp(
                        context,
                        "Hello MADLY SERVICES, I am interested in exploring your business services. Please share details."
                    )
                },
                onQuickCall = { BusinessConfig.openDialer(context) }
            )
        }

        // 2. QUICK SERVICE CHIPS SCROLL
        item {
            QuickServiceChips(
                onSelectService = { onNavigateToService(it) }
            )
        }

        // 3. SERVICES OVERVIEW (5 Core Service Cards)
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                SectionHeader(
                    badge = "OUR SERVICES",
                    title = "Complete Solutions Under One Roof",
                    subtitle = "Explore our 5 specialized service divisions built for reliability, connectivity, security, and retail efficiency."
                )

                Spacer(modifier = Modifier.height(16.dp))

                services.forEach { service ->
                    ServiceCard(
                        service = service,
                        onViewDetails = { onNavigateToService(service.id) },
                        onAction = { onOpenQuote(service.title) },
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
            }
        }

        // 4. FEATURED BUSINESS SOLUTIONS PROMOTIONAL PANELS
        item {
            FeaturedSolutionsPanels(
                onBusinessSolutions = { onNavigateToService("network") },
                onPersonalSolutions = { onNavigateToService("insurance") },
                onOpenQuote = { onOpenQuote(null) }
            )
        }

        // 5. WHY CHOOSE MADLY SERVICES? (6 Benefit Cards)
        item {
            WhyChooseUsSection()
        }

        // 6. WHO WE SERVE (Industry Grid)
        item {
            WhoWeServeSection(
                onSelectIndustry = { onOpenQuote(it) }
            )
        }

        // 7. HOW IT WORKS (4-Step Process)
        item {
            HowItWorksSection(
                onStart = { onOpenQuote(null) }
            )
        }

        // 8. FAQ ACCORDION SECTION (9 Required Questions)
        item {
            FaqSection(
                onContactTeam = { BusinessConfig.openWhatsApp(context) }
            )
        }

        // 9. EMBEDDED LEAD GENERATION FORM
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                EnquiryFormCard(
                    viewModel = viewModel,
                    title = "Send an Enquiry to MADLY SERVICES",
                    subtitle = "Share your requirements directly with our Hyderabad team. We respond quickly via WhatsApp or Phone Call."
                )
            }
        }

        // 10. FINAL CONVERSION SECTION
        item {
            FinalConversionSection(
                onCall = { BusinessConfig.openDialer(context) },
                onWhatsApp = { BusinessConfig.openWhatsApp(context) },
                onRequestQuote = { onOpenQuote(null) }
            )
        }

        // 11. FOOTER
        item {
            HomeFooter(
                onNavigate = { screen, id ->
                    if (id != null) onNavigateToService(id) else onNavigateToSection(screen)
                }
            )
        }
    }
}

// ==========================================
// SUB-COMPONENTS
// ==========================================

@Composable
private fun HeroSection(
    onExploreServices: () -> Unit,
    onChatWhatsApp: () -> Unit,
    onQuickCall: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        BrandNavy,
                        Color(0xFF0F3D8C),
                        Color(0xFF07214F)
                    )
                )
            )
            .padding(horizontal = 16.dp, vertical = 28.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Service area & Tagline badge
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFF163E82))
                    .border(1.dp, Color(0xFF285AB0), RoundedCornerShape(20.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(BrandWhatsAppGreen)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Hyderabad, Telangana | Reliable Services",
                    color = Color(0xFFD3E3FC),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Headline
            Text(
                text = "One Trusted Partner.\nMultiple Solutions for Your Business.",
                color = BrandWhite,
                fontSize = 26.sp,
                lineHeight = 33.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = (-0.5).sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Supporting text
            Text(
                text = "From insurance guidance and network maintenance to CCTV security, router solutions, and restaurant billing software, MADLY SERVICES helps homes and businesses stay protected, connected, secure, and efficient.",
                color = Color(0xFFCBDDF7),
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Additional trust statement
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF092A66))
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = BrandCorporateBlue,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Solutions for Homes, Shops, Offices, Restaurants & Growing Businesses.",
                    color = BrandWhite,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Dual CTA Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = onExploreServices,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrandAccentRed,
                        contentColor = BrandWhite
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(46.dp)
                        .testTag("hero_explore_services_button")
                ) {
                    Text(
                        text = "Explore Services",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = onChatWhatsApp,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrandWhatsAppGreen,
                        contentColor = BrandWhite
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(46.dp)
                        .testTag("hero_chat_whatsapp_button")
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_whatsapp),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = BrandWhite
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Chat WhatsApp",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Direct contact preview chip
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onQuickCall)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF061E47))
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Call,
                        contentDescription = null,
                        tint = Color(0xFFBACDE8),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Contact: Ravi Biyyala (8499922598)",
                        color = Color(0xFFE2E8F0),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Text(
                    text = "Tap to Call",
                    color = BrandCorporateBlue,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun QuickServiceChips(onSelectService: (String) -> Unit) {
    val items = listOf(
        "insurance" to "Insurance Plans",
        "network" to "Network Maintenance",
        "cctv" to "CCTV Surveillance",
        "router" to "Routers & Wi-Fi",
        "billing" to "Restaurant POS"
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(BrandWhite)
            .padding(horizontal = 12.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) { (id, label) ->
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = BrandSkyTint,
                border = BorderStroke(1.dp, Color(0xFFBFD7F5)),
                modifier = Modifier.clickable { onSelectService(id) }
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = label,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = BrandNavy
                    )
                }
            }
        }
    }
}

@Composable
private fun ServiceCard(
    service: ServiceItem,
    onViewDetails: () -> Unit,
    onAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = BrandWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(1.dp, BrandBorder),
        modifier = modifier
            .fillMaxWidth()
            .testTag("service_card_${service.id}")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            // Icon + Title + Audience badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(BrandSkyTint)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = service.iconRes),
                        contentDescription = service.title,
                        modifier = Modifier.size(36.dp),
                        tint = Color.Unspecified
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = service.targetAudience.uppercase(),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = BrandCorporateBlue,
                        letterSpacing = 0.5.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = service.title,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = BrandNavy
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Short Description
            Text(
                text = service.shortDescription,
                fontSize = 13.sp,
                color = BrandBodyText,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Key Offerings Checklist
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(BrandLightBg)
                    .padding(10.dp)
            ) {
                Text(
                    text = "Key Offerings:",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrandNavy
                )
                Spacer(modifier = Modifier.height(4.dp))
                service.subServices.take(4).forEach { sub ->
                    Row(
                        modifier = Modifier.padding(vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = BrandCorporateBlue,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = sub,
                            fontSize = 12.sp,
                            color = BrandNavy
                        )
                    }
                }
            }

            // Regulatory note if present
            service.regulatoryNote?.let { note ->
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = note,
                    fontSize = 10.sp,
                    color = Color(0xFF667085),
                    lineHeight = 13.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Buttons: Primary Action CTA + "Learn More"
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onAction,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrandAccentRed,
                        contentColor = BrandWhite
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1.3f)
                ) {
                    Text(
                        text = service.ctaLabel,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                OutlinedButton(
                    onClick = onViewDetails,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = BrandCorporateBlue
                    ),
                    border = BorderStroke(1.dp, BrandCorporateBlue),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Details",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(12.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun FeaturedSolutionsPanels(
    onBusinessSolutions: () -> Unit,
    onPersonalSolutions: () -> Unit,
    onOpenQuote: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        SectionHeader(
            badge = "TAILORED SOLUTIONS",
            title = "Focused Portfolios for Every Need",
            subtitle = "Whether equipping a commercial establishment or protecting personal family assets."
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Panel 1: For Businesses & Commercial Hubs
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = BrandNavy),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Business,
                        contentDescription = null,
                        tint = Color(0xFFBACDE8),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "For Businesses & Enterprises",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = BrandWhite
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Structured LAN networks, high-definition CCTV security, high-load router sales, and lightning-fast restaurant POS billing software designed to maximize uptime and prevent revenue leakage.",
                    fontSize = 12.sp,
                    color = Color(0xFFD4E2F8),
                    lineHeight = 17.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = onBusinessSolutions,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BrandCorporateBlue,
                            contentColor = BrandWhite
                        ),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text("Explore Business Tech", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                    OutlinedButton(
                        onClick = onOpenQuote,
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = BrandWhite),
                        border = BorderStroke(1.dp, Color(0xFF4C75B5))
                    ) {
                        Text("Request Business Quote", fontSize = 11.sp)
                    }
                }
            }
        }

        // Panel 2: For Individuals & Families
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F5FD)),
            border = BorderStroke(1.dp, Color(0xFFBFD7F5)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null,
                        tint = BrandCorporateBlue,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "For Individuals & Families",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = BrandNavy
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Comprehensive life, health, motor, and property insurance guidance, plus reliable home Wi-Fi and residential CCTV security to keep your household safe and connected.",
                    fontSize = 12.sp,
                    color = BrandBodyText,
                    lineHeight = 17.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = onPersonalSolutions,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrandAccentRed,
                        contentColor = BrandWhite
                    ),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text("Explore Insurance & Home Solutions", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun WhyChooseUsSection() {
    val benefits = listOf(
        Triple(
            Icons.Default.Layers,
            "Multiple Services Under One Roof",
            "Eliminate coordinating with separate vendors. We handle insurance, networking, CCTV, routers, and billing software seamlessly."
        ),
        Triple(
            Icons.Default.Tune,
            "Solutions Tailored to Your Needs",
            "We analyze your exact floor plan, business size, budget, or family requirements before recommending suitable packages."
        ),
        Triple(
            Icons.Default.Build,
            "Professional Technical Assistance",
            "Knowledgeable technicians equipped with proper diagnostic tools for cable testing, camera focus, and network calibration."
        ),
        Triple(
            Icons.Default.PriceCheck,
            "Practical & Cost-Conscious Options",
            "Transparent recommendations without unnecessary equipment markups or rigid plans you do not need."
        ),
        Triple(
            Icons.Default.SupportAgent,
            "Convenient Phone & WhatsApp Support",
            "Direct direct reach to our leadership and technician team via 8499922598 and 8500321299 without automated bots."
        ),
        Triple(
            Icons.Default.Security,
            "Ongoing Support Options",
            "Annual Maintenance Contracts (AMC) and preventive checkups available to ensure continuous performance."
        )
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BrandWhite)
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        SectionHeader(
            badge = "WHY MADLY SERVICES",
            title = "A Partner Committed to Your Success",
            subtitle = "Practical advantages that make MADLY SERVICES the preferred solution partner in Hyderabad."
        )

        Spacer(modifier = Modifier.height(16.dp))

        benefits.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                rowItems.forEach { (icon, title, desc) ->
                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = BrandLightBg),
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
                                Icon(
                                    imageVector = icon,
                                    contentDescription = null,
                                    tint = BrandCorporateBlue,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = title,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = BrandNavy,
                                lineHeight = 17.sp
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = desc,
                                fontSize = 11.sp,
                                color = BrandBodyText,
                                lineHeight = 15.sp
                            )
                        }
                    }
                }
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun WhoWeServeSection(onSelectIndustry: (String) -> Unit) {
    val industries = listOf(
        Pair(Icons.Default.Home, "Homes & Residential Customers"),
        Pair(Icons.Default.Store, "Retail Shops & Outlets"),
        Pair(Icons.Default.Apartment, "Offices & Commercial Hubs"),
        Pair(Icons.Default.Restaurant, "Restaurants, Cafés & Food Courts"),
        Pair(Icons.Default.Business, "Small Businesses & Startups"),
        Pair(Icons.Default.Warehouse, "Warehouses & Storage Facilities")
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        SectionHeader(
            badge = "WHO WE SERVE",
            title = "Engineered for Every Setting",
            subtitle = "From private residences to high-volume commercial venues across Hyderabad."
        )

        Spacer(modifier = Modifier.height(14.dp))

        industries.chunked(2).forEach { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                row.forEach { (icon, name) ->
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onSelectIndustry(name) },
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = BrandWhite),
                        border = BorderStroke(1.dp, BrandBorder)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = BrandCorporateBlue,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = name,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = BrandNavy
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HowItWorksSection(onStart: () -> Unit) {
    val steps = listOf(
        "1. Choose Your Service" to "Select from Insurance, Network, CCTV, Routers, or Restaurant Billing.",
        "2. Contact Our Team" to "Submit an enquiry form or call Ravi Biyyala directly via Phone or WhatsApp.",
        "3. Discuss Requirements" to "Our specialist discusses floor plans, camera count, POS counters, or policy goals.",
        "4. Receive Solution & Next Steps" to "Get an honest estimate, clear schedule, and professional deployment."
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BrandWhite)
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        SectionHeader(
            badge = "SIMPLE PROCESS",
            title = "How It Works",
            subtitle = "Four straightforward steps to solve your tech, connectivity, and protection needs."
        )

        Spacer(modifier = Modifier.height(16.dp))

        steps.forEachIndexed { index, (title, desc) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(BrandNavy),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${index + 1}",
                        color = BrandWhite,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = BrandNavy
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = desc,
                        fontSize = 12.sp,
                        color = BrandBodyText,
                        lineHeight = 16.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onStart,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BrandCorporateBlue,
                contentColor = BrandWhite
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Begin Your Consultation", fontWeight = FontWeight.Bold, fontSize = 13.sp)
        }
    }
}

@Composable
private fun FaqSection(onContactTeam: () -> Unit) {
    val faqs = listOf(
        "What services does MADLY SERVICES provide?" to
            "MADLY SERVICES provides 5 primary service solutions: All Types of Insurance guidance (health, life, motor, property), Network Maintenance & cabling, CCTV Installation & maintenance, Router Sales & configuration, and Restaurant Billing POS Software.",

        "Do you support homes and commercial businesses?" to
            "Yes! We service individual homeowners and residential communities, as well as retail shops, corporate offices, restaurants, cafés, and commercial warehouses throughout Hyderabad and Telangana.",

        "Can I request CCTV installation and maintenance?" to
            "Yes. We handle end-to-end CCTV camera installation, HD/4K setup, DVR/NVR configuration, wiring repair, and mobile app live streaming setup so you can view your feeds anywhere.",

        "Can you help configure an existing router?" to
            "Absolutely. Our technicians can configure routers you already own, tune Wi-Fi frequencies, setup secure guest SSIDs, eliminate dead zones, or set up Mesh Wi-Fi systems.",

        "Do you provide restaurant billing software demonstrations?" to
            "Yes! You can request a free demo directly in this app or via WhatsApp. We will demonstrate live POS billing, KOT ticket generation, menu updates, and GST sales reports.",

        "Can I request ongoing network maintenance?" to
            "Yes, we provide both on-demand technical visits and structured Annual Maintenance Contracts (AMC) for businesses requiring proactive network inspections.",

        "How do I enquire about insurance plans?" to
            "Submit the enquiry form on our Insurance page, or reach out to Ravi Biyyala over Phone (8499922598) or WhatsApp (8500321299). We will review your goals and explain eligible policy options.",

        "How can I request a quote?" to
            "Tap 'Get a Free Quote' anywhere in this app, or fill out the enquiry card. There are no consultation fees for initial requirements assessment.",

        "How can I contact MADLY SERVICES through WhatsApp?" to
            "Tap the green WhatsApp button located throughout the app to open a chat directly with 8500321299 with your service enquiry prefilled."
    )

    var expandedIndex by remember { mutableIntStateOf(-1) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        SectionHeader(
            badge = "COMMON QUESTIONS",
            title = "Frequently Asked Questions",
            subtitle = "Clear, accurate answers regarding our services, coverage area, and enquiry process."
        )

        Spacer(modifier = Modifier.height(14.dp))

        faqs.forEachIndexed { index, (q, a) ->
            val isExpanded = expandedIndex == index
            Card(
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isExpanded) Color(0xFFF7FAFD) else BrandWhite
                ),
                border = BorderStroke(1.dp, if (isExpanded) Color(0xFFBED5F3) else BrandBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .clickable {
                        expandedIndex = if (isExpanded) -1 else index
                    }
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = q,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = BrandNavy,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = null,
                            tint = BrandCorporateBlue
                        )
                    }

                    AnimatedVisibility(visible = isExpanded) {
                        Column {
                            Spacer(modifier = Modifier.height(8.dp))
                            HorizontalDivider(color = BrandBorder)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = a,
                                fontSize = 12.sp,
                                color = BrandBodyText,
                                lineHeight = 17.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FinalConversionSection(
    onCall: () -> Unit,
    onWhatsApp: () -> Unit,
    onRequestQuote: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = BrandNavy),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Looking for a Reliable Service Partner?",
                fontSize = 19.sp,
                fontWeight = FontWeight.ExtraBold,
                color = BrandWhite,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Tell us what you need. Our team can help you explore the right service for your home, shop, office, or business.",
                fontSize = 13.sp,
                color = Color(0xFFD4E2F8),
                textAlign = TextAlign.Center,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(18.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onCall,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrandCorporateBlue,
                        contentColor = BrandWhite
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Call 8499922598", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = onWhatsApp,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrandWhatsAppGreen,
                        contentColor = BrandWhite
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_whatsapp),
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = BrandWhite
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("WhatsApp", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onRequestQuote,
                colors = ButtonDefaults.buttonColors(
                    containerColor = BrandAccentRed,
                    contentColor = BrandWhite
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Request a Quote Online", fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun HomeFooter(
    onNavigate: (String, String?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF061B40))
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_madly_badge),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "MADLY SERVICES",
                color = BrandWhite,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }

        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Your One-Stop Solution Partner | Reliable Services for a Better Tomorrow",
            color = Color(0xFFA7BEDD),
            fontSize = 11.sp
        )

        Spacer(modifier = Modifier.height(14.dp))
        HorizontalDivider(color = Color(0xFF163E82))
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Business Contact: ${BusinessConfig.CONTACT_PERSON}",
            color = BrandWhite,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "Phone: ${BusinessConfig.PHONE_NUMBER} | WhatsApp: ${BusinessConfig.WHATSAPP_NUMBER}",
            color = Color(0xFFD4E2F8),
            fontSize = 11.sp
        )
        Text(
            text = "Primary Service Area: ${BusinessConfig.PRIMARY_SERVICE_AREA}",
            color = Color(0xFFBACDE8),
            fontSize = 11.sp
        )
        Text(
            text = BusinessConfig.BUSINESS_HOURS,
            color = Color(0xFF90A4CE),
            fontSize = 10.sp
        )

        Spacer(modifier = Modifier.height(14.dp))
        HorizontalDivider(color = Color(0xFF163E82))
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "About Us",
                color = Color(0xFFD4E2F8),
                fontSize = 11.sp,
                modifier = Modifier.clickable { onNavigate("about", null) }
            )
            Text(
                text = "Contact Us",
                color = Color(0xFFD4E2F8),
                fontSize = 11.sp,
                modifier = Modifier.clickable { onNavigate("contact", null) }
            )
            Text(
                text = "Privacy & Terms",
                color = Color(0xFFD4E2F8),
                fontSize = 11.sp,
                modifier = Modifier.clickable { onNavigate("legal", null) }
            )
            Text(
                text = "Admin Portal",
                color = Color(0xFFD4E2F8),
                fontSize = 11.sp,
                modifier = Modifier.clickable { onNavigate("admin", null) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "© 2026 MADLY SERVICES. All Rights Reserved. Built for Hyderabad & Telangana.",
            color = Color(0xFF708DB8),
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun SectionHeader(badge: String, title: String, subtitle: String) {
    Column {
        Text(
            text = badge,
            color = BrandCorporateBlue,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = title,
            color = BrandNavy,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 26.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = subtitle,
            color = BrandBodyText,
            fontSize = 13.sp,
            lineHeight = 18.sp
        )
    }
}
