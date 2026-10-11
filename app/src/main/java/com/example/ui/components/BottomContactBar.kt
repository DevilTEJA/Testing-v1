package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.config.BusinessConfig
import com.example.ui.theme.BrandAccentRed
import com.example.ui.theme.BrandCorporateBlue
import com.example.ui.theme.BrandNavy
import com.example.ui.theme.BrandWhatsAppGreen
import com.example.ui.theme.BrandWhite

@Composable
fun BottomContactBar(
    onOpenQuoteDialog: () -> Unit
) {
    val context = LocalContext.current

    Surface(
        color = BrandWhite,
        shadowElevation = 12.dp,
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Call Ravi Biyyala (8499922598)
            OutlinedButton(
                onClick = { BusinessConfig.openDialer(context, BusinessConfig.PHONE_NUMBER) },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .weight(1f)
                    .testTag("bottom_bar_call_button"),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = BrandNavy
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = "Call MADLY SERVICES",
                    modifier = Modifier.size(16.dp),
                    tint = BrandNavy
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Call",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }

            // Chat on WhatsApp (8500321299)
            Button(
                onClick = { BusinessConfig.openWhatsApp(context) },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .weight(1.3f)
                    .testTag("bottom_bar_whatsapp_button"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BrandWhatsAppGreen,
                    contentColor = BrandWhite
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_whatsapp),
                    contentDescription = "WhatsApp",
                    modifier = Modifier.size(18.dp),
                    tint = BrandWhite
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "WhatsApp",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }

            // Quick Enquire / Quote
            Button(
                onClick = onOpenQuoteDialog,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .weight(1f)
                    .testTag("bottom_bar_enquire_button"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BrandAccentRed,
                    contentColor = BrandWhite
                )
            ) {
                Text(
                    text = "Enquire",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Composable
fun FloatingWhatsAppButton(
    prefilledMessage: String? = null,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    FloatingActionButton(
        onClick = { BusinessConfig.openWhatsApp(context, prefilledMessage) },
        containerColor = BrandWhatsAppGreen,
        contentColor = BrandWhite,
        shape = CircleShape,
        modifier = modifier.testTag("floating_whatsapp_button")
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_whatsapp),
            contentDescription = "Quick WhatsApp Chat",
            modifier = Modifier.size(28.dp),
            tint = BrandWhite
        )
    }
}
