package com.example.mim

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.mim.rooom.Word
import com.example.mim.rooom.WordViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContentActivity : AppCompatActivity(),`in`,inn {
    var textView10:TextView?= null
    var textView11:TextView?= null
    var textView24:TextView?= null
    var adapterjjj:adapterdd? = null
    var a:adapterjjjjjj? = null
    var aa:adapterll? = null
    var mWordViewModel: WordViewModel? = null
    var txtName:String? = null
    var txtPrice:Int? = 0
    var txtId:Int? = 0
    var txtColor:String? = null
    var txtSize:String? = null
    var txtImage:String? = null
    var image:List<String>? = null
    var viewpage:ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        val id = intent.getIntExtra("id", 492)
        init()
        ss(id)

        val textView16 = findViewById<TextView>(R.id.textView16)
        textView16.setOnClickListener {
            val intent:Intent = Intent(this, ReviewActivity::class.java)
            startActivity(intent)
        }


        val recyclerView4 = findViewById<RecyclerView>(R.id.recyclerView4)
        aa = adapterll(arrayListOf(), this)
        val kk = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView4!!.layoutManager = kk
        recyclerView4!!.itemAnimator = DefaultItemAnimator()
        recyclerView4!!.adapter = aa

        val s = findViewById<RecyclerView>(R.id.recyclerView5)
        a = adapterjjjjjj(arrayListOf(), this)
        val k = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        s!!.layoutManager = k
        s!!.itemAnimator = DefaultItemAnimator()
        s!!.adapter = a

        val ss = findViewById<RecyclerView>(R.id.recyclerView8)
        adapterjjj = this?.let { adapterdd(arrayListOf(), this, it) }
        val layoutManagerr: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        ss!!.layoutManager = layoutManagerr
        layoutManagerr.setAutoMeasureEnabled(true)
        ss!!.setNestedScrollingEnabled(false)
        ss!!.setHasFixedSize(true)
        ss!!.adapter = adapterjjj


        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)

        val bu = findViewById<Button>(R.id.bu)
        bu.setOnClickListener {
            val word = txtPrice?.let { it1 -> txtId?.let { it2 -> Word(it2,txtName,txtImage, it1,1,txtSize,txtColor) } }
            System.out.println("baba "+txtImage + txtName+txtPrice+txtId+txtSize+txtColor)
            mWordViewModel!!.insert(word)
        }

        val buy = findViewById<Button>(R.id.buy)
        buy.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
        }
        viewpage = findViewById<ViewPager>(R.id.viewPager)
//        System.out.println("KKK"+txtImage!!)
//        val pagerAdapter = adapterTabView(supportFragmentManager, txtImage!!)
//        viewpage.adapter = pagerAdapter
    }
    fun init(){
        textView10 = findViewById<TextView>(R.id.textView10)
        textView11 = findViewById<TextView>(R.id.textView11)
        textView24 = findViewById<TextView>(R.id.textView24)
    }
    fun ss(id: Int){
        val call = APIClient.service.getProduction(id)
        call.enqueue(object : Callback<Production?> {
            override fun onResponse(
                    call: Call<Production?>?,
                    response: Response<Production?>
            ) {
                if (response.isSuccessful) {
                    val resource: Production = response.body()!!
                    val datumList: Production.DataK = resource.data
                    val list: List<Production.DataK> = resource.data2
                    val listColor: List<Production.DataK.ColorAndSize> = resource.data.colors
                    val listSize: List<Production.DataK.ColorAndSize> = resource.data.sizes
                    show(datumList)
                    retrieveList(list)
                    listSize(listSize)
                    listColor(listColor)
                }
            }

            override fun onFailure(call: Call<Production?>, t: Throwable) {
                System.out.println(t.message)
            }
        })
    }
    fun show(datumList: Production.DataK){
        txtName = datumList.name
        txtPrice = datumList.price
        txtId = datumList.id
        txtImage = datumList.image[0]
        image = datumList.image
        System.out.println("KKK"+image!!)
        val pagerAdapter = adapterTabView(supportFragmentManager, image!!)
        viewpage!!.adapter = pagerAdapter
//        System.out.println("baba "+txtImage + txtName+txtPrice+txtId)
        textView10!!.text = txtName
        textView11!!.text = txtPrice.toString()
        textView24!!.text = datumList.description
    }

    override fun onClick(id: Int) {
//        finish()
        val intent = Intent(this, ContentActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
    private fun retrieveList(users: List<Production.DataK>) {
        adapterjjj.apply {
            this!!.addDatas(users)
            notifyDataSetChanged()
        }
    }
    private fun listSize(size: List<Production.DataK.ColorAndSize>) {
        a.apply {
            this!!.addDatas(size)
            notifyDataSetChanged()
        }
    }
    private fun listColor(color: List<Production.DataK.ColorAndSize>) {
        aa.apply {
            this!!.addDatas(color)
            notifyDataSetChanged()
        }
    }

    override fun onClickColor(id: String) {
//        System.out.println("color: " + id)

        txtColor = id
    }

    override fun onClickSize(id: String) {
//        System.out.println("size: " + id)
//        a!!.notifyDataSetChanged()
        txtSize = id

    }
}