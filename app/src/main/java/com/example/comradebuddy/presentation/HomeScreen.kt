package com.example.comradebuddy.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.comradebuddy.data.Course
import com.example.comradebuddy.presentation.notes.CoursesList
import com.example.comradebuddy.presentation.sign_in.UserData

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    courseList: List<Course>,
    courseClicked: (String) -> Unit,
    userData: UserData?,
    onSignOut: () -> Unit

) {
    Column(
    modifier
    ) {
        Text(
            text =
            if (userData != null)"Hello ${userData.userName}!"  else "Hello User!",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onSignOut) {
            Text("Sign Out")
        }

        Text(
            text = "Courses",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )

        CoursesList(
            courses = courseList,
            courseClicked = courseClicked
        )
    }
}