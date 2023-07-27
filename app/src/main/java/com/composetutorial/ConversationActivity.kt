package com.composetutorial

import SampleData
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composetutorial.data.Message
import com.composetutorial.ui.theme.ComposeTutorialTheme
import com.composetutorial.ui.theme.Shapes

class ConversationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme(darkTheme = false) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Conversation(SampleData.conversationSample)
                }
            }
        }
    }


    @Composable
    fun Conversation(messages: List<Message>) {
        LazyColumn {
            items(messages) { item: Message ->
                MessageCard(item)
            }
        }
    }

    @Composable
    fun MessageCard(message: Message) {
        Row(modifier = Modifier.padding(12.dp)) {
            Image(
                painter = painterResource(id = R.drawable.image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.background, CircleShape),
                contentDescription = "Profile picture"
            )
            Spacer(modifier = Modifier.size(8.dp))
            var isExpanded by remember { mutableStateOf(false) }
            val surfaceColor by animateColorAsState(if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface)
            Column {
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = message.name, color = MaterialTheme.colors.primary)
                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    shape = Shapes.medium,
                    elevation = 1.dp,
                    modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                    Text(
                        text = message.text,
                        modifier = Modifier
                            .animateContentSize()
                            .padding(1.dp),
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.body1,
                        color = surfaceColor
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
    fun ConversationPreview() {
        ComposeTutorialTheme(darkTheme = false) {
            Surface(modifier = Modifier.fillMaxSize()) {
                Conversation(messages = SampleData.conversationSample)
            }
        }
    }
}