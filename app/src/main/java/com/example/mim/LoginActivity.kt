package com.example.mim

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val login = findViewById<Button>(R.id.btn_login)
        val editText5 = findViewById<EditText>(R.id.editText5)
        val editText6 = findViewById<EditText>(R.id.editText6)
        val register = findViewById<TextView>(R.id.textView27)
        login.setOnClickListener {

            sendData(editText5.text.toString(), editText6.text.toString())


//            val intent = Intent(baseContext, MainActivity::class.java)
//            startActivity(intent)
//            finish()
        }
        register.setOnClickListener {
            val intent = Intent(baseContext, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
    fun sendData(email: String, password: String){
        val call = APIClient.service.getLogin(email, password)
        call.enqueue(object : Callback<Login?> {
            override fun onResponse(
                call: Call<Login?>?,
                response: Response<Login?>
            ) {
                if (response.isSuccessful) {
                    val resource: Login = response.body()!!
                    val datumList: Login.datas = resource.data
                    System.out.println(datumList)
                    if (datumList.status == 1 && resource.status) {

                        val sharedpreferences = getSharedPreferences(
                            "APPMIMM",
                            Context.MODE_PRIVATE
                        )
                        val editor: SharedPreferences.Editor = sharedpreferences.edit()
                        editor.putInt("key", datumList.id)
                        editor.putString("mail", datumList.email)
                        editor.commit()

                        val intent = Intent(baseContext, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }

            override fun onFailure(call: Call<Login?>, t: Throwable) {
                System.out.println(t.message)
            }
        })
    }
}