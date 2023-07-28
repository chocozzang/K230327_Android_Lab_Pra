package com.example.test13_16_17_18.test18reqres.Adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test13_16_17_18.databinding.ItemRetrofitBinding
import com.example.test13_16_17_18.test18reqres.Model.UserModel
import com.example.test13_16_17_18.test18reqres.MyApplication
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewHolder(val binding: ItemRetrofitBinding): RecyclerView.ViewHolder(binding.root)

// MainActivity9에서, MyAdapter 클래스의 매개변수가 2개인 초기화 생성자를 호출함
// 서버에서 받은 데이터를 List로 대입함.
class MyAdapter (val context: Context, val datas: List<UserModel>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder(ItemRetrofitBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyViewHolder).binding
        val user = datas?.get(position)
        binding.id.text=user?.id
        binding.firstNameView.text=user?.firstName
        binding.lastNameView.text=user?.lastName

        // 네트워크 통신을 사용해서 이미지를 가져오는 작업임
        user?.avatar?.let {
            val avatarImageCall = (context.applicationContext as MyApplication).networkService.getAvatarImage(it)
            // 실제 통신 시작
            avatarImageCall.enqueue(object : Callback<ResponseBody> {
                // 성공시
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            // 데이터를 받아서 앱에서 사용 가능한 이미지 타입으로 변환해서 사용함.
                            val bitmap = BitmapFactory.decodeStream(response.body()!!.byteStream())
                            binding.avatarView.setImageBitmap(bitmap)
                        }
                    }
                }
                // 실패시
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    call.cancel()
                }
            })
        }
    }
}