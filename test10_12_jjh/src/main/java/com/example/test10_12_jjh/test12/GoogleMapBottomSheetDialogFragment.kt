package com.example.test10_12_jjh.test12

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.test10_12_jjh.R

class GoogleMapBottomSheetDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 여기에서 원하는 레이아웃 파일을 inflate하여 사용할 수 있습니다.
        return inflater.inflate(R.layout.activity_google_map_bottom_sheet_dialog_fragment, container, false)
    }
}