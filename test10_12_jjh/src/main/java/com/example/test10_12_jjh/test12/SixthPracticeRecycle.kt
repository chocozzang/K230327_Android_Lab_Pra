package com.example.test10_12_jjh.test12

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test10_12_jjh.R
import com.example.test10_12_jjh.adapter.APIAdapter
import com.example.test10_12_jjh.adapter.AdapterPractice
import com.example.test10_12_jjh.databinding.ActivitySixthPracticeRecycleBinding
import com.example.test10_12_jjh.model.TideModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SixthPracticeRecycle : AppCompatActivity() {
    lateinit var binding : ActivitySixthPracticeRecycleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySixthPracticeRecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apikey = "/FFdZti8UpV2Ku/EnEYvg=="
        val obscode = "DT_0001"
        val resulttype = "json"

        val networkService = (applicationContext as APIApplication).networkService
        val tidelist = mutableListOf<TideModel>()

        val mytide1 = networkService.getTide(apikey, obscode, "20230730", resulttype)
        val mytide2 = networkService.getTide(apikey, obscode, "20230731", resulttype)
        val mytide3 = networkService.getTide(apikey, obscode, "20230801", resulttype)
        val mytide4 = networkService.getTide(apikey, obscode, "20230802", resulttype)
        val mytide5 = networkService.getTide(apikey, obscode, "20230803", resulttype)
        val mytide6 = networkService.getTide(apikey, obscode, "20230804", resulttype)
        val mytide7 = networkService.getTide(apikey, obscode, "20230805", resulttype)

        // Q1. 왜 순서대로 못받는지
        // Q2. 스크롤 내리면 앱 종료됨

        mytide1.enqueue(object : Callback<TideModel> {
            override fun onResponse(call: Call<TideModel>, response: Response<TideModel>) {
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
                                                        mytide7.enqueue(object : Callback<TideModel> {
                                                            override fun onResponse(call : Call<TideModel>, response : Response<TideModel>) {
                                                                val tide = response.body()
                                                                tidelist.add(tide!!)
                                                                Log.d("apitest", "mytide7")
                                                                val layoutManager = LinearLayoutManager(this@SixthPracticeRecycle)
                                                                layoutManager.orientation = LinearLayoutManager.VERTICAL
                                                                binding.myrecyclerView.layoutManager = layoutManager
                                                                binding.myrecyclerView.adapter = APIAdapter(this@SixthPracticeRecycle, tidelist)
                                                                binding.myrecyclerView.addItemDecoration(
                                                                    DividerItemDecoration(
                                                                        this@SixthPracticeRecycle, LinearLayoutManager.VERTICAL
                                                                    )
                                                                )
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

/*
        mytide1.enqueue(object : Callback<TideModel> {
            override fun onResponse(call : Call<TideModel>, response : Response<TideModel>) {
                val tide = response.body()
                tidelist.add(tide!!)
                Log.d("apitest", "mytide1")
            }
            override fun onFailure(call: Call<TideModel>, t: Throwable) {
                Log.d("apitest", "failed")
                call.cancel()
            }
        })

        mytide2.enqueue(object : Callback<TideModel> {
            override fun onResponse(call : Call<TideModel>, response : Response<TideModel>) {
                val tide = response.body()
                tidelist.add(tide!!)
                Log.d("apitest", "mytide2")
            }
            override fun onFailure(call: Call<TideModel>, t: Throwable) {
                Log.d("apitest", "failed")
                call.cancel()
            }
        })

        mytide3.enqueue(object : Callback<TideModel> {
            override fun onResponse(call : Call<TideModel>, response : Response<TideModel>) {
                val tide = response.body()
                tidelist.add(tide!!)
                Log.d("apitest", "mytide3")
            }
            override fun onFailure(call: Call<TideModel>, t: Throwable) {
                Log.d("apitest", "failed")
                call.cancel()
            }
        })

        mytide4.enqueue(object : Callback<TideModel> {
            override fun onResponse(call : Call<TideModel>, response : Response<TideModel>) {
                val tide = response.body()
                tidelist.add(tide!!)
                Log.d("apitest", "mytide4")
            }
            override fun onFailure(call: Call<TideModel>, t: Throwable) {
                Log.d("apitest", "failed")
                call.cancel()
            }
        })

        mytide5.enqueue(object : Callback<TideModel> {
            override fun onResponse(call : Call<TideModel>, response : Response<TideModel>) {
                val tide = response.body()
                tidelist.add(tide!!)
                Log.d("apitest", "mytide5")
            }
            override fun onFailure(call: Call<TideModel>, t: Throwable) {
                Log.d("apitest", "failed")
                call.cancel()
            }
        })

        mytide6.enqueue(object : Callback<TideModel> {
            override fun onResponse(call : Call<TideModel>, response : Response<TideModel>) {
                val tide = response.body()
                tidelist.add(tide!!)
                Log.d("apitest", "mytide6")
            }
            override fun onFailure(call: Call<TideModel>, t: Throwable) {
                Log.d("apitest", "failed")
                call.cancel()
            }
        })

        mytide7.enqueue(object : Callback<TideModel> {
            override fun onResponse(call : Call<TideModel>, response : Response<TideModel>) {
                val tide = response.body()
                tidelist.add(tide!!)
                Log.d("apitest", "mytide7")
                if(tidelist.size == 7) {

                }
            }
            override fun onFailure(call: Call<TideModel>, t: Throwable) {
                Log.d("apitest", "failed")
                call.cancel()
            }
        })*/


        /*
        val tempdatas = mutableListOf<String>()
        for(i in 1..20) {
            tempdatas.add("Temp Item $i")
        }*/

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