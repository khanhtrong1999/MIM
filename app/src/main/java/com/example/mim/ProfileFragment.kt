package com.example.mim

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProfileFragment : Fragment(), `in` {
    private var param1: String? = null
    private var param2: String? = null

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
//        recyclerView11
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val sharedpreferences = context!!.getSharedPreferences(
                "APPMIMM",
                Context.MODE_PRIVATE
        )
        var id: String? = sharedpreferences.getString("mail","HIHI")
        val k = view.findViewById<TextView>(R.id.kkk)

        k.text = id
        val recyc = view.findViewById<RecyclerView>(R.id.recyclerView11)
        val list = listOf<String>(
            "ORDER",
            "CHANGE PROFILE",
            "CHANGE PASSWORD",
            "LOGOUT",
        )
        val adapter = adapterj(list, this)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyc!!.layoutManager = layoutManager
        layoutManager.setAutoMeasureEnabled(true)
        recyc!!.setNestedScrollingEnabled(false)
        recyc!!.setHasFixedSize(false);
        recyc!!.adapter = adapter
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(id: Int) {
        when(id){
            0 -> {
                var intent = Intent(context, StatusActivity::class.java)
                startActivity(intent)
            }
            1 -> {
                var intent = Intent(context, ChangeProfile::class.java)
                startActivity(intent)
            }
        }
    }
}