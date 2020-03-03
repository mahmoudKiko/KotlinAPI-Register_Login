package com.example.kotlinapi.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlinapi.R
import com.example.kotlinapi.api.RetrofitClient
import com.example.kotlinapi.model.LoginResponse
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        buttonLogin.setOnClickListener {

            val phone = editTextPhone.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (phone.isEmpty()) {
                editTextPhone.error = "Email required"
                editTextPhone.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                editTextPassword.error = "Password required"
                editTextPassword.requestFocus()
                return@setOnClickListener
            }


            RetrofitClient.instance.userLogin(phone, password)
                .enqueue(object: Callback<LoginResponse> {
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        if(!response.body()?.error!!){


                            val intent = Intent(applicationContext, ProfileActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                            startActivity(intent)


                        }else{
                            Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
                        }

                    }
                })

        }


    }
}
