package com.example.comradebuddy.data

// Course class
data class Course(val courseName: String, val units: List<CourseUnit>)

//
//// Course years
//data class CourseYear(val yearName: String, val units: List<CourseUnit>)

// Course Unit class
data class CourseUnit(val unitName: String, val fileName: String)
