package com.example.mim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mim.rooom.Word
import com.example.mim.rooom.WordViewModel
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderActivity : AppCompatActivity(),innn {
    var mWordViewModel: WordViewModel? = null
    private var param1: String? = null
    private var param2: String? = null
    var adapter:adapterjjjjjjj? = null
    var txt:TextView? = null
    var list:List<Word>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val recyclerView4 = findViewById<RecyclerView>(R.id.recyclerView7)
        val linear = findViewById<LinearLayout>(R.id.linearLayout)
        val button = findViewById<Button>(R.id.bu)
        txt = findViewById<TextView>(R.id.allPrice)

        adapter = this?.let { adapterjjjjjjj(arrayListOf(), it, this) }
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView4!!.layoutManager = layoutManager
        recyclerView4!!.itemAnimator = DefaultItemAnimator()
        recyclerView4!!.adapter = adapter


        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        mWordViewModel!!.allWords.observe(this, object : Observer<List<Word?>?> {
            override fun onChanged(@Nullable words: List<Word?>?) {
                retrieveList(words as List<Word>)
            }
        })

        linear.setOnClickListener {
            val intent = Intent(this, LocationActivity::class.java)
            intent.putExtra("fullname", "")
            intent.putExtra("address", "")
            intent.putExtra("phone", "")
            startActivity(intent)
        }

//        ss(8)

        button.setOnClickListener {
            val ssss:List<Word> = list!!
            val jsonObject = JSONObject()
            try {
                val subJsonObject = JSONObject()
                val arr = JSONArray()
                for( i in ssss){
                    subJsonObject.put("qty", i.soluong)
                    subJsonObject.put("price", i.price)
                    subJsonObject.put("total", i.price)
                    subJsonObject.put("size", i.size)
                    subJsonObject.put("color", i.color)
                    subJsonObject.put("product_id", i.id)
                    arr.put(subJsonObject)
                }
                jsonObject.put("products", arr)
                jsonObject.put("address", "password")
                jsonObject.put("user_id", 8)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            val jsonParser = JsonParser()
            val gsonObject = jsonParser.parse(jsonObject.toString()) as JsonObject
            System.out.println("cha m"+gsonObject)
            order(gsonObject, ssss)
        }

    }

    private fun order(data: JsonObject, d:List<Word>) {
        val call = APIClient.service.postOrder(data)
        call!!.enqueue(object : Callback<Status?> {
            override fun onResponse(
                call: Call<Status?>?,
                response: Response<Status?>
            ) {
                if(response.isSuccessful){
                    val resource: Status = response.body()!!

                    System.out.println("THUONGKK")
//                        progressDoalog!!.dismiss()
                    for(i in d){
                        mWordViewModel!!.deleteWord(i)
                    }
                    Toast.makeText(this@OrderActivity,"Order success", Toast.LENGTH_LONG)
                }
            }

            override fun onFailure(call: Call<Status?>, t: Throwable) {
                System.out.println(t.message)
            }
        })
    }

    fun ss(id: Int){
        val call = APIClient.service.getOrder(id)
        call.enqueue(object : Callback<Order?> {
            override fun onResponse(
                call: Call<Order?>?,
                response: Response<Order?>
            ) {
                if (response.isSuccessful) {
                    val resource: Order = response.body()!!
                    val list: List<Order.obj2> = resource.data
                    System.out.print("babg" + list)
//                    retrieveList(list)
                }
            }

            override fun onFailure(call: Call<Order?>, t: Throwable) {
                System.out.println(t.message)
            }
        })
    }
//    private fun retrieveList(data: List<Order.obj2>) {
//        adapter.apply {
//            this!!.h(data)
//            notifyDataSetChanged()
//        }
//    }

    private fun retrieveList(data: List<Word>) {
        list = data

        var sum = 0;
        for (i in data){
            sum = sum + (i.price * i.soluong)
        }
        txt!!.text = sum.toString()
        adapter.apply {
            this!!.h(data)
            notifyDataSetChanged()
        }
    }
    override fun onClick(data: Word) {
        System.out.println("KKKKK"+data.id)
        val bottomSheetFragment = SheetBottomFragment()
        val bundle = Bundle()
        bundle.putInt("key", data.id)
        var sss = ArrayList<String>()
        sss.add(data.id.toString())
        sss.add(data.name)
        sss.add(data.price.toString())
        sss.add(data.soluong.toString())
        sss.add(data.color)
        sss.add(data.size)
        sss.add(data.image)
        bundle.putStringArrayList("key_obj", sss)
        bottomSheetFragment.arguments = bundle
        bottomSheetFragment.show(
            (this as AppCompatActivity).supportFragmentManager,
            "bottomSheetFragment"
        )
//        findNavController().navigate(R.id.action_order_Buy_Fragment_to_sheetBottomFragment)
    }

    override fun onGiam(data: Word) {
        var ss = data.soluong - 1;
        if(ss == 0){
            mWordViewModel!!.deleteWord(data)
        }
        if(ss>=1){
            data.soluong = ss
            mWordViewModel!!.update(data)
        }
    }

    override fun onTang(data: Word) {
        var ss = data.soluong + 1;
        if(ss <= 10) {
            data.soluong = ss
            mWordViewModel!!.update(data)
        }
    }
}