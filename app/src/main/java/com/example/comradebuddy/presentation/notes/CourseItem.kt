package com.example.comradebuddy.presentation.notes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    OutlinedCard (
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .clickable(
                onClick = onCourseClicked
            )
            .padding(16.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.surfaceVariant
//        )

    ){

        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center

        ) {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.medium)
                        .aspectRatio(16 / 12f)
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = courseName,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .padding(4.dp),
                maxLines = 2
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun courseItem() {
    CourseItem(courseName = "Something") {
        
    }
}