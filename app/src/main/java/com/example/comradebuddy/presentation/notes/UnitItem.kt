package com.example.comradebuddy.presentation.notes

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.comradebuddy.R
import com.example.comradebuddy.ui.theme.ComradeBuddyTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitItem(
    itemName: String,
    onItemClicked: () -> Unit
) {

    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .clickable(onClick = onItemClicked)
    ) {

        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ){

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(56.dp)
            )
            Text(
                text = itemName,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OptionsPrev() {
       ComradeBuddyTheme {

           UnitItem("Information Technology", onItemClicked = {})
       }
}