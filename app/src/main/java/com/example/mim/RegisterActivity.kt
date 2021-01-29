package com.example.mim

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class  RegisterActivity : AppCompatActivity() {
    var first_name:EditText?= null
    var last_name:EditText?= null
    var email:EditText?= null
    var password:EditText?= null
    var comfi_pass:EditText?= null
    var register:Button?= null
    var progressDoalog:ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val back = findViewById<ImageView>(R.id.back)
        init()
        back.setOnClickListener {
            finish()
        }
//        progressDoalog = ProgressDialog(this)
//        progressDoalog!!.max = 100
//        progressDoalog!!.setMessage("Its loading....")
//        progressDoalog!!.setTitle("ProgressDialog bar example")
//        progressDoalog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        register!!.setOnClickListener {

            // show it
//            progressDoalog!!.show()
            ss(first_name!!.text.toString(),last_name!!.text.toString(),email!!.text.toString(),password!!.text.toString())
            val intent = Intent(baseContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    fun init(){
        first_name = findViewById<EditText>(R.id.editText10)
        last_name = findViewById<EditText>(R.id.editText7)
        email = findViewById<EditText>(R.id.editText8)
        password = findViewById<EditText>(R.id.editText5)
        comfi_pass = findViewById<EditText>(R.id.editText9)
        register = findViewById<Button>(R.id.button)
    }
    fun ss(first_name: String,
           last_name: String,
           email: String,
           password: String){
        val call:Call<Register> = APIClient.service.getRegister(
            first_name, last_name, email, password)
        call.enqueue(object : Callback<Register?> {
            override fun onResponse(
                call: Call<Register?>?,
                response: Response<Register?>
            ) {
                if(response.isSuccessful){
                    val resource: Register = response.body()!!
                    val datumList: Register.datas = resource.data
//                        progressDoalog!!.dismiss()
                }
            }
            override fun onFailure(call: Call<Register?>, t: Throwable) {
                System.out.println(t.message)
            }
        })

    }
}