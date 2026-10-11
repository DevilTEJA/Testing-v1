package com.example.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.BrandWhite
import com.example.ui.viewmodel.MainViewModel

@Composable
fun QuoteDialog(
    viewModel: MainViewModel,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .verticalScroll(rememberScrollState())
                .padding(vertical = 16.dp),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
            color = BrandWhite
        ) {
            EnquiryFormCard(
                viewModel = viewModel,
                title = "Get a Free Consultation / Quote",
                subtitle = "Speak directly with our technical team in Hyderabad to evaluate your requirements and get an accurate estimate.",
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}
