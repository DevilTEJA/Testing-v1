package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.config.BusinessConfig
import com.example.data.local.AppDatabase
import com.example.data.model.ALL_SERVICE_OPTIONS
import com.example.data.model.CUSTOMER_TYPE_OPTIONS
import com.example.data.model.Enquiry
import com.example.data.model.LeadStatus
import com.example.data.model.PREFERRED_CONTACT_METHODS
import com.example.data.repository.EnquiryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class EnquiryFormState(
    val customerName: String = "",
    val mobileNumber: String = "",
    val email: String = "",
    val customerType: String = CUSTOMER_TYPE_OPTIONS.first(),
    val requiredService: String = ALL_SERVICE_OPTIONS.first(),
    val cityArea: String = "Hyderabad",
    val requirementsMessage: String = "",
    val preferredContactMethod: String = PREFERRED_CONTACT_METHODS.first(),
    val consentGiven: Boolean = true,
    val sourcePage: String = "Home",

    // Validation errors
    val nameError: String? = null,
    val mobileError: String? = null,
    val consentError: String? = null,

    // Status
    val isSubmitting: Boolean = false,
    val submitSuccess: Boolean = false,
    val lastSubmittedEnquiry: Enquiry? = null,
    val submitErrorMessage: String? = null
)

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: EnquiryRepository

    init {
        val database = AppDatabase.getInstance(application)
        repository = EnquiryRepository(database.enquiryDao())
    }

    // Navigation Screen State
    private val _currentScreen = MutableStateFlow<String>("home")
    val currentScreen: StateFlow<String> = _currentScreen.asStateFlow()

    private val _selectedServiceId = MutableStateFlow<String?>(null)
    val selectedServiceId: StateFlow<String?> = _selectedServiceId.asStateFlow()

    // Screen stack for back navigation
    private val screenStack = mutableListOf("home")

    fun navigateTo(screen: String, serviceId: String? = null) {
        if (serviceId != null) {
            _selectedServiceId.value = serviceId
        }
        if (_currentScreen.value != screen) {
            screenStack.add(screen)
            _currentScreen.value = screen
        }
    }

    fun navigateBack(): Boolean {
        if (screenStack.size > 1) {
            screenStack.removeAt(screenStack.size - 1)
            val previous = screenStack.last()
            _currentScreen.value = previous
            return true
        }
        return false
    }

    // Quick Quote Dialog
    private val _isQuoteDialogOpen = MutableStateFlow(false)
    val isQuoteDialogOpen: StateFlow<Boolean> = _isQuoteDialogOpen.asStateFlow()

    fun openQuoteDialog(serviceName: String? = null) {
        if (serviceName != null) {
            _formState.value = _formState.value.copy(requiredService = serviceName)
        }
        _isQuoteDialogOpen.value = true
    }

    fun closeQuoteDialog() {
        _isQuoteDialogOpen.value = false
    }

    // Form State
    private val _formState = MutableStateFlow(EnquiryFormState())
    val formState: StateFlow<EnquiryFormState> = _formState.asStateFlow()

    fun updateName(name: String) {
        _formState.value = _formState.value.copy(customerName = name, nameError = null)
    }

    fun updateMobile(mobile: String) {
        val digitsOnly = mobile.filter { it.isDigit() }
        _formState.value = _formState.value.copy(mobileNumber = digitsOnly, mobileError = null)
    }

    fun updateEmail(email: String) {
        _formState.value = _formState.value.copy(email = email)
    }

    fun updateCustomerType(type: String) {
        _formState.value = _formState.value.copy(customerType = type)
    }

    fun updateRequiredService(service: String) {
        _formState.value = _formState.value.copy(requiredService = service)
    }

    fun updateCityArea(cityArea: String) {
        _formState.value = _formState.value.copy(cityArea = cityArea)
    }

    fun updateRequirementsMessage(message: String) {
        _formState.value = _formState.value.copy(requirementsMessage = message)
    }

    fun updatePreferredContactMethod(method: String) {
        _formState.value = _formState.value.copy(preferredContactMethod = method)
    }

    fun updateConsent(consent: Boolean) {
        _formState.value = _formState.value.copy(consentGiven = consent, consentError = null)
    }

    fun setFormDefaults(service: String, source: String) {
        _formState.value = _formState.value.copy(
            requiredService = service,
            sourcePage = source
        )
    }

    fun submitEnquiry(onSuccess: (Enquiry) -> Unit) {
        val current = _formState.value

        var hasError = false
        var nameErr: String? = null
        var mobileErr: String? = null
        var consentErr: String? = null

        if (current.customerName.trim().length < 2) {
            nameErr = "Please enter your full name (minimum 2 characters)"
            hasError = true
        }

        if (current.mobileNumber.trim().length < 10) {
            mobileErr = "Please enter a valid 10-digit mobile number"
            hasError = true
        }

        if (!current.consentGiven) {
            consentErr = "You must agree to be contacted regarding this enquiry"
            hasError = true
        }

        if (hasError) {
            _formState.value = current.copy(
                nameError = nameErr,
                mobileError = mobileErr,
                consentError = consentErr
            )
            return
        }

        _formState.value = current.copy(isSubmitting = true)

        viewModelScope.launch {
            try {
                val enquiry = Enquiry(
                    customerName = current.customerName.trim(),
                    mobileNumber = current.mobileNumber.trim(),
                    email = current.email.trim(),
                    customerType = current.customerType,
                    requiredService = current.requiredService,
                    cityArea = if (current.cityArea.isBlank()) "Hyderabad" else current.cityArea.trim(),
                    requirementsMessage = current.requirementsMessage.trim(),
                    preferredContactMethod = current.preferredContactMethod,
                    consentGiven = current.consentGiven,
                    status = LeadStatus.NEW.label,
                    timestamp = System.currentTimeMillis(),
                    sourcePage = current.sourcePage
                )

                val generatedId = repository.submitEnquiry(enquiry)
                val savedEnquiry = enquiry.copy(id = generatedId)

                _formState.value = EnquiryFormState(
                    submitSuccess = true,
                    lastSubmittedEnquiry = savedEnquiry,
                    requiredService = current.requiredService
                )
                onSuccess(savedEnquiry)
            } catch (e: Exception) {
                _formState.value = current.copy(
                    isSubmitting = false,
                    submitErrorMessage = "Failed to submit enquiry: ${e.message}"
                )
            }
        }
    }

    fun resetSubmitSuccess() {
        _formState.value = _formState.value.copy(submitSuccess = false, lastSubmittedEnquiry = null)
    }

    // Admin Dashboard State & Logic
    private val _isAdminAuthenticated = MutableStateFlow(false)
    val isAdminAuthenticated: StateFlow<Boolean> = _isAdminAuthenticated.asStateFlow()

    private val _adminPinInput = MutableStateFlow("")
    val adminPinInput: StateFlow<String> = _adminPinInput.asStateFlow()

    private val _adminPinError = MutableStateFlow<String?>(null)
    val adminPinError: StateFlow<String?> = _adminPinError.asStateFlow()

    fun updateAdminPin(pin: String) {
        _adminPinInput.value = pin
        _adminPinError.value = null
    }

    fun verifyAdminPin(): Boolean {
        if (_adminPinInput.value == BusinessConfig.ADMIN_DEFAULT_PIN) {
            _isAdminAuthenticated.value = true
            _adminPinError.value = null
            return true
        } else {
            _adminPinError.value = "Incorrect PIN. Default is ${BusinessConfig.ADMIN_DEFAULT_PIN}."
            return false
        }
    }

    fun adminLogout() {
        _isAdminAuthenticated.value = false
        _adminPinInput.value = ""
    }

    // Admin filters
    val adminSearchQuery = MutableStateFlow("")
    val adminServiceFilter = MutableStateFlow("All")
    val adminStatusFilter = MutableStateFlow("All")

    val enquiries: StateFlow<List<Enquiry>> = combine(
        repository.allEnquiries,
        adminSearchQuery,
        adminServiceFilter,
        adminStatusFilter
    ) { all, query, service, status ->
        all.filter { item ->
            val matchesQuery = query.isBlank() ||
                item.customerName.contains(query, ignoreCase = true) ||
                item.mobileNumber.contains(query) ||
                item.cityArea.contains(query, ignoreCase = true) ||
                item.requirementsMessage.contains(query, ignoreCase = true)

            val matchesService = service == "All" || item.requiredService == service
            val matchesStatus = status == "All" || item.status == status

            matchesQuery && matchesService && matchesStatus
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun updateLeadStatus(id: Long, newStatus: String) {
        viewModelScope.launch {
            repository.updateStatus(id, newStatus)
        }
    }

    fun updateLeadNotes(id: Long, notes: String) {
        viewModelScope.launch {
            repository.updateNotes(id, notes)
        }
    }

    fun deleteLead(id: Long) {
        viewModelScope.launch {
            repository.deleteEnquiry(id)
        }
    }

    fun generateCsvExport(leads: List<Enquiry>): String {
        val header = "ID,Name,Mobile,Email,Customer Type,Service,City/Area,Method,Status,Date,Notes\n"
        val rows = leads.joinToString("\n") { lead ->
            val dateStr = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm", java.util.Locale.getDefault()).format(java.util.Date(lead.timestamp))
            "\"${lead.id}\",\"${lead.customerName.replace("\"", "\"\"")}\",\"${lead.mobileNumber}\",\"${lead.email}\",\"${lead.customerType}\",\"${lead.requiredService}\",\"${lead.cityArea}\",\"${lead.preferredContactMethod}\",\"${lead.status}\",\"$dateStr\",\"${lead.followUpNotes.replace("\"", "\"\"")}\""
        }
        return header + rows
    }
}
