package com.example.mim

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class FilterFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    var aa:adapterlll? = null
    var a:adapterl? = null
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
        val view = inflater.inflate(R.layout.fragment_filter, container, false)
        var s = view.findViewById<RecyclerView>(R.id.recyclerView9)

        aa = adapterlll(arrayListOf())
        val layoutManagerr: RecyclerView.LayoutManager = GridLayoutManager(context, 2)
        s!!.layoutManager = layoutManagerr
        layoutManagerr.setAutoMeasureEnabled(true)
        s!!.setNestedScrollingEnabled(false)
        s!!.setHasFixedSize(true)
        s!!.adapter = aa
        ss()

        var ss = view.findViewById<RecyclerView>(R.id.recyclerView10)
        a = adapterl(arrayListOf())
        val layoutManagersr: RecyclerView.LayoutManager = GridLayoutManager(context, 3)
        ss!!.layoutManager = layoutManagersr
        layoutManagerr.setAutoMeasureEnabled(true)
        ss!!.setNestedScrollingEnabled(false)
        ss!!.setHasFixedSize(true)
        ss!!.adapter = a
        sss()

        return view
    }
    fun ss(){
        val call = APIClient.service.getColor()
        call.enqueue(object : Callback<Color?> {
            override fun onResponse(
                call: Call<Color?>?,
                response: Response<Color?>
            ) {
                Log.d("TAGH", response.code().toString() + "")
                val resource: Color = response.body()!!
                val datumList: List<Color.datas> = resource.data
                System.out.println(datumList)
                retrieveList(datumList)
            }

            override fun onFailure(call: Call<Color?>, t: Throwable) {
                System.out.println(t.message)
            }
        })
    }
    fun sss(){
        val call = APIClient.service.getSize()
        call.enqueue(object : Callback<Size?> {
            override fun onResponse(
                call: Call<Size?>?,
                response: Response<Size?>
            ) {
                Log.d("TAGH", response.code().toString() + "")
                val resource: Size = response.body()!!
                val datumList: List<Size.datas> = resource.data
                System.out.println(datumList)
                retrieveList1(datumList)
            }

            override fun onFailure(call: Call<Size?>, t: Throwable) {
                System.out.println(t.message)
            }
        })
    }
    private fun retrieveList(users: List<Color.datas>) {
        aa.apply {
            this!!.addDatas(users)
            notifyDataSetChanged()
        }
    }
    private fun retrieveList1(users: List<Size.datas>) {
        a.apply {
            this!!.addDatas(users)
            notifyDataSetChanged()
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FilterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}