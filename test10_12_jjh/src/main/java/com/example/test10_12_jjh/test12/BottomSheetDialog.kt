package com.example.test10_12_jjh.test12

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.test10_12_jjh.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDialog : BottomSheetDialogFragment() {

    lateinit var btn : Button
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
        btn = view.findViewById(R.id.btn_hide_bt_sheet)
        btn.setOnClickListener {
            Log.d("google22", "out")
            dismiss()
        }
        return view
    }

}