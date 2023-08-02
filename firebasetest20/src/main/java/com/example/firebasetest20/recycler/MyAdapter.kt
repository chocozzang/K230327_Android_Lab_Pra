package com.example.firebasetest20.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firebasetest20.MyApplication
import com.example.firebasetest20.databinding.ItemMainBinding
import com.example.firebasetest20.model.ItemData

// 리사이클러 뷰 -> 목록 형식으로 출력되는 뷰
// 뷰 홀더 -> 뷰 객체들의 모음집
// : 해당 뷰 홀더의 주생성자의 매개변수에, 바인딩 기법으로 객체가 선언됨.
class MyViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)

// 어댑터 : 데이터 <-> 뷰 연결
// ex. 데이터를 받아오고, 해당 바인딩으로 받아온 데이터를 연결함
// 주 생성자의 매개변수에 val으로 선언하면, 클래스 내부에서 전역처럼 쓸 수 있음
// Context -> 액티비티나 프래그먼트 형식
// itemList -> 실제 데이터 (공공데이터이거나, 개발자가 임의로 지정한 데이터)
// 리사이클러뷰 구성 클래스들의 공통으로 모두 RecyclerView 관련 부모 클래스를 상속받아야함
class MyAdapter(val context: Context, val itemList: MutableList<ItemData>): RecyclerView.Adapter<MyViewHolder>() {

    // 어댑터 클래스를 만들면, 세 함수를 반드시 override 해야함
    // 인플레이터 -> 해당 뷰를 출력하기 위한 객체를 초기화하는 작업
    // 초기화 ( 참조형 변수에 해당 메모리 위치 주소값을 할당하는 것을 의미함)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyViewHolder(ItemMainBinding.inflate(layoutInflater))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    // 뷰 - 데이터 연결
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        // 받은 데이터의 리스트 인덱스로, 해당 아이템 모델 객체에 접근함
        val data = itemList.get(position)

        // 뷰바인딩
        holder.binding.run {
            itemEmailView.text = data.email
            itemDateView.text = data.date
            itemContentView.text = data.content
        }

        //스토리지 이미지 다운로드........................
        // MyApplication은 매니페스트에 등록되어,
        // 앱이 시작되는 경우 이미 메모리에 로딩됨
        // storage.reference.child : 스토리지의 객체이고
        // 매개변수에 정의된 부분. images 상위폴더가 우리가 만든 임의의 펄더임
        // /${data.docId}.jpg가 이미지의 파일명임
        val imgRef = MyApplication.storage.reference.child("images/${data.docId}.jpg")
        // imgRef 객체를 이용해서 업로드 및 다운로드 기능을 구현
        // 여기서는 다운로드를 예를 들고 있음
        // 보통 다운로드가 실행이 되는 경우에 콜백으로 돌아와서 로직을 수행함
        imgRef.downloadUrl.addOnCompleteListener{ task ->
            if(task.isSuccessful){
                // 후처리 -> 스토리지의 이미지 URL 주소를 잘 가지고 왔다면,
                // Glide를 이용해서 이미지를 불러오고
                // into를 통해서 해당 결과 이미지 뷰에 출력하는 코드
                // 원래 안드로이드에는 이미즈의 객체 타임을 bitMap 구조로 바꾸어주는 코드가 있음
                // 그러나 여기서는 implementation 'com.firebaseui:firebase-ui-storage:8.0.0'을
                // 이용해서 한 번에 다운로드 받고 이미지를 바이트로 처리하여 출력까지 한 번에 해결하는 라이브러리
                Glide.with(context)
                    // 이미지를 불러옴
                    .load(task.result)
                    // 불러온 이미지를 결과 뷰에 출력하는 코드
                    .into(holder.binding.itemImageView)
            }
        }
    }
}