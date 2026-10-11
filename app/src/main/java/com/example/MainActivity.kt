package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.BottomContactBar
import com.example.ui.components.FloatingWhatsAppButton
import com.example.ui.components.MadlyHeader
import com.example.ui.components.QuoteDialog
import com.example.ui.screens.AboutScreen
import com.example.ui.screens.AdminScreen
import com.example.ui.screens.ContactScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LegalScreen
import com.example.ui.screens.ServiceDetailScreen
import com.example.ui.screens.ServicesOverviewScreen
import com.example.ui.theme.MadlyServicesTheme
import com.example.ui.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MadlyServicesTheme {
                MadlyApp()
            }
        }
    }
}

@Composable
fun MadlyApp(viewModel: MainViewModel = viewModel()) {
    val currentScreen by viewModel.currentScreen.collectAsState()
    val selectedServiceId by viewModel.selectedServiceId.collectAsState()
    val isQuoteDialogOpen by viewModel.isQuoteDialogOpen.collectAsState()

    // Handle Android system back button
    BackHandler(enabled = currentScreen != "home") {
        viewModel.navigateBack()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MadlyHeader(
                currentScreen = currentScreen,
                onNavigate = { screen, serviceId ->
                    viewModel.navigateTo(screen, serviceId)
                },
                onOpenQuoteDialog = {
                    viewModel.openQuoteDialog(null)
                }
            )
        },
        bottomBar = {
            BottomContactBar(
                onOpenQuoteDialog = {
                    viewModel.openQuoteDialog(null)
                }
            )
        },
        floatingActionButton = {
            // Floating WhatsApp launcher
            FloatingWhatsAppButton()
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedContent(
                targetState = currentScreen,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "ScreenTransition"
            ) { screen ->
                when (screen) {
                    "home" -> HomeScreen(
                        viewModel = viewModel,
                        onNavigateToService = { serviceId ->
                            viewModel.navigateTo("service_detail", serviceId)
                        },
                        onNavigateToSection = { section ->
                            viewModel.navigateTo(section)
                        },
                        onOpenQuote = { serviceName ->
                            viewModel.openQuoteDialog(serviceName)
                        }
                    )

                    "services_overview" -> ServicesOverviewScreen(
                        onNavigateToService = { serviceId ->
                            viewModel.navigateTo("service_detail", serviceId)
                        },
                        onOpenQuote = { serviceName ->
                            viewModel.openQuoteDialog(serviceName)
                        }
                    )

                    "service_detail" -> ServiceDetailScreen(
                        serviceId = selectedServiceId ?: "insurance",
                        viewModel = viewModel,
                        onBack = { viewModel.navigateBack() },
                        onSelectOtherService = { otherId ->
                            viewModel.navigateTo("service_detail", otherId)
                        }
                    )

                    "about" -> AboutScreen(
                        onNavigateToService = { serviceId ->
                            viewModel.navigateTo("service_detail", serviceId)
                        },
                        onContact = { viewModel.navigateTo("contact") }
                    )

                    "contact" -> ContactScreen(
                        viewModel = viewModel
                    )

                    "admin" -> AdminScreen(
                        viewModel = viewModel
                    )

                    "legal" -> LegalScreen()

                    else -> HomeScreen(
                        viewModel = viewModel,
                        onNavigateToService = { serviceId ->
                            viewModel.navigateTo("service_detail", serviceId)
                        },
                        onNavigateToSection = { section ->
                            viewModel.navigateTo(section)
                        },
                        onOpenQuote = { serviceName ->
                            viewModel.openQuoteDialog(serviceName)
                        }
                    )
                }
            }

            // Global Quote Dialog accessible from any screen
            if (isQuoteDialogOpen) {
                QuoteDialog(
                    viewModel = viewModel,
                    onDismiss = { viewModel.closeQuoteDialog() }
                )
            }
        }
    }
}
