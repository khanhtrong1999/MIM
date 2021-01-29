package com.example.mim

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Order_Buy_Fragment : Fragment(),`innn` {
    var mWordViewModel: WordViewModel? = null
    private var param1: String? = null
    private var param2: String? = null
    var adapter:adapterjjjjjjj? = null
    var txt:TextView? = null
    var tt:TextView? = null
    var list:List<Word>? = null
    val EXTRA_REPLY = "com.example.mim.REPLY"
    val NEW_WORD_ACTIVITY_REQUEST_CODE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("UseRequireInsteadOfGet", "FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_order__buy_, container, false)
        val recyclerView4 = view.findViewById<RecyclerView>(R.id.recyclerView7)
        val linear = view.findViewById<LinearLayout>(R.id.linearLayout)
        tt = view.findViewById<TextView>(R.id.kkk)
        val button = view.findViewById<Button>(R.id.bu)
        txt = view.findViewById<TextView>(R.id.allPrice)

        val sharedpreferences = context!!.getSharedPreferences(
            "APPMIMM",
            Context.MODE_PRIVATE
        )
        var id:Int = sharedpreferences.getInt("key", -1);

        adapter = context?.let { adapterjjjjjjj(arrayListOf(), it, this) }
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
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
            val intent = Intent(context, LocationActivity::class.java)
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE)
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
                jsonObject.put("address", tt!!.text.toString())
                jsonObject.put("user_id", id)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            val jsonParser = JsonParser()
            val gsonObject = jsonParser.parse(jsonObject.toString()) as JsonObject
            System.out.println("cha m" + gsonObject)
            order(gsonObject, ssss)
        }

        return view
    }

    private fun order(data: JsonObject, d: List<Word>) {
        val call = APIClient.service.postOrder(data)
        call!!.enqueue(object : Callback<Status?> {
            override fun onResponse(
                call: Call<Status?>?,
                response: Response<Status?>
            ) {
                if (response.isSuccessful) {
                    val resource: Status = response.body()!!

                    System.out.println("THUONGKK")
//                        progressDoalog!!.dismiss()
                    for (i in d) {
                        mWordViewModel!!.deleteWord(i)
                    }
                    Toast.makeText(context, "Order success", Toast.LENGTH_LONG)
                }
            }

            override fun onFailure(call: Call<Status?>, t: Throwable) {
                System.out.println(t.message)
            }
        })
    }

    companion object {
        @JvmStatic fun newInstance(param1: String, param2: String) =
                Order_Buy_Fragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
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
        System.out.println("KKKKK" + data.id)
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
            (context as AppCompatActivity).supportFragmentManager,
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            System.out.println("hihihihi"+data!!.getStringExtra(EXTRA_REPLY))
            tt!!.text = data!!.getStringExtra(EXTRA_REPLY)
        } else {

        }
    }
}
