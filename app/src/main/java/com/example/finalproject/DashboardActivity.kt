package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DashboardActivity : AppCompatActivity() {
    private lateinit var btn_about_me: Button
    private lateinit var btn_photos: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btn_about_me = findViewById(R.id.btn_about_me)
        btn_photos = findViewById(R.id.btn_photos)

        btn_about_me.setOnClickListener {
            startActivity(Intent(this, about_me::class.java))
        }
        btn_photos.setOnClickListener {
            startActivity(Intent(this, photos::class.java))
        }
    }
}