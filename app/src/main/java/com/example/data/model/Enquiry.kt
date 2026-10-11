package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "enquiries")
data class Enquiry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val customerName: String,
    val mobileNumber: String,
    val email: String = "",
    val customerType: String,
    val requiredService: String,
    val cityArea: String,
    val requirementsMessage: String,
    val preferredContactMethod: String = "WhatsApp",
    val consentGiven: Boolean = true,
    val status: String = "New", // New, Contacted, In Progress, Converted, Closed
    val timestamp: Long = System.currentTimeMillis(),
    val sourcePage: String = "Home",
    val followUpNotes: String = ""
)

enum class LeadStatus(val label: String) {
    NEW("New"),
    CONTACTED("Contacted"),
    IN_PROGRESS("In Progress"),
    CONVERTED("Converted"),
    CLOSED("Closed")
}

val ALL_SERVICE_OPTIONS = listOf(
    "All Types of Insurance",
    "Network Maintenance",
    "CCTV Service & Maintenance",
    "Router Sales & Services",
    "Restaurant Billing Software",
    "Multiple Services",
    "Other Enquiry"
)

val CUSTOMER_TYPE_OPTIONS = listOf(
    "Home & Residential",
    "Retail Shop",
    "Office / Corporate",
    "Restaurant & Café",
    "Small Business",
    "Warehouse & Commercial"
)

val PREFERRED_CONTACT_METHODS = listOf(
    "WhatsApp",
    "Phone Call",
    "Email"
)
