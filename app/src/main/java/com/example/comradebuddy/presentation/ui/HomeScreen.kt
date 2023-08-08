package com.example.comradebuddy.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Home

import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.comradebuddy.AppNavigation
import com.example.comradebuddy.R
import com.example.comradebuddy.data.Course
import com.example.comradebuddy.presentation.notes.CoursesList

import com.example.comradebuddy.presentation.sign_in.UserData

import kotlinx.coroutines.launch

/** Home Screen composable func that implements a scaffold that calls
 * the top-bar and the bottom-bar
 * It also has a column that hosts components of the home screen.
 * The Home sections containing the Course LazyVertical grid, pastPapers and user greatings
 * at the top part
 * **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    courseList: List<Course>,
    courseClicked: (String) -> Unit,
    userData: UserData?,
    navController: NavController,
    drawerState: DrawerState,

    ) {
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            AppTopAppBar(
                onIconButtonClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }
            )
        },
        bottomBar = {

            // BottomNavBar composable is called
            BottomNavBar(
                navController = navController ,
                // Define a list of BottomNavItems
                items = listOf(
                    BottomNavItem(
                        name ="Home",
                        route = AppNavigation.HOME.name,
                        icon = Icons.Default.Home
                    )
                ),

                // Navigate to the route of the item selected
                onItemClick = {
                    navController.popBackStack()
                    navController.navigate(it.route)
                }
            )
        }

    ) {padding->

        Column(
            modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){

                Text(
                    text =
                    if (userData != null)"Hello, ${userData.userName}!"  else "Hello User!",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .paddingFromBaseline(top = 40.dp, bottom = 8.dp)

                        .weight(1f)
                )

                if (userData?.profilePictureUrl != null) {
                    AsyncImage(
                        model = userData.profilePictureUrl,
                        contentDescription = "profile Picture",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                    )
                }
            }

            HomeSection(
                title = R.string.course_Slot ) {
                CoursesList(
                    courses = courseList,
                    courseClicked = courseClicked
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            HomeSection(title = R.string.past_papers_slot) {

            }
        }

    }

}