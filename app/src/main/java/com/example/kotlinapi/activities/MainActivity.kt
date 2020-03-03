package com.example.kotlinapi.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlinapi.R
import com.example.kotlinapi.api.RetrofitClient
import com.example.kotlinapi.model.DefaultRespoonse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        textViewLogin.setOnClickListener {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }

        buttonSignUp.setOnClickListener{

            val userName = editTextUsername.text.toString().trim()
            val phone = editTextPhone.text.toString().trim()
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            val address = editTextAddress.text.toString().trim()



            if(userName.isEmpty()){
                editTextUsername.error = "Name required"
                editTextUsername.requestFocus()
                return@setOnClickListener
            }

            if(phone.isEmpty())
            {
                editTextPhone.error = "Phone required"
                editTextPhone.requestFocus()
                return@setOnClickListener
            }

            if(email.isEmpty())
            {
                editTextEmail.error = "Email required"
                editTextEmail.requestFocus()
                return@setOnClickListener
            }


            if(password.isEmpty()){
                editTextPassword.error = "Password required"
                editTextPassword.requestFocus()
                return@setOnClickListener
            }



            if(address.isEmpty()){
                editTextAddress.error = "Address required"
                editTextAddress.requestFocus()
                return@setOnClickListener
            }


            RetrofitClient.instance.createUser(userName ,phone , email ,password ,address )
                .enqueue(object: Callback<DefaultRespoonse>{
                    override fun onFailure(call: Call<DefaultRespoonse>, t: Throwable)
                    {

                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()

                    }

                    override fun onResponse(call: Call<DefaultRespoonse>, response: Response<DefaultRespoonse>)
                    {
                        Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()

                    }


                })


        }
    }
}
