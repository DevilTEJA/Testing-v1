package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.config.BusinessConfig
import com.example.data.model.ALL_SERVICE_OPTIONS
import com.example.data.model.CUSTOMER_TYPE_OPTIONS
import com.example.data.model.PREFERRED_CONTACT_METHODS
import com.example.ui.theme.BrandAccentRed
import com.example.ui.theme.BrandBodyText
import com.example.ui.theme.BrandBorder
import com.example.ui.theme.BrandCorporateBlue
import com.example.ui.theme.BrandLightBg
import com.example.ui.theme.BrandNavy
import com.example.ui.theme.BrandWhatsAppGreen
import com.example.ui.theme.BrandWhite
import com.example.ui.viewmodel.MainViewModel

@Composable
fun EnquiryFormCard(
    viewModel: MainViewModel,
    title: String = "Request a Free Consultation & Quote",
    subtitle: String = "Fill out your details below and our service specialist will contact you promptly.",
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val formState by viewModel.formState.collectAsState()

    var customerTypeDropdownOpen by remember { mutableStateOf(false) }
    var serviceDropdownOpen by remember { mutableStateOf(false) }
    var showSuccessDialog by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = BrandWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BrandBorder),
        modifier = modifier
            .fillMaxWidth()
            .testTag("enquiry_form_card")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Header
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(4.dp, 22.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(BrandAccentRed)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrandNavy
                )
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                fontSize = 13.sp,
                color = BrandBodyText,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(18.dp))

            // 1. Full Name
            OutlinedTextField(
                value = formState.customerName,
                onValueChange = { viewModel.updateName(it) },
                label = { Text("Full Name *") },
                placeholder = { Text("e.g. Ramesh Kumar") },
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = null, tint = BrandCorporateBlue)
                },
                isError = formState.nameError != null,
                supportingText = {
                    formState.nameError?.let { Text(it, color = BrandAccentRed, fontSize = 11.sp) }
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("input_customer_name"),
                colors = outlinedColors()
            )

            Spacer(modifier = Modifier.height(6.dp))

            // 2. Mobile Number (10 digits)
            OutlinedTextField(
                value = formState.mobileNumber,
                onValueChange = { viewModel.updateMobile(it) },
                label = { Text("Mobile Number (10 digits) *") },
                placeholder = { Text("e.g. 9876543210") },
                leadingIcon = {
                    Icon(Icons.Default.Phone, contentDescription = null, tint = BrandCorporateBlue)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                isError = formState.mobileError != null,
                supportingText = {
                    formState.mobileError?.let { Text(it, color = BrandAccentRed, fontSize = 11.sp) }
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("input_mobile_number"),
                colors = outlinedColors()
            )

            Spacer(modifier = Modifier.height(6.dp))

            // 3. Email Address (Optional)
            OutlinedTextField(
                value = formState.email,
                onValueChange = { viewModel.updateEmail(it) },
                label = { Text("Email Address (Optional)") },
                placeholder = { Text("e.g. name@example.com") },
                leadingIcon = {
                    Icon(Icons.Default.Mail, contentDescription = null, tint = BrandCorporateBlue)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("input_email_address"),
                colors = outlinedColors()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 4. Customer Type Dropdown
            Text(
                text = "Customer Type *",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = BrandNavy
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .border(1.dp, BrandBorder, RoundedCornerShape(8.dp))
                        .background(BrandLightBg)
                        .clickable { customerTypeDropdownOpen = true }
                        .padding(horizontal = 14.dp, vertical = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = formState.customerType,
                        fontSize = 14.sp,
                        color = BrandNavy,
                        fontWeight = FontWeight.Medium
                    )
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = BrandNavy)
                }

                DropdownMenu(
                    expanded = customerTypeDropdownOpen,
                    onDismissRequest = { customerTypeDropdownOpen = false }
                ) {
                    CUSTOMER_TYPE_OPTIONS.forEach { type ->
                        DropdownMenuItem(
                            text = { Text(type, fontSize = 13.sp) },
                            onClick = {
                                viewModel.updateCustomerType(type)
                                customerTypeDropdownOpen = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // 5. Required Service Dropdown
            Text(
                text = "Required Service *",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = BrandNavy
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .border(1.dp, BrandBorder, RoundedCornerShape(8.dp))
                        .background(BrandLightBg)
                        .clickable { serviceDropdownOpen = true }
                        .padding(horizontal = 14.dp, vertical = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = formState.requiredService,
                        fontSize = 14.sp,
                        color = BrandCorporateBlue,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = BrandNavy)
                }

                DropdownMenu(
                    expanded = serviceDropdownOpen,
                    onDismissRequest = { serviceDropdownOpen = false }
                ) {
                    ALL_SERVICE_OPTIONS.forEach { service ->
                        DropdownMenuItem(
                            text = { Text(service, fontSize = 13.sp) },
                            onClick = {
                                viewModel.updateRequiredService(service)
                                serviceDropdownOpen = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // 6. City or Area
            OutlinedTextField(
                value = formState.cityArea,
                onValueChange = { viewModel.updateCityArea(it) },
                label = { Text("City or Area (Hyderabad / Telangana)") },
                placeholder = { Text("e.g. Madhapur, Hyderabad") },
                leadingIcon = {
                    Icon(Icons.Default.LocationOn, contentDescription = null, tint = BrandCorporateBlue)
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("input_city_area"),
                colors = outlinedColors()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 7. Brief Description of Requirements
            OutlinedTextField(
                value = formState.requirementsMessage,
                onValueChange = { viewModel.updateRequirementsMessage(it) },
                label = { Text("Brief Description of Requirements") },
                placeholder = { Text("Please describe your setup, number of cameras, POS counters, or insurance requirements...") },
                minLines = 3,
                maxLines = 5,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("input_requirements_message"),
                colors = outlinedColors()
            )

            Spacer(modifier = Modifier.height(14.dp))

            // 8. Preferred Contact Method
            Text(
                text = "Preferred Contact Method",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = BrandNavy
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                PREFERRED_CONTACT_METHODS.forEach { method ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable { viewModel.updatePreferredContactMethod(method) }
                            .padding(vertical = 4.dp)
                    ) {
                        RadioButton(
                            selected = formState.preferredContactMethod == method,
                            onClick = { viewModel.updatePreferredContactMethod(method) },
                            colors = RadioButtonDefaults.colors(selectedColor = BrandCorporateBlue)
                        )
                        Text(text = method, fontSize = 12.sp, color = BrandNavy)
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // 9. Consent Checkbox
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.updateConsent(!formState.consentGiven) }
            ) {
                Checkbox(
                    checked = formState.consentGiven,
                    onCheckedChange = { viewModel.updateConsent(it) },
                    colors = CheckboxDefaults.colors(checkedColor = BrandCorporateBlue)
                )
                Text(
                    text = "I consent to MADLY SERVICES contacting me via phone/WhatsApp regarding this enquiry.",
                    fontSize = 11.sp,
                    color = BrandBodyText,
                    lineHeight = 15.sp
                )
            }
            formState.consentError?.let {
                Text(
                    text = it,
                    color = BrandAccentRed,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Submit Button
            Button(
                onClick = {
                    viewModel.submitEnquiry {
                        showSuccessDialog = true
                    }
                },
                enabled = !formState.isSubmitting,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BrandAccentRed,
                    contentColor = BrandWhite
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("submit_enquiry_button")
            ) {
                if (formState.isSubmitting) {
                    CircularProgressIndicator(
                        color = BrandWhite,
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Submit Enquiry",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }

    // Success Confirmation Dialog
    if (showSuccessDialog) {
        val lastLead = formState.lastSubmittedEnquiry
        AlertDialog(
            onDismissRequest = {
                showSuccessDialog = false
                viewModel.resetSubmitSuccess()
            },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = BrandWhatsAppGreen,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Enquiry Submitted Successfully!",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = BrandNavy
                    )
                }
            },
            text = {
                Column {
                    Text(
                        text = "Thank you, ${lastLead?.customerName ?: "Valued Customer"}! Your enquiry for \"${lastLead?.requiredService ?: "MADLY SERVICES"}\" has been saved into our local lead management system.",
                        fontSize = 13.sp,
                        color = BrandBodyText,
                        lineHeight = 18.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Would you also like to immediately send your enquiry to Ravi Biyyala on WhatsApp for accelerated response?",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = BrandNavy
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val lead = lastLead
                        val msg = if (lead != null) {
                            "Hello MADLY SERVICES, I submitted an enquiry on your app for *${lead.requiredService}*. Name: ${lead.customerName}, Contact: ${lead.mobileNumber}, Area: ${lead.cityArea}. Requirements: ${lead.requirementsMessage.ifBlank { "Please share details." }}"
                        } else {
                            "Hello MADLY SERVICES, I submitted an enquiry. Please share details."
                        }
                        showSuccessDialog = false
                        viewModel.resetSubmitSuccess()
                        BusinessConfig.openWhatsApp(context, msg)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrandWhatsAppGreen,
                        contentColor = BrandWhite
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_whatsapp),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = BrandWhite
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Chat on WhatsApp", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = {
                        showSuccessDialog = false
                        viewModel.resetSubmitSuccess()
                    }
                ) {
                    Text("Done", fontSize = 12.sp, color = BrandNavy)
                }
            }
        )
    }
}

@Composable
private fun outlinedColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = BrandCorporateBlue,
    unfocusedBorderColor = BrandBorder,
    focusedLabelColor = BrandCorporateBlue,
    unfocusedLabelColor = BrandBodyText
)
