package com.example.conduitechangement

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.conduitechangement.databinding.ActivityLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class LoginActivity : AppCompatActivity() {
    private lateinit var bindinglogin : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindinglogin = ActivityLoginBinding.inflate(layoutInflater)
        val view = bindinglogin.root
        setContentView(view)


        /*
        var isPasswordVisible = false
        bindinglogin.eye.setOnClickListener(){
            if (isPasswordVisible) {
                bindinglogin.pwd.transformationMethod = PasswordTransformationMethod.getInstance()
                bindinglogin.eye.setImageResource(R.drawable.eye_slash)
            } else {
                bindinglogin.pwd.transformationMethod = null
                bindinglogin.eye.setImageResource(R.drawable.eye)
            }
            isPasswordVisible = !isPasswordVisible
            bindinglogin.pwd.setSelection(bindinglogin.pwd.text.length)
        }
         */

        bindinglogin.textView7.setOnClickListener(){
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
        bindinglogin.signin.setOnClickListener {
            val nom = bindinglogin.name.text.toString()
            val password = bindinglogin.pwd.text.toString()
            if (nom.length == 0) {
                bindinglogin.name.error = "Enter your name"
            }
            else if (password.length == 0) {
                bindinglogin.pwd.error = "Enter your password"
            }
            else {
                try {
                    login(nom, password)
                }
                catch (_: InterruptedException) {
                } catch (_: IOException) {
                } catch (_: NullPointerException) {
                } catch (_: IllegalStateException) {
                } catch (_: Exception) { }
            }
        }
    }


    private fun login(nom: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitService.endpoint.verifyUser(nom, password)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val user = response.body() // mettre adapter a ce niveau car c adapter qui contient les donnees
                    if (user != null) {
                        val pref = getSharedPreferences("fileConnexion", MODE_PRIVATE)
                        pref.edit {
                            putBoolean("connected", true)
                            putInt("user_id" , user.user_id!!)
                            putString("nom" , user.nom)
                            putInt("pnt" , user.pnts)
                        }
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "wrond name and/or password",
                            Toast.LENGTH_SHORT
                        ).show();
                    }

                }
                else {
                    throw Exception("Unexpected code $response")
                }
            }

        }
    }


}