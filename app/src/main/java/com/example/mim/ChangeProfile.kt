package com.example.mim

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangeProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_profile)

        var first = findViewById<EditText>(R.id.editText10)
        var last = findViewById<EditText>(R.id.editText12)
        var Male = findViewById<RadioButton>(R.id.radioButton)
        var Female = findViewById<RadioButton>(R.id.radioButton2)
        var phone = findViewById<EditText>(R.id.editTextPhone)
        var birthday = findViewById<EditText>(R.id.editTextDate)
        var address = findViewById<EditText>(R.id.editText7)
//       id
        var ss = findViewById<Button>(R.id.button)
        ss.setOnClickListener {
            System.out.println(first.text.toString())
            var gender = -1;
            if(Male.isChecked){
                gender = 0
            }
            if(Female.isChecked){
                gender = 1
            }
            sendData(first.text.toString(),last.text.toString(),gender ,phone.text.toString(),birthday.text.toString(),address.text.toString(),8)
        }

    }
    fun sendData(first: String, last: String,gender:Int,phone:String,birthday:String,address:String,id:Int){
        val call = APIClient.service.getUpdateProfile(first, last,"",0,phone,birthday,address,id)
        call.enqueue(object : Callback<Profile?> {
            override fun onResponse(
                call: Call<Profile?>?,
                response: Response<Profile?>
            ) {
                if (response.isSuccessful) {
                    val resource: Profile = response.body()!!
                    Toast.makeText(baseContext,resource.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Profile?>, t: Throwable) {
                System.out.println(t.message)
            }
        })
    }
}