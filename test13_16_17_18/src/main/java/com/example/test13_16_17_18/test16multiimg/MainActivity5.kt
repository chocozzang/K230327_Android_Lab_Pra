package com.example.test13_16_17_18.test16multiimg

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityMain5Binding
import com.example.test13_16_17_18.databinding.MultiImageItemBinding

// 깃허브 위치 :
// https://github.com/lsy3709/K230201AndroidLab/tree/master/test16-2multiimg
// 1. 코드 경로 :
// https://github.com/lsy3709/K230201AndroidLab/blob/master
// /test16-2multiimg/src/main/java/com/example/test16_2multiimg/MainActivity.kt
// 2. 뷰 경로 :
// https://github.com/lsy3709/K230201AndroidLab/blob/master
// /test16-2multiimg/src/main/res/layout/activity_main.xml
// 3. MultiImageItemBinding : 이미지를 리사이클러 뷰로 출력하기 위한 목록 아이템 요소의 뷰
// https://github.com/lsy3709/K230201AndroidLab/blob/master
// /test16-2multiimg/src/main/res/layout/multi_image_item.xml
// 4. Glide : 이미지를 출력하는 도구로서 코루틴을 적용하고 비동기식 처리임
// 많이 사용하는 도구이며 build.gradle에서 dependency를 추가하면 됨
// implementation 'com.github.bumptech.glide:glide:4.12.0'
class MainActivity5 : AppCompatActivity() {
    lateinit var binding : ActivityMain5Binding
    // 갤러리에 선택된 이미지의 주소를 하나로 객체화하여 리스트에 담음
    var list = ArrayList<Uri>()

    var adapter = MultiImageAdapter(list,this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        binding = ActivityMain5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            list.clear()
            if(it.data?.clipData != null){
                val count = it.data!!.clipData?.itemCount
                if (count != null) {
                    if(count > 10) {
                        Toast.makeText(this@MainActivity5,"사진 선택은 10장까지만 가능",Toast.LENGTH_SHORT).show()
                    }
                }
                for (i in 0 until count!!){
                    val imageUri = it.data!!.clipData?.getItemAt(i)?.uri
                    Log.d("jjh uri", "uri : $imageUri")
                    if (imageUri != null) {
                        list.add(imageUri)
                    }
                }
            } else {
                it.data.let {
                        uri ->
                    val imageUri : Uri? = it.data?.data
                    if(imageUri != null){
                        list.add(imageUri)
                    }
                }
            }
            adapter.notifyDataSetChanged()
        }

        binding.getImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true)
            intent.setType("image/*")
            intent.action = Intent.ACTION_GET_CONTENT

            requestGalleryLauncher.launch(intent)
        }
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter


    }

    // 테스트용으로 뷰홀더, 어댑터를 한 번에 사용하고 있음
    // 리사이클러 뷰에 출력하기 위한 목록 요소의 아이템 뷰
    class ViewHolder(val binding: MultiImageItemBinding) : RecyclerView.ViewHolder(binding.root)
    // 어댑터는 뷰홀더를 가져와야함
    class MultiImageAdapter(val items:ArrayList<Uri>,val context: Context) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        // 뷰홀더를 생성함
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RecyclerView.ViewHolder
        // 여기부터 직접 정의함
                = ViewHolder(MultiImageItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

        // 뷰홀더와 객체를 합쳐줌
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val binding = (holder as ViewHolder).binding
            // Glide 이미지 전문으로 처리 해주는 API
            // bitmapFactory options 설정, 이상한 calc 함수통해서 리사이즈.
            // context -> 현재 액티비티 -> this@MainActivity5.
            // load , 사진을 불러오기. items => list , 각사진의 Uri 주소가 담아져 있음.
            // override -> 직접 원하는 사이즈 적어주면,
            // into , 해당 뷰에 , 사진을 넣는 작업.
            // items에 들어간 이미지 요소들을 뷰에 넣는 작업
            // 이때 Glide를 활용하여 이미지를 넣음
            Glide.with(context).load(items[position])
                // 출력 이미지의 세로 가로를 설정할 수 있음
                .override(500,500)
                // 출력 이미지 뷰에 선택한 이미지를 불러오는 역할
                .into(binding.image)
        }

        override fun getItemCount(): Int {
            return items.size
        }


    }
}