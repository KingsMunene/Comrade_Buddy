package com.example.comradebuddy.presentation.notes

import androidx.lifecycle.ViewModel
import com.example.comradebuddy.data.CourseUnit
import com.example.comradebuddy.data.DataManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NoteBookViewModel : ViewModel(){
    // uiState mutable state
    private var uiState = MutableStateFlow(DataManager)

    // Backing property
    val _uiState = uiState.asStateFlow()


    // Units ArrayList that will be loaded by calling the get units function
    var units = ArrayList<CourseUnit>()

    // Unit clicked file name

    var fileName: String = ""


    /** Function to get units for a specific course
     * It receives a course name and uses it to get a list of units associated to that
     * course
     * **/
    fun getUnits(course: String): ArrayList<CourseUnit> {
        var units: List<CourseUnit> = ArrayList()

        DataManager.courses.forEach {

            if (it.courseName == course){
                units = it.units
            }
        }
        return units as ArrayList<CourseUnit>

    }

}