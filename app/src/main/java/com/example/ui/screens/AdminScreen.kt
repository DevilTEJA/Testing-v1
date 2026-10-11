package com.example.ui.screens

import android.content.Intent
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.config.BusinessConfig
import com.example.data.model.ALL_SERVICE_OPTIONS
import com.example.data.model.Enquiry
import com.example.data.model.LeadStatus
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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AdminScreen(
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    val isAuthenticated by viewModel.isAdminAuthenticated.collectAsState()

    if (!isAuthenticated) {
        AdminLoginCard(viewModel = viewModel)
    } else {
        AdminDashboardContent(viewModel = viewModel)
    }
}

@Composable
private fun AdminLoginCard(viewModel: MainViewModel) {
    val pinInput by viewModel.adminPinInput.collectAsState()
    val pinError by viewModel.adminPinError.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BrandLightBg)
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = BrandWhite),
            border = BorderStroke(1.dp, BrandBorder),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("admin_login_card")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                        .background(BrandSkyTint),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = BrandCorporateBlue,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Admin Lead Management Portal",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrandNavy
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Enter authorized PIN to view, update, and manage customer enquiries stored securely in the app.",
                    fontSize = 12.sp,
                    color = BrandBodyText,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    lineHeight = 16.sp
                )

                Spacer(modifier = Modifier.height(18.dp))

                OutlinedTextField(
                    value = pinInput,
                    onValueChange = { viewModel.updateAdminPin(it) },
                    label = { Text("Security PIN") },
                    placeholder = { Text("Default: 1234") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    visualTransformation = PasswordVisualTransformation(),
                    isError = pinError != null,
                    supportingText = {
                        pinError?.let { Text(it, color = BrandAccentRed, fontSize = 11.sp) }
                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("admin_pin_input")
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { viewModel.verifyAdminPin() },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrandNavy,
                        contentColor = BrandWhite
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp)
                        .testTag("admin_login_button")
                ) {
                    Text("Unlock Dashboard", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Default PIN for business demonstration: 1234",
                    fontSize = 11.sp,
                    color = Color(0xFF667085)
                )
            }
        }
    }
}

