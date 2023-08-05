package com.example.comradebuddy.presentation.notes

import android.util.Log
import android.view.View
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.ViewModel
import com.example.comradebuddy.R

import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

// We expect every document to start at first page (0)
const val DEFAULTPAGENO = 0

class PdfViewModel: ViewModel() {

    //Data class to hold the document page number
    data class DocPage(val pageNumber: Int = DEFAULTPAGENO)

    // State for the current page
    private var _uiState = MutableStateFlow(DocPage())

    @Composable
    fun PdfViewer(
        fileName: String,
    ) {

        AndroidView(
            factory = {
                View.inflate(it, R.layout.pdf_viewer, null)

            },
            update = {
                val pdfViewer = it.findViewById<PDFView>(R.id.pdfViewer)

                pdfViewer.fromAsset(fileName)
                    .defaultPage(_uiState.value.pageNumber)
                    .enableSwipe(true)
                    .scrollHandle(DefaultScrollHandle(it.context))
                    .onPageChange { page, _ ->
                        if (page >= 1){
                           _uiState.update { it.copy(pageNumber = page) }
                        }
                        Log.d("Here", "OnPageChange called")

                    }
                    .spacing(0)
                    .load()
            },
            modifier = Modifier.fillMaxSize()
        )


    }
}