package com.example.data.config

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import java.net.URLEncoder

object BusinessConfig {
    const val BRAND_NAME = "MADLY SERVICES"
    const val TAGLINE = "Your One-Stop Solution Partner"
    const val SECONDARY_TAGLINE = "Reliable Services for a Better Tomorrow"
    const val CONTACT_PERSON = "Ravi Biyyala"
    const val PHONE_NUMBER = "8499922598"
    const val WHATSAPP_NUMBER = "8500321299"
    const val PRIMARY_SERVICE_AREA = "Hyderabad, Telangana, India"
    const val BUSINESS_HOURS = "Mon - Sat: 9:00 AM - 8:00 PM | Sun: By appointment & emergency enquiries"
    const val ADMIN_DEFAULT_PIN = "1234"

    fun openDialer(context: Context, phoneNumber: String = PHONE_NUMBER) {
        try {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Could not open phone dialer: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    fun openWhatsApp(context: Context, prefilledMessage: String? = null, number: String = WHATSAPP_NUMBER) {
        try {
            val formattedNumber = if (number.startsWith("91")) number else "91$number"
            val message = prefilledMessage ?: "Hello MADLY SERVICES, I would like to enquire about your services. Please share the available options and next steps."
            val encodedMessage = URLEncoder.encode(message, "UTF-8")
            val url = "https://wa.me/$formattedNumber?text=$encodedMessage"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Could not launch WhatsApp: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}
