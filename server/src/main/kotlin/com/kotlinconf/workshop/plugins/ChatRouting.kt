package com.kotlinconf.workshop.plugins

import com.kotlinconf.workshop.ChatMessage
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

fun Application.configureChatRouting(chat: Chat = Chat()) {
    routing {
        webSocket("/chat") {
            handleSocket(this, chat)
        }
    }
}

// Task: Implement chat server using SharedFlow
suspend fun handleSocket(
    socket: WebSocketServerSession,
    chat: Chat
) {
    coroutineScope {
        launch {
            // use socket.receiveDeserialized<ChatMessage>() to receive a message from the WebSocket
            while (true) {
                val message = socket.receiveDeserialized<ChatMessage>()
                chat.broadcastMessage(message)
            }
        }
        launch {
            // use session.sendSerialized(message) to send a message to the WebSocket
            chat.messageFlow.collect {
                socket.sendSerialized(it)
            }
        }
    }
}

class Chat {
    private val _messageFlow: MutableSharedFlow<ChatMessage> = MutableSharedFlow(replay = 3)
    val messageFlow: SharedFlow<ChatMessage> get() = _messageFlow
    suspend fun broadcastMessage(message: ChatMessage) {
        _messageFlow.emit(message)
    }
}