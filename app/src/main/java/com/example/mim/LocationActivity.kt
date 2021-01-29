package com.example.mim

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LocationActivity : AppCompatActivity() {
    val EXTRA_REPLY = "com.example.mim.REPLY"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        var text = findViewById<EditText>(R.id.editText7)
        var bun = findViewById<Button>(R.id.button)
        bun.setOnClickListener {
            val replyIntent = Intent()
            replyIntent.putExtra(EXTRA_REPLY, text.text.toString())
            setResult(RESULT_OK, replyIntent)
            finish()
        }

    }
}