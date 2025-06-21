package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    private lateinit var emailTextView: EditText
    private lateinit var passwordTextView: EditText
    private lateinit var button: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var btn_go_back: Button
    private lateinit var btn_forgot_password: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // creating instance of FirebaseAuth
        auth = FirebaseAuth.getInstance()

        emailTextView = findViewById(R.id.email_edittext)
        passwordTextView = findViewById(R.id.password_edittext)
        button = findViewById(R.id.login_button)

        button.setOnClickListener {
            loginUserAccount()
        }
        btn_go_back = findViewById(R.id.btn_go_back)
        btn_go_back.setOnClickListener {
            finish()
        }
        btn_forgot_password = findViewById(R.id.btn_forgot_password)
        btn_forgot_password.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Forgot Password")
            val view = layoutInflater.inflate(R.layout.dialog_forgot_password, null)
            val username = view.findViewById<EditText>(R.id.et_username)
            builder.setView(view)
            builder.setPositiveButton("Reset", {_, _->
                forgotPassword(username)
            })
            builder.setNegativeButton("Close", {_, _-> })
            builder.show()
        }

    }
    private fun forgotPassword(userName: EditText) {
        if (userName.text.toString().isEmpty()) {
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(userName.text.toString()).matches()) {
            return
        }
        auth.sendPasswordResetEmail(userName.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Email sent.", Toast.LENGTH_LONG).show()
                }
            }
    }
    private fun loginUserAccount() {
        val email = emailTextView.text.toString()
        val password = passwordTextView.text.toString()

        // Validations for input email and password
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter credentials", Toast.LENGTH_LONG).show()
        } else {
            // login existing user
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // login successful
                    Toast.makeText(this, "Login successful!", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, DashboardActivity::class.java))
                } else {
                    // login failed
                    Toast.makeText(this, "Login failed!", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}
