package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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
import com.example.data.model.ServiceItem
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
fun ServiceDetailScreen(
    serviceId: String,
    viewModel: MainViewModel,
    onBack: () -> Unit,
    onSelectOtherService: (String) -> Unit
) {
    val context = LocalContext.current
    val service = ServiceCatalog.getById(serviceId) ?: ServiceCatalog.services.first()

    // Pre-populate the form with this specific service
    LaunchedEffect(service.id) {
        viewModel.setFormDefaults(
            service = service.title,
            source = "Service Detail: ${service.title}"
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BrandLightBg)
            .testTag("service_detail_screen_${service.id}")
    ) {
        // 1. Back button & Category Bar
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BrandNavy)
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = BrandWhite
                    )
                }
                Text(
                    text = "Services / ${service.title}",
                    color = Color(0xFFBACDE8),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        // 2. Service Hero Banner
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                BrandNavy,
                                Color(0xFF0F3D8C),
                                Color(0xFF08275E)
                            )
                        )
                    )
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(BrandSkyTint)
                                .padding(10.dp),
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

                        Column {
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = Color(0xFF1E488F)
                            ) {
                                Text(
                                    text = service.targetAudience,
                                    color = Color(0xFFD4E2F8),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = service.title,
                                color = BrandWhite,
                                fontSize = 21.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = service.detailedOverview,
                        color = Color(0xFFCBDDF7),
                        fontSize = 13.sp,
                        lineHeight = 19.sp
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    // Direct Contact CTAs
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = { BusinessConfig.openDialer(context) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = BrandCorporateBlue,
                                contentColor = BrandWhite
                            ),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Call Us", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }

                        Button(
                            onClick = { BusinessConfig.openWhatsApp(context, service.defaultPrefilledMessage) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = BrandWhatsAppGreen,
                                contentColor = BrandWhite
                            ),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(painterResource(id = R.drawable.ic_whatsapp), contentDescription = null, modifier = Modifier.size(14.dp), tint = BrandWhite)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("WhatsApp Chat", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // 3. Sub-services Checklist
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                Text(
                    text = "Detailed Scope & Offerings",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrandNavy
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Included options and technical capabilities for ${service.title}:",
                    fontSize = 12.sp,
                    color = BrandBodyText
                )

                Spacer(modifier = Modifier.height(12.dp))

                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = BrandWhite),
                    border = BorderStroke(1.dp, BrandBorder)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        service.subServices.forEachIndexed { index, sub ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clip(CircleShape)
                                        .background(BrandSkyTint),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        tint = BrandCorporateBlue,
                                        modifier = Modifier.size(12.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = sub,
                                    fontSize = 13.sp,
                                    color = BrandNavy,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                            if (index < service.subServices.size - 1) {
                                HorizontalDivider(color = BrandLightBg)
                            }
                        }
                    }
                }
            }
        }

        // 4. Regulatory / Compliance Note if applicable
        service.regulatoryNote?.let { note ->
            item {
                Card(
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF7ED)),
                    border = BorderStroke(1.dp, Color(0xFFFED7AA)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.Top) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = Color(0xFFC2410C),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = note,
                            fontSize = 11.sp,
                            color = Color(0xFF7C2D12),
                            lineHeight = 15.sp
                        )
                    }
                }
            }
        }

        // 5. Customer Benefits
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BrandWhite)
                    .padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                Text(
                    text = "Why Choose MADLY SERVICES for This",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrandNavy
                )
                Spacer(modifier = Modifier.height(12.dp))

                service.benefits.forEach { (benefitTitle, benefitDesc) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = BrandCorporateBlue,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = benefitTitle,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = BrandNavy
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = benefitDesc,
                                fontSize = 12.sp,
                                color = BrandBodyText,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }
        }

        // 6. Typical Use Cases
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                Text(
                    text = "Typical Applications & Use Cases",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrandNavy
                )
                Spacer(modifier = Modifier.height(10.dp))

                service.useCases.forEach { useCase ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = BrandWhite),
                        border = BorderStroke(1.dp, BrandBorder),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .clip(CircleShape)
                                    .background(BrandAccentRed)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = useCase,
                                fontSize = 12.sp,
                                color = BrandNavy,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }
        }

        // 7. How It Works (Process Steps)
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BrandWhite)
                    .padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                Text(
                    text = "Engagement & Delivery Process",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrandNavy
                )
                Spacer(modifier = Modifier.height(12.dp))

                service.processSteps.forEach { (stepTitle, stepDesc) ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    ) {
                        Text(
                            text = stepTitle,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = BrandCorporateBlue
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = stepDesc,
                            fontSize = 12.sp,
                            color = BrandBodyText,
                            lineHeight = 16.sp
                        )
                    }
                }
            }
        }

        // 8. Service FAQs
        item {
            var expandedFaq by remember { mutableIntStateOf(-1) }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                Text(
                    text = "Frequently Asked Questions",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrandNavy
                )
                Spacer(modifier = Modifier.height(10.dp))

                service.faqs.forEachIndexed { idx, (q, a) ->
                    val isExp = expandedFaq == idx
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = BrandWhite),
                        border = BorderStroke(1.dp, BrandBorder),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable { expandedFaq = if (isExp) -1 else idx }
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
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
                                    imageVector = if (isExp) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                    contentDescription = null,
                                    tint = BrandCorporateBlue
                                )
                            }
                            AnimatedVisibility(visible = isExp) {
                                Column {
                                    Spacer(modifier = Modifier.height(6.dp))
                                    HorizontalDivider(color = BrandBorder)
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = a,
                                        fontSize = 12.sp,
                                        color = BrandBodyText,
                                        lineHeight = 16.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // 9. Dedicated Enquiry Form with preselected service
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                EnquiryFormCard(
                    viewModel = viewModel,
                    title = "Enquire for ${service.title}",
                    subtitle = "Leave your details to schedule a consultation, on-site assessment, or demo in Hyderabad."
                )
            }
        }

        // 10. Related Services Switcher
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BrandWhite)
                    .padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                Text(
                    text = "Explore Other Services",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrandNavy
                )
                Spacer(modifier = Modifier.height(10.dp))

                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    val otherServices = ServiceCatalog.services.filter { it.id != service.id }
                    items(otherServices) { other ->
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = BrandLightBg,
                            border = BorderStroke(1.dp, BrandBorder),
                            modifier = Modifier.clickable { onSelectOtherService(other.id) }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = other.iconRes),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                    tint = Color.Unspecified
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = other.title,
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

        // Bottom space so floating bar doesn't obscure
        item {
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}
