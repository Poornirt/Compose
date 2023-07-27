package com.composetutorial

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composetutorial.ui.theme.ComposeTutorialTheme
import com.composetutorial.ui.theme.Shapes

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme(darkTheme = true) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Greeting("Android")
                }
            }
        }
    }


    @Composable
    fun Greeting(name: String) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.image),
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.background, CircleShape),
                contentDescription = "Profile picture"
            )
            Column {
                Surface(shape = Shapes.medium, elevation = 1.dp) {
                    Text(
                        text = "Name",
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.subtitle1
                    )
                }
                Surface(shape = Shapes.medium, elevation = 2.dp) {
                    Text(
                        text = "Age",
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.subtitle1
                    )
                }
            }
        }
    }

    @Preview(name = "Dark Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun DefaultPreview() {
        ComposeTutorialTheme(darkTheme = true) {
            Surface(modifier = Modifier.fillMaxSize()) {
                Greeting("Android")
            }
        }
    }
}