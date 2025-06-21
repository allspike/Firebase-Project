package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val register: Button = findViewById(R.id.register)
        val login: Button = findViewById(R.id.login)

        register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}