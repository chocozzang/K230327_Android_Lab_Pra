package com.example.test10_12_jjh.test12

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.test10_12_jjh.R
import com.example.test10_12_jjh.adapter.APIAdapter
import com.example.test10_12_jjh.model.TideModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDialog(val datas : MutableList<TideModel>) : BottomSheetDialogFragment() {

    lateinit var btn : Button
    lateinit var rcview : RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(
            R.layout.activity_google_map_bottom_sheet_dialog_fragment,
            container,
            false
        )
        rcview = view.findViewById(R.id.myrecyclerView2)
        rcview.adapter = APIAdapter(requireContext(),  datas)
        btn = view.findViewById(R.id.btn_hide_bt_sheet)
        btn.setOnClickListener {
            Log.d("google22", "out")
            dismiss()
        }
        return view
    }

}