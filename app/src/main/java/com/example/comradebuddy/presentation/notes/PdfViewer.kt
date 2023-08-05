package com.example.comradebuddy.presentation.notes

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

// PDf view Composable

@Composable
fun PdfDocViewer(
    fileName: String,
    pdfViewModel: PdfViewModel = viewModel()
) {
    pdfViewModel.PdfViewer(fileName)

}
