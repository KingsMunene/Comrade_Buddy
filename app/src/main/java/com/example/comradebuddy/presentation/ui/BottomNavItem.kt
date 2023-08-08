package com.example.comradebuddy.presentation.ui

import androidx.compose.ui.graphics.vector.ImageVector

// bottom nav Item class that defines what a bottomNavItem contains
data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector
)
