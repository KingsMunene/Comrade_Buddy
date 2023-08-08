package com.example.comradebuddy.presentation.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.comradebuddy.AppNavigation
import com.example.comradebuddy.data.CourseUnit
import com.example.comradebuddy.data.DataManager
import com.example.comradebuddy.presentation.ui.AppTopAppBar
import com.example.comradebuddy.presentation.ui.BottomNavBar
import com.example.comradebuddy.presentation.ui.BottomNavItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitsList(
    units: List<CourseUnit>,
    onUnitClicked: (String, String) -> Unit,
    navController: NavController,
) {
    val scope = rememberCoroutineScope()
    // Maintain the state of the drawer
    Scaffold(
        topBar = {
            AppTopAppBar(
                onIconButtonClick = {
                },
                false
            )
        },
        bottomBar = {
            BottomNavBar(
                navController = navController ,
                items = listOf(
                    BottomNavItem(
                        name ="Home",
                        route = AppNavigation.HOME.name,
                        icon = Icons.Default.Home
                    )
                ),
                onItemClick = {
                    navController.popBackStack()
                    navController.navigate(it.route)
                }
            )
        }

    ) { padding->

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            modifier = Modifier.padding(padding)
        )
        {
            items(
                units
            ) { unit ->
                UnitItem(
                    itemName = unit.unitName,
                    onItemClicked = { onUnitClicked(unit.fileName, unit.unitName) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Unit() {

//    var courses = getUnits(DataManager.courses)
    //UnitsList(DataManager.courses[0].units, onUnitClicked = {})
}