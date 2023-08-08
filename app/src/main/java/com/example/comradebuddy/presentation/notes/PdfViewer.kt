package com.example.comradebuddy.presentation.notes

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.comradebuddy.AppNavigation
import com.example.comradebuddy.presentation.ui.AppTopAppBar
import com.example.comradebuddy.presentation.ui.BottomNavBar
import com.example.comradebuddy.presentation.ui.BottomNavItem

// PDf view Composable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PdfDocViewer(
    fileName: String,
    unitName: String,
    pdfViewModel: PdfViewModel = viewModel()
) {
        // Call the pdfViewer composable function in the viewModel
        pdfViewModel.PdfViewer(fileName, unitName )

}
