package com.example.mim

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mim.rooom.Word
import com.example.mim.rooom.WordViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SheetBottomFragment : BottomSheetDialogFragment(),`in`, inn {
    private var param1: Int? = null
    private var param2: String? = null
    var a:adapterjjjjjj? = null
    var aa:adapterll? = null
    var textView10:TextView? = null
    var textView11:TextView? = null
    var mWordViewModel: WordViewModel? = null
    var color:String? = null
    var size:String? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        if (dialog.window != null) {
            dialog.window!!.setGravity(Gravity.BOTTOM)
            dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            dialog.setCancelable(false)
        }
        dialog.setOnShowListener {
            val bottomSheet =
                (it as BottomSheetDialog).findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?
            val behavior = BottomSheetBehavior.from(bottomSheet!!)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.isDraggable = false
        }
        return dialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        System.out.println("BABA" + param1)
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view:View = inflater.inflate(R.layout.fragment_sheet_bottom, container, false)
        textView10 = view.findViewById<TextView>(R.id.textView10)
        textView11 = view.findViewById<TextView>(R.id.textView11)

        val recyclerView4 = view.findViewById<RecyclerView>(R.id.recyclerView4)

        aa = adapterll(arrayListOf(), this)
        val kk = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView4!!.layoutManager = kk
        recyclerView4!!.itemAnimator = DefaultItemAnimator()
        recyclerView4!!.adapter = aa

        val s = view.findViewById<RecyclerView>(R.id.recyclerView5)
        a = adapterjjjjjj(arrayListOf(), this)
        val k = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        s!!.layoutManager = k
        s!!.itemAnimator = DefaultItemAnimator()
        s!!.adapter = a

        var ss = arguments!!.getInt("key")
        var sas = arguments!!.getStringArrayList("key_obj")
        ss(ss)
        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        val but = view.findViewById<AppCompatButton>(R.id.node)
        but.setOnClickListener {
            val ss = Word(sas!![0].toInt(),sas!![1], sas!![6],sas!![2].toInt(),sas!![3].toInt(),sas!![5],sas!![4])
            ss.color =  color
            ss.size = size
            mWordViewModel!!.update(ss)

            dismiss()
        }
        System.out.println("BABA" + ss)
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int, param2: String) {
            SheetBottomFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
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
//                    val list: List<Production.DataK> = resource.data2
                    val listColor: List<Production.DataK.ColorAndSize> = resource.data.colors
                    val listSize: List<Production.DataK.ColorAndSize> = resource.data.sizes
//                    retrieveList(list)
                    show(datumList)
                    listSize(listSize)
                    listColor(listColor)
                }
            }

            override fun onFailure(call: Call<Production?>, t: Throwable) {
                System.out.println(t.message)
            }
        })
    }

    private fun show(resource: Production.DataK) {
        textView10!!.text = resource.name
        textView11!!.text = resource.price.toString()
    }

    //    private fun retrieveList(users: List<Production.DataK>) {
//        adapterjjj.apply {
//            this!!.addDatas(users)
//            notifyDataSetChanged()
//        }
//    }
    override fun onClick(id: Int) {
        System.out.print("con cqc")
    }

    override fun onClickColor(id: String) {
        color = id
    }

    override fun onClickSize(id: String) {
        size = id
    }
}