@Composable
private fun AdminDashboardContent(viewModel: MainViewModel) {
    val context = LocalContext.current
    val leads by viewModel.enquiries.collectAsState()
    val searchQuery by viewModel.adminSearchQuery.collectAsState()
    val serviceFilter by viewModel.adminServiceFilter.collectAsState()
    val statusFilter by viewModel.adminStatusFilter.collectAsState()

    var selectedLeadForDetail by remember { mutableStateOf<Enquiry?>(null) }
    var leadForNotesEdit by remember { mutableStateOf<Enquiry?>(null) }
    var notesText by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BrandLightBg)
            .testTag("admin_dashboard_lazy_column")
    ) {
        // Top Admin Header
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BrandNavy)
                    .padding(horizontal = 16.dp, vertical = 18.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.AdminPanelSettings,
                                contentDescription = null,
                                tint = BrandWhite,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "MADLY Lead Dashboard",
                                color = BrandWhite,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(
                            text = "${leads.size} enquiries recorded",
                            color = Color(0xFFBACDE8),
                            fontSize = 12.sp
                        )
                    }

                    Row {
                        // Export CSV
                        IconButton(
                            onClick = {
                                val csv = viewModel.generateCsvExport(leads)
                                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                    type = "text/plain"
                                    putExtra(Intent.EXTRA_SUBJECT, "MADLY SERVICES Lead Export")
                                    putExtra(Intent.EXTRA_TEXT, csv)
                                }
                                context.startActivity(Intent.createChooser(shareIntent, "Export Leads via"))
                            }
                        ) {
                            Icon(Icons.Default.Share, contentDescription = "Export CSV", tint = BrandWhite)
                        }

                        // Logout
                        IconButton(onClick = { viewModel.adminLogout() }) {
                            Icon(Icons.Default.Close, contentDescription = "Lock", tint = BrandWhite)
                        }
                    }
                }
            }
        }

        // Search Bar
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { viewModel.adminSearchQuery.value = it },
                    placeholder = { Text("Search by name, phone, area...") },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = null, tint = BrandCorporateBlue)
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                )
            }
        }

        // Service Filter Chips
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Filter by Service:",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrandNavy,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
                )

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    val serviceOptions = listOf("All") + ALL_SERVICE_OPTIONS
                    items(serviceOptions) { s ->
                        val isSelected = serviceFilter == s
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = if (isSelected) BrandCorporateBlue else BrandWhite,
                            border = BorderStroke(1.dp, if (isSelected) BrandCorporateBlue else BrandBorder),
                            modifier = Modifier.clickable { viewModel.adminServiceFilter.value = s }
                        ) {
                            Text(
                                text = s,
                                color = if (isSelected) BrandWhite else BrandNavy,
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                            )
                        }
                    }
                }
            }
        }

        // Status Filter Chips
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Filter by Status:",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrandNavy,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
                )

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    val statusOptions = listOf("All") + LeadStatus.entries.map { it.label }
                    items(statusOptions) { st ->
                        val isSelected = statusFilter == st
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = if (isSelected) BrandNavy else BrandWhite,
                            border = BorderStroke(1.dp, if (isSelected) BrandNavy else BrandBorder),
                            modifier = Modifier.clickable { viewModel.adminStatusFilter.value = st }
                        ) {
                            Text(
                                text = st,
                                color = if (isSelected) BrandWhite else BrandNavy,
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                            )
                        }
                    }
                }
            }
        }

        // Lead Cards List
        if (leads.isEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "No enquiries found matching filters.",
                            color = BrandBodyText,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "New enquiries submitted by users appear here immediately.",
                            color = Color(0xFF667085),
                            fontSize = 11.sp
                        )
                    }
                }
            }
        } else {
            items(leads, key = { it.id }) { lead ->
                LeadItemCard(
                    lead = lead,
                    onStatusChange = { newStatus -> viewModel.updateLeadStatus(lead.id, newStatus) },
                    onEditNotes = {
                        leadForNotesEdit = lead
                        notesText = lead.followUpNotes
                    },
                    onDelete = { viewModel.deleteLead(lead.id) },
                    onCallCustomer = { BusinessConfig.openDialer(context, lead.mobileNumber) },
                    onWhatsAppCustomer = {
                        val message = "Hello ${lead.customerName}, this is Ravi Biyyala from MADLY SERVICES regarding your enquiry for *${lead.requiredService}*. How can we assist you today?"
                        BusinessConfig.openWhatsApp(context, message, lead.mobileNumber)
                    },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }

    // Follow-up Notes Dialog
    if (leadForNotesEdit != null) {
        val targetLead = leadForNotesEdit!!
        AlertDialog(
            onDismissRequest = { leadForNotesEdit = null },
            title = { Text("Update Follow-up Notes", fontWeight = FontWeight.Bold) },
            text = {
                Column {
                    Text(text = "Lead: ${targetLead.customerName} (${targetLead.requiredService})", fontSize = 12.sp, color = BrandBodyText)
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = notesText,
                        onValueChange = { notesText = it },
                        placeholder = { Text("Enter internal notes, customer availability, quoted pricing...") },
                        minLines = 3,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.updateLeadNotes(targetLead.id, notesText)
                        leadForNotesEdit = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = BrandCorporateBlue)
                ) {
                    Text("Save Notes")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { leadForNotesEdit = null }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
private fun LeadItemCard(
    lead: Enquiry,
    onStatusChange: (String) -> Unit,
    onEditNotes: () -> Unit,
    onDelete: () -> Unit,
    onCallCustomer: () -> Unit,
    onWhatsAppCustomer: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dateStr = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault()).format(Date(lead.timestamp))

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = BrandWhite),
        border = BorderStroke(1.dp, BrandBorder),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            // Header: Name + Status Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = lead.customerName,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = BrandNavy
                    )
                    Text(
                        text = "$dateStr • ${lead.customerType}",
                        fontSize = 11.sp,
                        color = Color(0xFF667085)
                    )
                }

                StatusBadge(status = lead.status)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Service + Area
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = BrandSkyTint
                ) {
                    Text(
                        text = lead.requiredService,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = BrandCorporateBlue,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Area: ${lead.cityArea}",
                    fontSize = 11.sp,
                    color = BrandBodyText
                )
            }

            if (lead.requirementsMessage.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "\"${lead.requirementsMessage}\"",
                    fontSize = 12.sp,
                    color = BrandBodyText,
                    lineHeight = 16.sp
                )
            }

            if (lead.followUpNotes.isNotBlank()) {
                Spacer(modifier = Modifier.height(6.dp))
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Color(0xFFFFFBEB),
                    border = BorderStroke(1.dp, Color(0xFFFDE68A)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Notes: ${lead.followUpNotes}",
                        fontSize = 11.sp,
                        color = Color(0xFF92400E),
                        modifier = Modifier.padding(6.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(color = BrandLightBg)
            Spacer(modifier = Modifier.height(8.dp))

            // Status Selector Chips
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Status:", fontSize = 10.sp, color = BrandBodyText)
                LeadStatus.entries.forEach { statusOption ->
                    val isCurrent = lead.status == statusOption.label
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if (isCurrent) BrandNavy else Color(0xFFF2F4F7),
                        modifier = Modifier.clickable { onStatusChange(statusOption.label) }
                    ) {
                        Text(
                            text = statusOption.label,
                            fontSize = 9.sp,
                            fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Normal,
                            color = if (isCurrent) BrandWhite else BrandBodyText,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Action Buttons: Call, WhatsApp, Notes, Delete
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    // Call Lead
                    Button(
                        onClick = onCallCustomer,
                        colors = ButtonDefaults.buttonColors(containerColor = BrandCorporateBlue, contentColor = BrandWhite),
                        shape = RoundedCornerShape(6.dp),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        modifier = Modifier.height(30.dp)
                    ) {
                        Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(12.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Call", fontSize = 11.sp)
                    }

                    // WhatsApp Lead
                    Button(
                        onClick = onWhatsAppCustomer,
                        colors = ButtonDefaults.buttonColors(containerColor = BrandWhatsAppGreen, contentColor = BrandWhite),
                        shape = RoundedCornerShape(6.dp),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        modifier = Modifier.height(30.dp)
                    ) {
                        Icon(painterResource(id = R.drawable.ic_whatsapp), contentDescription = null, modifier = Modifier.size(12.dp), tint = BrandWhite)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("WhatsApp", fontSize = 11.sp)
                    }

                    // Notes
                    OutlinedButton(
                        onClick = onEditNotes,
                        shape = RoundedCornerShape(6.dp),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                        modifier = Modifier.height(30.dp)
                    ) {
                        Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(12.dp), tint = BrandNavy)
                        Spacer(modifier = Modifier.width(2.dp))
                        Text("Notes", fontSize = 11.sp, color = BrandNavy)
                    }
                }

                // Delete Lead
                IconButton(onClick = onDelete, modifier = Modifier.size(28.dp)) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color(0xFF9E77ED), modifier = Modifier.size(18.dp))
                }
            }
        }
    }
}

@Composable
private fun StatusBadge(status: String) {
    val (bgColor, textColor) = when (status) {
        "New" -> Pair(Color(0xFFEFF8FF), Color(0xFF175CD3))
        "Contacted" -> Pair(Color(0xFFFEF08A), Color(0xFF854D0E))
        "In Progress" -> Pair(Color(0xFFEDE9FE), Color(0xFF5B21B6))
        "Converted" -> Pair(Color(0xFFDCFCE7), Color(0xFF166534))
        "Closed" -> Pair(Color(0xFFF3F4F6), Color(0xFF4B5563))
        else -> Pair(BrandSkyTint, BrandCorporateBlue)
    }

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = bgColor
    ) {
        Text(
            text = status,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = textColor,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
        )
    }
}
