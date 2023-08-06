package com.example.comradebuddy.presentation.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.comradebuddy.data.CourseUnit
import com.example.comradebuddy.data.DataManager

@Composable
fun UnitsList(
    units: List<CourseUnit>,
    onUnitClicked: (String) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        modifier = Modifier.padding(8.dp)
    )
    {
        items(
            units
        ){unit ->
            UnitItem(
                itemName = unit.unitName,
                onItemClicked = {onUnitClicked(unit.fileName)}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Unit() {

//    var courses = getUnits(DataManager.courses)
    UnitsList(DataManager.courses[0].units, onUnitClicked = {})
}