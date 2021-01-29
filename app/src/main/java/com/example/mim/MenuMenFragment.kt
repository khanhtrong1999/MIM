package com.example.mim

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MenuMenFragment : Fragment(),`in` {
    private var param1: String? = null
    private var param2: String? = null
    var adapterd:adapterjj? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_menu_men, container, false)
        adapter_1(view)
        adapter_2(view)
        return view
    }

    private fun adapter_1(view:View){
//        val list = listOf<String>(
//            "SHOES",
//            "CLOTHING",
//            "ACCESSORIES",
//            "SPORT",
//            "BRAND",
//            "RELEASE DATES"
//        )
//        val recyc = view.findViewById<RecyclerView>(R.id.rvContacts)
//        val adapter = adapterj(listOf())
//        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        recyc!!.layoutManager = layoutManager
//        layoutManager.setAutoMeasureEnabled(true)
//        recyc!!.setNestedScrollingEnabled(false)
//        recyc!!.setHasFixedSize(false);
//        recyc!!.adapter = adapter
    }

    private fun adapter_2(view:View){
//        val listt = listOf<String>(
//            "NEW ARRIVALS",
//            "ULTRABOOST",
//            "ESSENTIALS",
//            "RUY",
//            "ADICOLOR",
//            "SUPERSTAR"
//        )

        val recycl = view.findViewById<RecyclerView>(R.id.recyclerView)
        adapterd = adapterjj(arrayListOf(), this)
        val layoutManagerr = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recycl!!.layoutManager = layoutManagerr
        layoutManagerr.setAutoMeasureEnabled(true)
        recycl!!.setNestedScrollingEnabled(false)
        recycl!!.setHasFixedSize(true)
        recycl!!.adapter = adapterd
        sss()
    }
    fun sss(){
        val call = APIClient.service.getCategory()
        call.enqueue(object : Callback<Category?> {
            override fun onResponse(
                call: Call<Category?>?,
                response: Response<Category?>
            ) {
                Log.d("TAGH", response.code().toString() + "")
                val resource: Category = response.body()!!
                val datumList: List<Category.datas> = resource.data
                System.out.println(datumList)
                retrieveList1(datumList)
            }
            override fun onFailure(call: Call<Category?>, t: Throwable) {
                System.out.println(t.message)
            }
        })
    }
    private fun retrieveList1(users: List<Category.datas>) {
        adapterd.apply {
            this!!.addDatas(users)
            notifyDataSetChanged()
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuMenFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(id: Int) {

        val intent = Intent(context, ShowActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}