package com.example.comradebuddy.presentation.ui

import androidx.compose.ui.graphics.vector.ImageVector

// Class defining the components of a NavDrawer item
data class NavDrawerItem(
    val id: String,
    val title: String,
    val icon: ImageVector
)