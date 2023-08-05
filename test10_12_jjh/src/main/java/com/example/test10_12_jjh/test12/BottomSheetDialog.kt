package com.example.test10_12_jjh.test12

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test10_12_jjh.R
import com.example.test10_12_jjh.adapter.APIAdapter
import com.example.test10_12_jjh.model.TideModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDialog(val datas : MutableList<TideModel>, val levels : MutableList<Entry>) : BottomSheetDialogFragment() {

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
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        rcview.layoutManager = linearLayoutManager
        rcview.adapter = APIAdapter(requireContext(),  datas)
//        btn = view.findViewById(R.id.btn_hide_bt_sheet)
//        btn.setOnClickListener {
//            Log.d("google22", "out")
//            dismiss()
//        }
//        val windowHeight = (resources.displayMetrics.heightPixels * 0.8).toInt()
//        Log.d("google22", "$windowHeight")
//        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, windowHeight)


        val dataset = LineDataSet(levels, "물높이")
        val lineData = LineData(dataset)
        val lineChart : LineChart = view.findViewById(R.id.lineChart)
        lineChart.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return value.toInt().toString()
            }
        }
        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        lineChart.data = lineData
        lineChart.invalidate()

        return view
    }

}