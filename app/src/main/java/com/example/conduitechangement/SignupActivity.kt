package com.example.conduitechangement

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.conduitechangement.databinding.ActivityLoginBinding
import com.example.conduitechangement.databinding.ActivitySignupBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class SignupActivity : AppCompatActivity() {
    private lateinit var bindingRegister: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingRegister = ActivitySignupBinding.inflate(layoutInflater)
        val view = bindingRegister.root
        setContentView(view)

        bindingRegister.textView7.setOnClickListener(){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        bindingRegister.signup1.setOnClickListener(){
            val nom = bindingRegister.name2.text.toString()
            val mail = bindingRegister.mail.text.toString()
            val pwd = bindingRegister.pwd2.text.toString()
            val confirm = bindingRegister.pwd.text.toString()
            if (nom.length == 0) {
                bindingRegister.name2.error = "Enter your name"
            }
            if (mail.length == 0) {
                bindingRegister.mail.error = "Enter your e-mail"
            }
            if (pwd.length == 0) {
                bindingRegister.pwd2.error = "Enter your pasword"
            }
            if (confirm.length == 0) {
                bindingRegister.pwd.error = "Enter your pasword"
            }
            if (confirm != pwd) {
                Toast.makeText(this, "password not matching", Toast.LENGTH_SHORT).show();
            }
            else {
                val new = User ( null ,nom ,mail, pwd , 0)
                AddUser(new)
            }
        }
    }

    private fun AddUser(user : User) {
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val response = RetrofitService.endpoint.adduser(user)
                withContext(Dispatchers.Main){
                    if(response.isSuccessful){
                        Toast.makeText(this@SignupActivity , "Added" + response.code(), Toast.LENGTH_SHORT).show();

                        val intent = Intent(this@SignupActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else{
                        Toast.makeText(this@SignupActivity , "error " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
            catch (_: InterruptedException) {
            } catch (_: IOException) {
            } catch (_: NullPointerException) {
            } catch (_: IllegalStateException) {
            }
        }
    }

}