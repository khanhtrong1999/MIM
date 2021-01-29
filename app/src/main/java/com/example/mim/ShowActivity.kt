package com.example.mim

import ComparePricesShow
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ShowActivity : AppCompatActivity(), `in` {
    var datumList:List<Production2.DataK>? = null
    var adapterjjj:adaptehh? = null
    var boolean:Boolean? = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)
        var id = intent.getIntExtra("id", -1)
        System.out.println("lll"+id)
        var recy3 = findViewById<RecyclerView>(R.id.recyclerView6)
        val button = findViewById<Button>(R.id.sort) as Button
        val buttonfilter = findViewById<Button>(R.id.filter) as Button
        adapterjjj = this?.let { adaptehh(arrayListOf(), this, it) }
        val layoutManagerr: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        recy3!!.layoutManager = layoutManagerr
        layoutManagerr.setAutoMeasureEnabled(true)
        recy3!!.setNestedScrollingEnabled(false)
        recy3!!.setHasFixedSize(true);
        recy3!!.adapter = adapterjjj
        ss(id)
        button.setOnClickListener {
            if(boolean == true){
                datumList?.let { it1 -> sortASC(it1) }
                boolean = false
            }else{
                datumList?.let { it1 -> sortDESC(it1) }
                boolean = true
            }
        }
        val s = findViewById<DrawerLayout>(R.id.drawerLayout)
        buttonfilter.setOnClickListener {
            s.openDrawer(Gravity.RIGHT)
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.nav_view, FilterFragment.newInstance("",""))
                    .commit()
        }
    }

    fun ss(name: Int){
        val call = APIClient.service.getCategoryShow(name)
        call.enqueue(object : Callback<Production2?> {
            override fun onResponse(
                    call: Call<Production2?>?,
                    response: Response<Production2?>
            ) {
                Log.d("TAG", response.code().toString() + "")
                val resource: Production2 = response.body()!!
                datumList = resource.data



                retrieveList(datumList!!)
            }

            override fun onFailure(call: Call<Production2?>, t: Throwable) {
                System.out.println(t.message)
            }
        })
    }

    private fun retrieveList(data: List<Production2.DataK>) {
        adapterjjj.apply {
            this!!.addDatas(data)
            notifyDataSetChanged()
        }
    }
    private fun sortASC(data: List<Production2.DataK>) {
        var s:List<Production2.DataK> = data.sortedWith(ComparePricesShow)
        adapterjjj.apply {
            this!!.addDatas(s)
            notifyDataSetChanged()
        }
    }
    private fun sortDESC(data: List<Production2.DataK>) {
        var s:List<Production2.DataK> = data.sortedWith(ComparePricesShow).reversed()
        adapterjjj.apply {
            this!!.addDatas(s)
            notifyDataSetChanged()
        }
    }


    override fun onClick(id: Int) {
        var intent = Intent(this, ContentActivity::class.java)
        intent.putExtra("id",id)
        startActivity(intent)
    }
}