package com.example.comradebuddy.presentation.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.comradebuddy.data.Course
import com.example.comradebuddy.data.DataManager


@Composable
fun CoursesList(
    courses: List<Course>,
    courseClicked: (String) -> Unit
    ) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    )
    {
        items(
            courses,
            key = { it.courseName }

            ){course ->
            CourseItem(
                courseName = course.courseName,
                onCourseClicked = {courseClicked(course.courseName)}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoursesListPrev() {

//    var courses = getUnits(DataManager.courses)
    CoursesList(DataManager.courses, courseClicked = {})
}

// get units as per course
