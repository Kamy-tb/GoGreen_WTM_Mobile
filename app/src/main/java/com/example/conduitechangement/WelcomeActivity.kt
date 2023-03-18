package com.example.conduitechangement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.conduitechangement.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    lateinit var bindingWelcome: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        bindingWelcome = ActivityWelcomeBinding.inflate(layoutInflater)
        val view = bindingWelcome.root
        setContentView(view)

        bindingWelcome.signinwlc.setOnClickListener(){
            val intent = Intent(this,LoginActivity::class.java)
            this.startActivity(intent)
        }

        bindingWelcome.signup.setOnClickListener(){
            val intent = Intent(this,SignupActivity::class.java)
            this.startActivity(intent)
        }
    }


}