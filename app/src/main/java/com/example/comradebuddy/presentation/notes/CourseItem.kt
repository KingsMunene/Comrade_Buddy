package com.example.comradebuddy.presentation.notes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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

@Composable
fun CourseItem(
    courseName: String,
    onCourseClicked: () -> Unit
) {

    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .clickable(onClick = onCourseClicked)
            .background(color = MaterialTheme.colorScheme.background)
    ) {

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
        ){

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = courseName,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding()
            )
        }
    }
}

@Preview()
@Composable
fun courseItem() {
    CourseItem(courseName = "Something") {
        
    }
}