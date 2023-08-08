package com.example.comradebuddy.presentation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.comradebuddy.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopAppBar(
    onIconButtonClick: () -> Unit,
    homeScreen: Boolean = true
) {
    TopAppBar(
        title = { Text( stringResource(id = R.string.app_name) ) },
        navigationIcon = {
            if (homeScreen ) {
                IconButton(
                    onClick = onIconButtonClick
                ) {
                    Icon(Icons.Filled.Menu, contentDescription = null)
                }
            }
        }
    )
}

@Preview
@Composable
fun TopBarPrev() {
   // AppTopAppBar()
}