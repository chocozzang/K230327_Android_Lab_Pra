package com.example.test10_12_jjh.test12

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test10_12_jjh.R
import com.example.test10_12_jjh.adapter.APIAdapter
import com.example.test10_12_jjh.databinding.ActivitySixthPracticeRecycleBinding
import com.example.test10_12_jjh.model.TideModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SixthPracticeRecycle : AppCompatActivity() {
    lateinit var binding : ActivitySixthPracticeRecycleBinding
    lateinit var obscode : String
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySixthPracticeRecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinnerDropdown = binding.spinnerDropdown
        val options = resources.getStringArray(R.array.dropdown_items)
        val optiontoSpinner = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, options)
        optiontoSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDropdown.adapter = optiontoSpinner

        spinnerDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                obscode = selectedItem
                Log.d("apitest", "$selectedItem")
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        binding.btnClickMe.setOnClickListener {
            val apikey = "/FFdZti8UpV2Ku/EnEYvg=="
            //val obscode = "DT_0001"
            val resulttype = "json"
            val today = LocalDateTime.now()
            val firstDay = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
            val secondDay = today.plusDays(1L).format(DateTimeFormatter.ofPattern("yyyyMMdd"))
            val thirdDay = today.plusDays(2L).format(DateTimeFormatter.ofPattern("yyyyMMdd"))
            val fourthDay = today.plusDays(3L).format(DateTimeFormatter.ofPattern("yyyyMMdd"))
            val fifthDay = today.plusDays(4L).format(DateTimeFormatter.ofPattern("yyyyMMdd"))
            val sixthDay = today.plusDays(5L).format(DateTimeFormatter.ofPattern("yyyyMMdd"))
            val seventhDay = today.plusDays(6L).format(DateTimeFormatter.ofPattern("yyyyMMdd"))

            val networkService = (applicationContext as APIApplication).tideService
            val tidelist = mutableListOf<TideModel>()
            val myadapter = APIAdapter(this@SixthPracticeRecycle, tidelist, null, null)

            val mytide1 = networkService.getTide(apikey, obscode, firstDay, resulttype)
            val mytide2 = networkService.getTide(apikey, obscode, secondDay, resulttype)
            val mytide3 = networkService.getTide(apikey, obscode, thirdDay, resulttype)
            val mytide4 = networkService.getTide(apikey, obscode, fourthDay, resulttype)
            val mytide5 = networkService.getTide(apikey, obscode, fifthDay, resulttype)
            val mytide6 = networkService.getTide(apikey, obscode, sixthDay, resulttype)
            val mytide7 = networkService.getTide(apikey, obscode, seventhDay, resulttype)

            mytide1.enqueue(object : Callback<TideModel> {
                override fun onResponse(call: Call<TideModel>, response: Response<TideModel>) {
                    tidelist.clear()
                    Log.d("apitest", "${tidelist.size}")
                    val tide = response.body()
                    Log.d("apitest", "mytide1")
                    tidelist.add(tide!!)
                    mytide2.enqueue(object : Callback<TideModel> {
                        override fun onResponse(call : Call<TideModel>, response : Response<TideModel>) {
                            val tide = response.body()
                            tidelist.add(tide!!)
                            Log.d("apitest", "mytide2")
                            mytide3.enqueue(object : Callback<TideModel> {
                                override fun onResponse(call : Call<TideModel>, response : Response<TideModel>) {
                                    val tide = response.body()
                                    tidelist.add(tide!!)
                                    Log.d("apitest", "mytide3")
                                    mytide4.enqueue(object : Callback<TideModel> {
                                        override fun onResponse(call : Call<TideModel>, response : Response<TideModel>) {
                                            val tide = response.body()
                                            tidelist.add(tide!!)
                                            Log.d("apitest", "mytide4")
                                            mytide5.enqueue(object : Callback<TideModel> {
                                                override fun onResponse(call : Call<TideModel>, response : Response<TideModel>) {
                                                    val tide = response.body()
                                                    tidelist.add(tide!!)
                                                    Log.d("apitest", "mytide5")
                                                    mytide6.enqueue(object : Callback<TideModel> {
                                                        override fun onResponse(call : Call<TideModel>, response : Response<TideModel>) {
                                                            val tide = response.body()
                                                            tidelist.add(tide!!)
                                                            Log.d("apitest", "mytide6")
                                                            mytide7.enqueue(object :
                                                                Callback<TideModel> {
                                                                override fun onResponse(call : Call<TideModel>, response : Response<TideModel>) {
                                                                    val tide = response.body()
                                                                    tidelist.add(tide!!)
                                                                    Log.d("apitest", "mytide7")
                                                                    val layoutManager = LinearLayoutManager(this@SixthPracticeRecycle)
                                                                    myadapter.notifyDataSetChanged()
                                                                    layoutManager.orientation = LinearLayoutManager.VERTICAL
                                                                    binding.myrecyclerView.layoutManager = layoutManager
                                                                    binding.myrecyclerView.addItemDecoration(
                                                                        DividerItemDecoration(
                                                                            this@SixthPracticeRecycle, LinearLayoutManager.VERTICAL
                                                                        )
                                                                    )
                                                                    binding.myrecyclerView.adapter = myadapter
                                                                }
                                                                override fun onFailure(call: Call<TideModel>, t: Throwable) {
                                                                    Log.d("apitest", "failed")
                                                                    call.cancel()
                                                                }
                                                            })
                                                        }
                                                        override fun onFailure(call: Call<TideModel>, t: Throwable) {
                                                            Log.d("apitest", "failed")
                                                            call.cancel()
                                                        }
                                                    })
                                                }
                                                override fun onFailure(call: Call<TideModel>, t: Throwable) {
                                                    Log.d("apitest", "failed")
                                                    call.cancel()
                                                }
                                            })
                                        }
                                        override fun onFailure(call: Call<TideModel>, t: Throwable) {
                                            Log.d("apitest", "failed")
                                            call.cancel()
                                        }
                                    })
                                }
                                override fun onFailure(call: Call<TideModel>, t: Throwable) {
                                    Log.d("apitest", "failed")
                                    call.cancel()
                                }
                            })

                        }
                        override fun onFailure(call: Call<TideModel>, t: Throwable) {
                            Log.d("apitest", "failed")
                            call.cancel()
                        }
                    })
                }
                override fun onFailure(call: Call<TideModel>, t: Throwable) {
                    Log.d("apitest", "failed")
                    call.cancel()
                }
            })
        }


//        val layoutManager = LinearLayoutManager(this)
//        layoutManager.orientation = LinearLayoutManager.VERTICAL
//        binding.myrecyclerView.layoutManager = layoutManager
//        //binding.myrecyclerView.adapter = AdapterPractice(tempdatas)
//
//        //binding.myrecyclerView.addItemDecoration(MyDecoration(this))
//        // 배경 이미지 입력 및 아이템 목록 요소 꾸미기 확인
//        binding.myrecyclerView.addItemDecoration(
//            DividerItemDecoration(this,
//                LinearLayoutManager.VERTICAL)
//        )
    }

    class MyDecoration(val context: Context): RecyclerView.ItemDecoration() {
        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            // onDraw -> 목록의 뒤에 그려줌
            super.onDraw(c, parent, state)
            c.drawBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.yuri5), 0f,0f,null);
        }

        // onDrawOver -> 목록의 위에 올려줌
        override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDrawOver(c, parent, state)
            //뷰 사이즈 계산
            val width = parent.width
            val height = parent.height
            //이미지 사이즈 계산
            val dr: Drawable? = ResourcesCompat.getDrawable(context.getResources(),
                R.drawable.kbo, null)
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