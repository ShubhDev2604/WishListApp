package eu.tutorials.mywishlistapp.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object ColorDarkTokens {
    val Primary = Color(0xFFDFD0B8)
    val OnPrimary = Color(0xFF222831)
    val PrimaryContainer = Color(0xFFBFAF9B) // darker beige variant
    val OnPrimaryContainer = Color(0xFF222831)
    val InversePrimary = Color(0xFF948979) // muted contrast

    val Secondary = Color(0xFF948979)
    val OnSecondary = Color(0xFF222831)
    val SecondaryContainer = Color(0xFF7A6F60) // darker brownish tone
    val OnSecondaryContainer = Color(0xFFECE3D1)

    val Tertiary = Color(0xFF80CBC4) // teal-ish soft accent (optional)
    val OnTertiary = Color(0xFF00332F)
    val TertiaryContainer = Color(0xFF4F9E99)
    val OnTertiaryContainer = Color(0xFFDFF5F2)

    val Background = Color(0xFF222831)
    val OnBackground = Color(0xFFDFD0B8)

    val Surface = Color(0xFF393E46)
    val OnSurface = Color(0xFFDFD0B8)

    val SurfaceVariant = Color(0xFF50575F) // muted gray for cards/lists
    val OnSurfaceVariant = Color(0xFFD1CFCB)

    val SurfaceTint = Primary

    val InverseSurface = Color(0xFFECE3D1) // light variant of background
    val InverseOnSurface = Color(0xFF222831)

    val Error = Color(0xFFCF6679) // Material3 default dark error
    val OnError = Color(0xFF000000)
    val ErrorContainer = Color(0xFFB00020)
    val OnErrorContainer = Color(0xFFFFFFFF)

    val Outline = Color(0xFF7D7D7D) // medium gray
    val OutlineVariant = Color(0xFF5D5D5D)

    val Scrim = Color(0x66000000) // black with opacity
}

val CustomDarkColorScheme = darkColorScheme(
    primary = ColorDarkTokens.Primary,
    onPrimary = ColorDarkTokens.OnPrimary,
    primaryContainer = ColorDarkTokens.PrimaryContainer,
    onPrimaryContainer = ColorDarkTokens.OnPrimaryContainer,
    inversePrimary = ColorDarkTokens.InversePrimary,
    secondary = ColorDarkTokens.Secondary,
    onSecondary = ColorDarkTokens.OnSecondary,
    secondaryContainer = ColorDarkTokens.SecondaryContainer,
    onSecondaryContainer = ColorDarkTokens.OnSecondaryContainer,
    tertiary = ColorDarkTokens.Tertiary,
    onTertiary = ColorDarkTokens.OnTertiary,
    tertiaryContainer = ColorDarkTokens.TertiaryContainer,
    onTertiaryContainer = ColorDarkTokens.OnTertiaryContainer,
    background = ColorDarkTokens.Background,
    onBackground = ColorDarkTokens.OnBackground,
    surface = ColorDarkTokens.Surface,
    onSurface = ColorDarkTokens.OnSurface,
    surfaceVariant = ColorDarkTokens.SurfaceVariant,
    onSurfaceVariant = ColorDarkTokens.OnSurfaceVariant,
    surfaceTint = ColorDarkTokens.SurfaceTint,
    inverseSurface = ColorDarkTokens.InverseSurface,
    inverseOnSurface = ColorDarkTokens.InverseOnSurface,
    error = ColorDarkTokens.Error,
    onError = ColorDarkTokens.OnError,
    errorContainer = ColorDarkTokens.ErrorContainer,
    onErrorContainer = ColorDarkTokens.OnErrorContainer,
    outline = ColorDarkTokens.Outline,
    outlineVariant = ColorDarkTokens.OutlineVariant,
    scrim = ColorDarkTokens.Scrim
)


val CustomLightColorScheme = lightColorScheme(
    primary = Color(0xFF4C5FD5),              // #4C5FD5
    onPrimary = Color(0xFFFFFFFF),            // White
    primaryContainer = Color(0xFFE1E4FB),     // primary_shade1
    onPrimaryContainer = Color(0xFF2B3A8C),   // primary_shade5

    secondary = Color(0xFFFEC260),            // #FEC260
    onSecondary = Color(0xFF1F1F1F),          // Dark gray (from your resource)

    secondaryContainer = Color(0xFFFFE7B3),   // light shade of secondary (generated)
    onSecondaryContainer = Color(0xFF1F1F1F), // Matching onSecondary

    tertiary = Color(0xFF03DAC5),             // teal_200
    onTertiary = Color(0xFF000000),           // black
    tertiaryContainer = Color(0xFFB2FFF2),    // light teal-ish
    onTertiaryContainer = Color(0xFF00332F),

    background = Color(0xFFF9F9FB),           // #F9F9FB
    onBackground = Color(0xFF2D2D2D),         // #2D2D2D

    surface = Color(0xFFFFFFFF),              // White
    onSurface = Color(0xFF2D2D2D),            // Same as onBackground

    surfaceVariant = Color(0xFFE1E1E7),       // divider
    onSurfaceVariant = Color(0xFF3C3C3C),     // deeper gray

    surfaceTint = Color(0xFF4C5FD5),          // same as primary

    inverseSurface = Color(0xFF2D2D2D),       // onBackground
    inverseOnSurface = Color(0xFFFFFFFF),     // white

    error = Color(0xFFB00020),                // default Material error
    onError = Color(0xFFFFFFFF),              // white
    errorContainer = Color(0xFFFFDAD4),       // light red container
    onErrorContainer = Color(0xFF410002),     // Material default

    outline = Color(0xFF9E9E9E),              // light neutral gray
    outlineVariant = Color(0xFFDADADA),       // very soft divider
    scrim = Color(0x66000000)                 // semi-transparent black
)
