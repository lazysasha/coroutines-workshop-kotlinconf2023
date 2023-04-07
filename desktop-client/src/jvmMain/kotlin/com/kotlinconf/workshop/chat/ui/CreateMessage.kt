package com.kotlinconf.workshop.chat.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kotlinconf.workshop.ChatMessage

@Composable
internal fun CreateMessage(
    onMessageSent: (ChatMessage) -> Unit
) {
    var message by remember { mutableStateOf("") }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 10.dp)
            .padding(bottom = 10.dp),
        value = message,
        onValueChange = { message = it },
        label = { Text("Message") },
        trailingIcon = {
            if (message.isNotBlank()) {
                IconButton(
                    onClick = {
                        onMessageSent(ChatMessage(message))
                        message = ""
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = "Send",
                        tint = MaterialTheme.colors.primary,
                    )
                }
            }
        }
    )
}