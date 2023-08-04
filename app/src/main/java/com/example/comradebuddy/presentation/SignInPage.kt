package com.example.comradebuddy.presentation

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.graphics.Color.parseColor
import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext


import com.example.comradebuddy.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current

) {
    // Restrict the login screen to landscape
//    var activity = context as Activity
//    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT


    Scaffold(
        bottomBar = {
            Image(
                painter = painterResource(id = R.drawable.bottom_background),
                alignment = Alignment.BottomCenter,
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )

        }
    )
    {_->

        Column(
            modifier = Modifier
                .fillMaxSize(1f)
                .background(color = Color(parseColor("#ffffff"))),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.top_background) ,
                contentDescription = "Top background",
                contentScale = ContentScale.FillBounds
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier
                        .height(250.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = stringResource(id = R.string.app_title ),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(parseColor("#7d32a8"))
                )

                Spacer(Modifier.height(10.dp))

                OutlinedCard(
                    modifier = Modifier
                        .clickable{}
                ){
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(10.dp)

                    ){
                        Image(
                            painter = painterResource(id = R.drawable.google),
                            contentDescription = null
                        )
                        Text(
                            text = stringResource(id = R.string.sign_up_message)
                        )

                    }
                }
            }

            // Spacer(Modifier.height(110.dp))

        }
    }

}

@Preview(showBackground = true)
@Composable
fun SignInScreenPrev() {
    SignInScreen()
}