package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = BrandCorporateBlue,
    onPrimary = BrandWhite,
    primaryContainer = BrandNavy,
    onPrimaryContainer = BrandWhite,
    secondary = BrandCorporateBlue,
    onSecondary = BrandWhite,
    tertiary = BrandAccentRed,
    background = BrandNavyDark,
    surface = BrandNavy,
    onBackground = BrandWhite,
    onSurface = BrandWhite,
    surfaceVariant = BrandNavy,
    onSurfaceVariant = BrandSkyTint,
    outline = BrandBorder
)

private val LightColorScheme = lightColorScheme(
    primary = BrandNavy,
    onPrimary = BrandWhite,
    primaryContainer = BrandSkyTint,
    onPrimaryContainer = BrandNavy,
    secondary = BrandCorporateBlue,
    onSecondary = BrandWhite,
    secondaryContainer = BrandSkyTint,
    onSecondaryContainer = BrandCorporateBlue,
    tertiary = BrandAccentRed,
    onTertiary = BrandWhite,
    background = BrandLightBg,
    surface = BrandSurface,
    onBackground = BrandBodyText,
    onSurface = BrandNavy,
    surfaceVariant = BrandSurfaceVariant,
    onSurfaceVariant = BrandBodyText,
    outline = BrandBorder
)

@Composable
fun MadlyServicesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // Preserve corporate branding consistency across all Android devices
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
