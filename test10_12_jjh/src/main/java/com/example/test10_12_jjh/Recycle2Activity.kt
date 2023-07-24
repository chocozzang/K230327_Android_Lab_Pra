package com.example.test10_12_jjh

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.test10_12_jjh.adapter.MyAdapter
import com.example.test10_12_jjh.adapter.MyAdapter2
import com.example.test10_12_jjh.databinding.ActivityRecycle2Binding
import com.example.test10_12_jjh.databinding.ActivityRecycleBinding

class Recycle2Activity : AppCompatActivity() {
    lateinit var binding : ActivityRecycle2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        // 리사이클러 뷰 - 옵션 부분, 배경 이미지 넣는 부분
        // 앞의 뷰홀더, 어댑터, 연결 부분은 동일함
        // 한 번 더 해보기
        super.onCreate(savedInstanceState)
        binding = ActivityRecycle2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 임시 데이터
        val datas = mutableListOf<String>()
        for(i in 1..9){
            datas.add("Item $i")
        }

        val linearlayoutmanager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = linearlayoutmanager
        linearlayoutmanager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.adapter = MyAdapter2(datas)
        // 배경 이미지 넣기 아이템 목록 요소 꾸미기 확인
        // 경로 :
        // https://github.com/lsy3709/AndroidLab/blob/master
        // /test11/src/main/java/com/example/test11/MainActivity350.kt
        binding.recyclerView.addItemDecoration(MyDecoration(this))

        // 구분선 설정, 이미지를 넣어볼 예정
//        binding.recyclerView.addItemDecoration(
//            DividerItemDecoration(this,
//                LinearLayoutManager.VERTICAL)
//        )
    }

    class MyDecoration(val context: Context): RecyclerView.ItemDecoration() {
        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDraw(c, parent, state)
            c.drawBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.stadium), 0f,0f,null);
        }

        override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDrawOver(c, parent, state)
            //뷰 사이즈 계산
            val width = parent.width
            val height = parent.height
            //이미지 사이즈 계산
            val dr: Drawable? = ResourcesCompat.getDrawable(context.getResources(), R.drawable.kbo, null)
            val drWidth = dr?.intrinsicWidth
            val drHeight = dr?.intrinsicHeight
            //이미지가 그려질 위치 계산
            val left = width / 2 - drWidth?.div(2) as Int
            val top = height / 2 - drHeight?.div(2) as Int
            c.drawBitmap(
                BitmapFactory.decodeResource(context.getResources(), R.drawable.kbo),
                left.toFloat(),
                top.toFloat(),
                null
            )

        }

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            val index = parent.getChildAdapterPosition(view) + 1

            if (index % 3 == 0) //left, top, right, bottom
                outRect.set(10, 10, 10, 60)
            else
                outRect.set(10, 10, 10, 0)

            view.setBackgroundColor(Color.LTGRAY)
            ViewCompat.setElevation(view, 20.0f)

        }
    }
}