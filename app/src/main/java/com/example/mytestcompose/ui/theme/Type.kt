package com.example.mytestcompose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

import com.example.mytestcompose.R

val latoBlack   = FontFamily(Font(R.font.lato_black  ))
val latoBold    = FontFamily(Font(R.font.lato_bold   ))
val latoLight   = FontFamily(Font(R.font.lato_light  ))
val latoRegular = FontFamily(Font(R.font.lato_regular))
val latoThin    = FontFamily(Font(R.font.lato_thin   ))

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily    = latoRegular,
        fontWeight    = FontWeight.Normal,
        fontSize      = 16.sp,
        lineHeight    = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily    = latoRegular,
        fontWeight    = FontWeight.Medium,
        fontSize      = 11.sp,
        lineHeight    = 16.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily    = latoBold,
        fontWeight    = FontWeight.Normal,
        fontSize      = 22.sp,
        lineHeight    = 28.sp,
        letterSpacing = 0.sp
    )
)

val style = Typography.bodyLarge.copy(
    shadow = Shadow(
        color      = Color.Black,
        offset     = Offset(x = 4f, y = 4f),
        blurRadius = 8f
    )
)

val StyleTitle = Typography.titleLarge.copy(
    shadow = Shadow(
        color      = Color.Black,
        offset     = Offset(x = 4f, y = 4f),
        blurRadius = 8f
    )
)