package com.lennyai.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : Activity() {

    private lateinit var input: EditText
    private lateinit var chatContainer: LinearLayout
    private lateinit var chatScroll: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input = findViewById(R.id.messageInput)
        chatContainer = findViewById(R.id.chatContainer)
        chatScroll = findViewById(R.id.chatScroll)

        val sendButton: Button = findViewById(R.id.sendButton)

        sendButton.setOnClickListener {
            sendMessage()
        }
    }

    private fun sendMessage() {
        val message = input.text.toString().trim()

        if (message.isEmpty()) return

        addMessage(message, true)
        input.text.clear()

        val reply = when {
            message.lowercase().contains("hallo") ->
                "Hallo! Schön, dass du da bist. Ich bin LennyAI."

            message.lowercase().contains("wer bist du") ->
                "Ich bin LennyAI, dein eigener KI-Chatbot."

            else ->
                "Das ist momentan eine Testantwort. Die echte KI-Anbindung kommt als Nächstes."
        }

        addMessage(reply, false)
    }

    private fun addMessage(text: String, fromUser: Boolean) {
        val messageView = TextView(this)

        messageView.text = text
        messageView.textSize = 16f
        messageView.setTextColor(Color.WHITE)
        messageView.setPadding(24, 16, 24, 16)

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        params.setMargins(0, 8, 0, 8)

        if (fromUser) {
            messageView.setBackgroundColor(Color.rgb(45, 90, 150))
            params.gravity = Gravity.END
        } else {
            messageView.setBackgroundColor(Color.rgb(41, 41, 41))
            params.gravity = Gravity.START
        }

        messageView.layoutParams = params
        chatContainer.addView(messageView)

        chatScroll.post {
            chatScroll.fullScroll(ScrollView.FOCUS_DOWN)
        }
    }
}

