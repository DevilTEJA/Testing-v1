package com.example.data.model

import com.example.R

data class ServiceItem(
    val id: String,
    val title: String,
    val shortDescription: String,
    val iconRes: Int,
    val ctaLabel: String,
    val defaultPrefilledMessage: String,
    val subServices: List<String>,
    val detailedOverview: String,
    val benefits: List<Pair<String, String>>,
    val useCases: List<String>,
    val processSteps: List<Pair<String, String>>,
    val faqs: List<Pair<String, String>>,
    val regulatoryNote: String? = null,
    val targetAudience: String
)

object ServiceCatalog {
    val services = listOf(
        ServiceItem(
            id = "insurance",
            title = "All Types of Insurance",
            shortDescription = "Explore insurance solutions designed around your protection needs, family goals, and financial priorities.",
            iconRes = R.drawable.ic_service_insurance,
            ctaLabel = "Enquire About Insurance",
            defaultPrefilledMessage = "Hello MADLY SERVICES, I would like to enquire about Insurance plans. Please share the available options and next steps.",
            subServices = listOf(
                "Life Insurance",
                "Health Insurance",
                "Term Insurance",
                "Vehicle / Motor Insurance",
                "Home & Property Insurance",
                "Travel Insurance",
                "Child Education & Retirement Planning",
                "Savings & Investment-linked Insurance Plans (subject to availability)"
            ),
            detailedOverview = "Protecting your family, vehicle, home, and health requires the right coverage tailored to your specific situation. MADLY SERVICES assists individuals and families in Hyderabad with objective guidance, policy comparisons, and streamlined documentation to help you find suitable protection for every milestone.",
            benefits = listOf(
                "Personalized Guidance" to "Custom policy recommendations matched with your family's budget and coverage requirements.",
                "Multiple Protection Categories" to "Single point of enquiry for life, health, auto, home, and business insurance needs.",
                "Hassle-Free Renewals" to "Prompt assistance with renewal timelines, claims guidance, and documentation checklist.",
                "Clarity & Transparency" to "No jargon; clear explanation of coverage limits, copay terms, and deductible details."
            ),
            useCases = listOf(
                "Families seeking comprehensive health coverage with cashless hospital networks in Hyderabad",
                "Vehicle owners renewing two-wheeler or four-wheeler motor insurance without lapse",
                "Parents planning long-term education funds and financial security for their children",
                "Homeowners and businesses wanting structure & asset protection against fire or theft"
            ),
            processSteps = listOf(
                "1. Share Requirements" to "Tell us your protection objectives, family member ages, or vehicle details.",
                "2. Options Review" to "We present suitable policy configurations with benefits and exclusions explained.",
                "3. Documentation & Verification" to "Collect necessary verification documents and KYC details.",
                "4. Issuance & Continued Support" to "Receive your policy documentation with continuous renewal assistance."
            ),
            faqs = listOf(
                "How do I enquire about insurance plans with MADLY SERVICES?" to "You can submit an enquiry form directly in this app, call Ravi Biyyala at 8499922598, or connect over WhatsApp at 8500321299 for prompt assistance.",
                "Can you help compare different health and term insurance options?" to "Yes, we evaluate coverage options, waiting periods, network hospitals in Telangana, and premium structures to help you pick the best fit.",
                "Are insurance policies issued directly by MADLY SERVICES?" to "MADLY SERVICES operates as an independent guidance and facilitation partner. Policy issuance, underwriting terms, and claims are governed by the respective licensed insurer according to IRDAI guidelines."
            ),
            regulatoryNote = "Notice: Insurance eligibility, benefits, premiums, exclusions, and terms depend strictly on the specific policy chosen. MADLY SERVICES facilitates enquiries and customer assistance; policy issuance and underwriting are subject to applicable licensing and statutory insurer regulations.",
            targetAudience = "Individuals, Families & Small Business Owners"
        ),
        ServiceItem(
            id = "network",
            title = "Network Maintenance",
            shortDescription = "Keep your home or business network connected, stable, and ready for everyday operations.",
            iconRes = R.drawable.ic_service_network,
            ctaLabel = "Request Network Support",
            defaultPrefilledMessage = "Hello MADLY SERVICES, I would like to request Network Maintenance support for my setup. Please share details and availability.",
            subServices = listOf(
                "Structured LAN Cabling & Wi-Fi Setup",
                "Router & Switch Configuration",
                "Network Troubleshooting & Speed Diagnostics",
                "Office & Home Network Support",
                "Wi-Fi Connectivity Optimization & Dead-zone Elimination",
                "Preventive Network Maintenance",
                "Annual Maintenance Contracts (AMC) for Businesses"
            ),
            detailedOverview = "Network downtime hampers productivity, disrupts POS systems, and frustrates staff and customers. MADLY SERVICES provides end-to-end network maintenance and setup services across Hyderabad. Whether configuring multi-floor office networks, optimizing bandwidth, or resolving persistent Wi-Fi dropouts, our team ensures stable, reliable connectivity.",
            benefits = listOf(
                "High Stability & Speed" to "Properly configured bandwidth allocation, traffic prioritization, and clean cabling.",
                "Reduced Downtime" to "Quick diagnostic turnaround for network hardware failures and ISP connectivity issues.",
                "Scalable Infrastructure" to "Future-ready network layouts that accommodate additional devices, printers, and terminals.",
                "Custom AMC Packages" to "Scheduled health inspections and priority technician dispatch for commercial spaces."
            ),
            useCases = listOf(
                "Offices experiencing slow internet or dropped connections across departments",
                "Retail stores needing uninterrupted LAN connections for billing counters and card swipe machines",
                "Homes and large villas needing seamless Wi-Fi roaming without dead zones",
                "Commercial hubs seeking preventive network maintenance contracts"
            ),
            processSteps = listOf(
                "1. On-Site / Remote Assessment" to "Examine your current cabling, switches, router load, and coverage map.",
                "2. Diagnosis & Solution Plan" to "Identify bottlenecks, wiring faults, or equipment limitations.",
                "3. Execution & Configuration" to "Reconfigure switches, route cables neatly, and optimize Wi-Fi channels.",
                "4. Testing & Verification" to "Perform latency, throughput, and stress tests across all access points."
            ),
            faqs = listOf(
                "Can I request ongoing network maintenance (AMC)?" to "Yes, MADLY SERVICES provides both on-demand incident support and scheduled Annual Maintenance Contracts tailored for retail shops, offices, and restaurants.",
                "Do you support both residential homes and commercial businesses?" to "Yes, we handle residential home network setups as well as multi-node corporate office infrastructures across Hyderabad.",
                "How quickly can a technician inspect network issues?" to "For priority requests in Hyderabad, we coordinate same-day or next-business-day inspection based on scheduling and urgency."
            ),
            regulatoryNote = null,
            targetAudience = "Offices, Retailers, Cafés & Home Offices"
        ),
        ServiceItem(
            id = "cctv",
            title = "CCTV Service & Maintenance",
            shortDescription = "Improve visibility and security with professionally planned CCTV installation and maintenance solutions.",
            iconRes = R.drawable.ic_service_cctv,
            ctaLabel = "Request CCTV Service",
            defaultPrefilledMessage = "Hello MADLY SERVICES, I would like to enquire about CCTV Installation and Maintenance. Please share available options and next steps.",
            subServices = listOf(
                "New CCTV System Installation & Site Survey",
                "Camera Configuration & Angle Calibration",
                "High-Definition (HD) and 4K Camera Options",
                "Mobile Remote-Viewing & Cloud/App Setup",
                "DVR / NVR Configuration & Hard Drive Storage Setup",
                "Camera Troubleshooting, Cable Repair & Lens Cleaning",
                "Home, Shop, Office, Restaurant & Warehouse Solutions"
            ),
            detailedOverview = "Security and surveillance demand dependable hardware and meticulous camera placement. MADLY SERVICES offers professional CCTV camera installation, re-alignment, maintenance, and mobile live-view setup. We service bullet, dome, PTZ, and IP cameras to keep your residential and commercial premises covered 24/7.",
            benefits = listOf(
                "Optimal Coverage Angles" to "Careful blind-spot elimination across entrances, cash registers, aisles, and storage rooms.",
                "Live Mobile Surveillance" to "Access your live feeds and historical recordings securely on your smartphone anytime.",
                "Crisp Night Vision & HD Clarity" to "High-resolution camera options ensuring clear facial and license plate recognition.",
                "Reliable Storage Setup" to "Properly calculated DVR/NVR surveillance hard drives ensuring continuous loop recording."
            ),
            useCases = listOf(
                "Retail shop owners wanting to monitor cashier desks, display shelves, and customer entrances",
                "Gated communities and individual homeowners looking for perimeter security and door monitoring",
                "Warehouses requiring night-vision coverage across loading docks and inventory bays",
                "Offices needing compliance recording and employee attendance monitoring"
            ),
            processSteps = listOf(
                "1. Site Survey & Requirement Discussion" to "We analyze camera count, field-of-view, lighting, and cable routing.",
                "2. Equipment Recommendation" to "Suggest suitable dome, bullet, or IP cameras and appropriate DVR/NVR storage.",
                "3. Professional Installation" to "Mount cameras securely, pull concealed cables, and connect power supplies.",
                "4. App Configuration & Handover" to "Configure smartphone live streaming, review playback controls, and train staff."
            ),
            faqs = listOf(
                "Can I monitor my CCTV cameras on my smartphone remotely?" to "Yes, we configure official remote viewing apps so you can check live feeds and playback from anywhere with an active internet connection.",
                "Can you repair or upgrade my existing CCTV cameras?" to "Yes, we service existing setups, repair faulty power supplies/cables, replace damaged cameras, or upgrade older analog systems to HD/IP cameras.",
                "Do you provide warranties on new CCTV equipment?" to "All newly supplied hardware carries manufacturer warranty according to OEM policies, and we back our installation work with dedicated technical support."
            ),
            regulatoryNote = "Notice: CCTV systems enhance visual security and deterrence. Uninterrupted surveillance requires continuous electrical power, functioning network connectivity, and regular storage maintenance.",
            targetAudience = "Shops, Homes, Restaurants, Offices & Warehouses"
        ),
        ServiceItem(
            id = "router",
            title = "Router Sales & Services",
            shortDescription = "Find the right networking equipment and get help with installation, configuration, and Wi-Fi coverage.",
            iconRes = R.drawable.ic_service_router,
            ctaLabel = "Enquire About Routers",
            defaultPrefilledMessage = "Hello MADLY SERVICES, I would like to enquire about Router Sales and Configuration. Please share available models and guidance.",
            subServices = listOf(
                "Dual-Band & Wi-Fi 6 Router Sales",
                "Multiple Brands & Models (subject to availability)",
                "Professional Router Installation & Setup",
                "Wi-Fi Range Optimization & Channel Calibration",
                "Mesh Wi-Fi Systems for Large Spaces",
                "PPPoE, Static IP & ISP Bridge Setup",
                "Guest Network Isolation & Security Settings"
            ),
            detailedOverview = "A good internet connection is only as good as the router distributing it. MADLY SERVICES assists with sourcing the right router hardware for your space, setting up high-performance Wi-Fi 6 or Mesh systems, and configuring advanced security settings so you enjoy stable, seamless internet throughout your home or business.",
            benefits = listOf(
                "Tailored Device Selection" to "Get the exact router capacity needed for your bandwidth and concurrent device count.",
                "Seamless Mesh Coverage" to "Eliminate dead spots in multi-story houses and expansive office spaces.",
                "Enhanced Security" to "Strong WPA3 encryption, guest networks, and parental/firewall controls properly configured.",
                "Zero Setup Headache" to "Our technicians handle ISP login configuration, SSID naming, and signal tuning."
            ),
            useCases = listOf(
                "Apartments and villas struggling with weak Wi-Fi signals in bedrooms or balconies",
                "Cafés and restaurants offering secure guest Wi-Fi separate from billing counters",
                "Offices needing high-throughput Wi-Fi 6 routers supporting 40+ concurrent devices",
                "Users upgrading to high-speed fiber internet needing a gigabit-capable router"
            ),
            processSteps = listOf(
                "1. Space & Bandwidth Assessment" to "Determine square footage, wall thickness, ISP plan, and number of connected devices.",
                "2. Model Recommendation" to "Present reliable router/mesh models matching your exact budget and performance needs.",
                "3. Sourcing & Unboxing" to "Provide verified, authentic equipment with manufacturer warranty.",
                "4. Installation & Channel Tuning" to "Set up frequencies (2.4GHz / 5GHz), optimize placement, and test signal strength."
            ),
            faqs = listOf(
                "Can you help configure an existing router I already bought?" to "Yes! Our technicians can visit and configure your existing router, optimize settings, update firmware, or integrate it into a larger network.",
                "What is the advantage of a Mesh Wi-Fi system?" to "Mesh systems use multiple coordinated access points with a single Wi-Fi name, giving you automatic, seamless roaming without disconnecting as you move around.",
                "Which brands do you supply?" to "We recommend and supply popular, verified brands based on current market availability and your technical requirements."
            ),
            regulatoryNote = null,
            targetAudience = "Homeowners, Offices, Coworking Spaces & Retailers"
        ),
        ServiceItem(
            id = "billing",
            title = "Restaurant Billing Software Services",
            shortDescription = "Make restaurant operations easier with billing software, menu management, inventory tools, and business reporting.",
            iconRes = R.drawable.ic_service_billing,
            ctaLabel = "Book a Software Demo",
            defaultPrefilledMessage = "Hello MADLY SERVICES, I would like to book a demo for Restaurant Billing Software. Please share details and demonstration availability.",
            subServices = listOf(
                "Fast POS Billing Software & Thermal Printer Integration",
                "Menu & Item Catalog Management with Tax / GST Configuration",
                "Dine-in Table Management & Order Status Tracking",
                "Kitchen Order Ticket (KOT) Management & Kitchen Displays",
                "Ingredient & Inventory Tracking",
                "Daily Sales Reports, Cash Drawer Audit & Analytics",
                "Staff Permissions, Cashier Shifts & Anti-Theft Controls",
                "On-Site Software Installation, Training & Ongoing Technical Support"
            ),
            detailedOverview = "Running a restaurant, café, bakery, or cloud kitchen requires quick billing, zero order mix-ups, and airtight sales auditing. MADLY SERVICES provides modern POS restaurant billing software solutions, hardware pairing (thermal printers, barcode scanners, cash drawers), and on-site staff training to accelerate your daily workflow.",
            benefits = listOf(
                "Blazing Fast Checkout" to "Generate bills and receipts in under 3 seconds during peak dining hours.",
                "Zero Order Confusion" to "Instant KOT dispatch to kitchen or bar printers eliminates miscommunication.",
                "Comprehensive Reports" to "Review daily revenue, top-selling dishes, tax breakdown, and profit margins at a glance.",
                "Complete Staff Training" to "We train your billing staff and managers thoroughly so your team is confident from day one."
            ),
            useCases = listOf(
                "Quick Service Restaurants (QSR) and takeaway counters needing ultra-fast counter billing",
                "Fine dining and casual restaurants requiring table management and captain ordering",
                "Cafés and bakeries managing combo items, add-ons, and barcode scanning",
                "Sweet shops and food courts requiring weighing scale integration and multi-counter tokens"
            ),
            processSteps = listOf(
                "1. Discovery & Demo" to "We demonstrate software features and understand your restaurant format (dine-in, takeaway, QSR).",
                "2. Menu & Tax Setup" to "Upload your menu items, categories, pricing, and GST configurations.",
                "3. Hardware Setup" to "Connect billing PCs/tablets, receipt printers, and cash drawers.",
                "4. Staff Training & Go-Live" to "Conduct live billing simulations and provide ongoing operational support."
            ),
            faqs = listOf(
                "Do you provide a software demonstration before purchase?" to "Yes! You can book a free live demonstration directly through this app or via WhatsApp, and we will walk through the exact billing and management features.",
                "Does the software support GST billing and thermal receipt printing?" to "Yes, standard 2-inch and 3-inch thermal printers are supported with customizable GST tax invoices and restaurant logos.",
                "Can it operate offline if the internet disconnects?" to "Yes, our recommended POS software handles billing offline, ensuring checkout never stops even during ISP network interruptions."
            ),
            regulatoryNote = "Notice: Specific POS features, third-party food aggregator integrations, and cloud sync depend on the chosen software tier and licensing package.",
            targetAudience = "Restaurants, Cafés, Bakeries, Food Courts & Cloud Kitchens"
        )
    )

    fun getById(id: String): ServiceItem? = services.find { it.id == id }
}
