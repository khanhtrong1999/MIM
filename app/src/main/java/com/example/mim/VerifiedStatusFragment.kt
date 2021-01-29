package com.example.mim

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class VerifiedStatusFragment : Fragment(), `in` {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var adapter:adapterk? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_verified_status, container, false)
        val recyc = view.findViewById<RecyclerView>(R.id.recyc)
        adapter = context?.let { adapterk(arrayListOf(), it, this) }
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyc!!.layoutManager = layoutManager
        recyc!!.itemAnimator = DefaultItemAnimator()
        recyc!!.adapter = adapter
        val id = arguments!!.getInt(ARG_PARAM1)
        ss(id)
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int, param2: String) =
            VerifiedStatusFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    fun ss(id:Int){
        val call = APIClient.service.getVerified(id)
        call.enqueue(object : Callback<Order?> {
            override fun onResponse(
                call: Call<Order?>?,
                response: Response<Order?>
            ) {
                Log.d("TAG", response.code().toString() + "")
                val resource: Order = response.body()!!
                val datumList: List<Order.obj2> = resource.data
                retrieveList(datumList)
            }

            override fun onFailure(call: Call<Order?>, t: Throwable) {
                System.out.println(t.message)
            }
        })
    }
    fun retrieveList(data:List<Order.obj2>){
        adapter.apply {
            this!!.addDatas(data)
            notifyDataSetChanged()
        }
    }
    override fun onClick(id: Int) {

    }
}