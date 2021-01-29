package com.example.mim

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


class SearchActivity : AppCompatActivity(), `in` {
    var adapterjjj:adapterjjj? = null
    var datumList: List<obj1.obj2>? = null
    var boolean:Boolean? = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val editText = findViewById<EditText>(R.id.editText11) as EditText
        val button = findViewById<Button>(R.id.sort) as Button
        val buttonfilter = findViewById<Button>(R.id.filter) as Button
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        editText.postDelayed({
            editText.requestFocus()
            imm.showSoftInput(editText, 0)
        }, 100)

        var recy3 = findViewById<RecyclerView>(R.id.recyclerView6)
        adapterjjj = this?.let { adapterjjj(arrayListOf(), this, it) }
        val layoutManagerr: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        recy3!!.layoutManager = layoutManagerr
        layoutManagerr.setAutoMeasureEnabled(true)
        recy3!!.setNestedScrollingEnabled(false)
        recy3!!.setHasFixedSize(true);
        recy3!!.adapter = adapterjjj

        editText.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                ss(editText.text.toString())
//                System.out.print("thuong "+editText.text.toString())
                if(!editText.text.toString().equals("")){
                    ss(editText.text.toString())

                }

                return@OnEditorActionListener true
            }
            false
        })
//        ss("n")
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

    fun ss(name: String){
        val call = APIClient.service.getSearch(name)
        call.enqueue(object : Callback<obj1?> {
            override fun onResponse(
                    call: Call<obj1?>?,
                    response: Response<obj1?>
            ) {
                Log.d("TAG", response.code().toString() + "")
                val resource: obj1 = response.body()!!
                datumList = resource.data
//                sort(datumList)
                retrieveList(datumList!!)
//                System.out.print(resource)
            }

            override fun onFailure(call: Call<obj1?>, t: Throwable) {
                System.out.println(t.message)
            }
        })
    }
    private fun retrieveList(data: List<obj1.obj2>) {
        adapterjjj.apply {
            this!!.addDatas(data)
            notifyDataSetChanged()
        }
    }
    private fun sortASC(data: List<obj1.obj2>) {
        var s:List<obj1.obj2> = data.sortedWith(ComparePrices)
        adapterjjj.apply {
            this!!.addDatas(s)
            notifyDataSetChanged()
        }
    }
    private fun sortDESC(data: List<obj1.obj2>) {
        var s:List<obj1.obj2> = data.sortedWith(ComparePrices).reversed()
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