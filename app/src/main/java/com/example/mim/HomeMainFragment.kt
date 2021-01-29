package com.example.mim

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeMainFragment : Fragment(), `in` {
    private var param1: String? = null
    private var param2: String? = null
    var progressDoalog: ProgressDialog? = null
    var adapterjjj:adapterjjj?=null
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
        val view:View = inflater.inflate(R.layout.fragment_home_main, container, false)
        val recy: RecyclerView = view.findViewById(R.id.recyclerView)
        val recy3: RecyclerView = view.findViewById(R.id.recyclerView3)
        val item = listOf(
                R.drawable.adidas,
                R.drawable.converse,
                R.drawable.koolaburra,
                R.drawable.mega,
                R.drawable.skechers,
                R.drawable.oo,
                R.drawable.updatedlifestridelogo
        )

        

        var adapter = adapter(item)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recy!!.layoutManager = layoutManager
        recy!!.itemAnimator = DefaultItemAnimator()
        recy!!.adapter = adapter
        ss()
//        val itemjjj = listOf(
//            "gdghhgghsdjh",
//            "gdghhgghsdjh",
//            "gdghhgghsdjh",
//            "gdghhgghsdjh",
//            "gdghhgghsdjh",
//            "gdghhgghsdjh",
//            "gdghhgghsdjh",
//            "gdghhgghsdjh"
//        )

        adapterjjj = context?.let { adapterjjj(arrayListOf(), this, it) }
        val layoutManagerr: RecyclerView.LayoutManager = GridLayoutManager(context, 2)
        recy3!!.layoutManager = layoutManagerr
        layoutManagerr.setAutoMeasureEnabled(true)
        recy3!!.setNestedScrollingEnabled(false)
        recy3!!.setHasFixedSize(true)
        recy3!!.adapter = adapterjjj

        val relativeLayout = view.findViewById<ConstraintLayout>(R.id.relativeLayout);
        relativeLayout.setOnClickListener {
            onClickA()
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeMenFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(id: Int) {
//        findNavController().navigate(R.id.action_homeFragment_to_contentFragment)
        val intent = Intent(context, ContentActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    fun onClickA() {
        val intent = Intent(context, ShowActivity::class.java)
        startActivity(intent)
    }

    fun ss(){
        val call = APIClient.service.getHome()
        call.enqueue(object : Callback<obj1?> {
            override fun onResponse(
                    call: Call<obj1?>?,
                    response: Response<obj1?>
            ) {
                Log.d("TAG", response.code().toString() + "")
                val resource: obj1 = response.body()!!
                val datumList: List<obj1.obj2> = resource.data
                retrieveList(datumList)
            }

            override fun onFailure(call: Call<obj1?>, t: Throwable) {
                System.out.println(t.message)
            }
        })
    }
    private fun retrieveList(users: List<obj1.obj2>) {
        adapterjjj.apply {
            this!!.addDatas(users)
            notifyDataSetChanged()
        }
    }
}