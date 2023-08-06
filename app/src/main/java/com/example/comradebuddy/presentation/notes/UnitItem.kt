package com.example.comradebuddy.presentation.notes

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comradebuddy.R
import com.example.comradebuddy.ui.theme.ComradeBuddyTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitItem(
    itemName: String,
    onItemClicked: () -> Unit
) {

    Card(
        modifier = Modifier
            .clickable(onClick = onItemClicked)
            .fillMaxWidth()
    ) {

        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier =  Modifier
                .width(400.dp)
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop,
            )
            Text(
                text = itemName,
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                ),
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