package com.example.comradebuddy.presentation.ui


import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer

import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

import com.example.comradebuddy.data.Course
import com.example.comradebuddy.presentation.sign_in.UserData


/** As per material 3 the ModalNavigation is not part of the scaffold
 * Here we have a modal drawer that calls the homeScreen composable on its content section*/
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    courseList: List<Course>,
    courseClicked: (String) -> Unit,
    userData: UserData?,
    onSignOut: () -> Unit,
    navController: NavController
) {


    // Maintain the state of the drawer
    val drawerState = rememberDrawerState( DrawerValue.Closed)

    // Modal drawer function
    ModalNavigationDrawer(
        drawerState = drawerState,
        // Drawer content that calls the drawer Header and body composable defined at the bottom of this file
        drawerContent = {
            ModalDrawerSheet(

                // Define the size of the drawer
                modifier = Modifier
                    .requiredWidth(250.dp)
            ){
                Spacer(Modifier.height(12.dp))
                // Consumes user profile picture and user name
                NavigationDrawerHeader(
                    userData?.profilePictureUrl,
                    userData?.userName
                )

                // Defines a list of items to be displayed in the drawer
                DrawerBody(
                    items = listOf(
                        NavDrawerItem(
                            id ="About",
                            title ="About",
                            icon = Icons.Default.Info
                        ),
                        NavDrawerItem(
                            id ="LogOut",
                            title ="Log Out",
                            icon = Icons.Default.ExitToApp
                        )
                    ),
                    // We pass the id of the item selected and perform an action
                    onItemClick = {
                        when (it.id) {
                            "LogOut" -> {
                                onSignOut()
                            }
                        }
                    }
                )

            }
        },
        // Call the HomeScreen to pass the content
        content = {
            HomeScreen(
                courseList = courseList,
                courseClicked = courseClicked,
                userData = userData ,
                onSignOut = onSignOut,
                navController = navController,
                drawerState = drawerState,
            )

        }
)
}




// Composable to display the user profile picture and user name
@Composable
fun NavigationDrawerHeader(
    profilePictureUrl: String?,
    userName: String?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 30.dp),
        contentAlignment = Alignment.Center
    ) {
       Column {
           AsyncImage(
               model = profilePictureUrl,
               contentDescription = "User  Profile picture",
               modifier = Modifier
                   .clip(CircleShape)
                   .size(100.dp)
           )
           Spacer(modifier = Modifier.height(6.dp))

           Text(
               text = userName?: "User",
               textAlign = TextAlign.Center,
               fontSize = 24.sp
           )
       }

    }
}


// Receives a list of items defined in the drawer and arranges them using a lazyColumn
@Composable
fun DrawerBody(
    items: List<NavDrawerItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    // Receives an item and through that we can get the Id
    onItemClick: (NavDrawerItem) -> Unit
) {
    LazyColumn(modifier){
        items(items){ item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    // Pass the clicked item to the lambda function
                    .clickable {
                        onItemClick(item)
                    }
                    .padding(16.dp)
            ){
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.title
                )
                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = item.title,
                    style = itemTextStyle,
                    modifier = Modifier
                )

            }
        }
    }
    
}

@Preview(showBackground = true)
@Composable
fun DrawerPrev() {
  //  Drawer()
}