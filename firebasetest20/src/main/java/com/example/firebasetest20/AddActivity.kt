package com.example.firebasetest20

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.firebasetest20.databinding.ActivityAddBinding
import com.example.firebasetest20.util.dateToString
import java.io.File
import java.util.Date

class AddActivity : AppCompatActivity() {

    // 전역으로 기본 바인딩 -> 뷰 객체 모음
    lateinit var binding: ActivityAddBinding
    // 전역으로 파일 경로를 설정해서 갤러리에서 사진을 선택한 후 해당 파잉의 절대경로를 저장
    lateinit var filePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 바인딩 변환 후 인플레이트로 출력 객체 초기화
        binding= ActivityAddBinding.inflate(layoutInflater)
        // 실제 화면에 출력 -> 레이아웃을 그려줌
        setContentView(binding.root)

    }

    // 인텐트로 후처리하는 함수
    // 사진 선택 후에 돌아오는 경우 후처리를 하는 코드
    val requestLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult())
    {
        if(it.resultCode === android.app.Activity.RESULT_OK){
            // it의 해당 정보를 담은 액체
            // RESULT_OK => 안드로이드의 200코드와 동일함
            // Glide로 이미지를 처리함
            Glide
                .with(getApplicationContext())
                // 이미지 불러옴
                .load(it.data?.data)
                // 출력 사진의 크기
                .apply(RequestOptions().override(250, 200))
                // 사진의 크기를 조정험
                .centerCrop()
                // 불러온 이미지를 결과 뷰에 출력함
                .into(binding.addImageView)


            val cursor = contentResolver.query(it.data?.data as Uri,
                arrayOf<String>(MediaStore.Images.Media.DATA), null, null, null);
            cursor?.moveToFirst().let {
                filePath=cursor?.getString(0) as String
            }
        }
    }

    // 액션 바의 메뉴 구성 옵션
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // 액션 바 메뉴의 이벤트 리스너
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId === R.id.menu_add_gallery){
            val intent = Intent(Intent.ACTION_PICK)
            // 인텐트 옵션은 액션 문자열 - 이미지 선택 후 uri를 가져오는 데이터타입, MiME Type, 모든 이미지
            intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
            // 후처리르 호출하는 삼수이고, 위에 정의한 변수로 이동함
            // 즉, 사진을 선택해서 돌아오는 경우 위의 코드로 이동함 -> requestLauncher
            requestLauncher.launch(intent)
        }else if(item.itemId === R.id.menu_add_save){
            if(binding.addImageView.drawable !== null && binding.addEditView.text.isNotEmpty()){
                //store 에 먼저 데이터를 저장후 document id 값으로 업로드 파일 이름 지정
                saveStore()
            }else {
                Toast.makeText(this, "데이터가 모두 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    // 파이어베이스 스토어에 저장하는 기능의 함수.
    private fun saveStore(){
        //add............................
        // 맵 객체에 키, 값의 형태로 데이터를 data 변수에 담았음.
        val data = mapOf(
            // 인증된 유저의 이메일를 의미.
            // 앱이 시작시 인증을 체크하는 MyApplication의 checkAuth() 확인함.
            "email" to MyApplication.email,
            // 뷰에서 입력된 값
            "content" to binding.addEditView.text.toString(),
            "date" to dateToString(Date())
        )

        // MyApplication -> db -> 파이어 스토어를 사용하기위한 객체.
        // collection -> 컬렉션을 생성하는 함수 매개변수로 컬렉션 명,(임의로 지정가능.)
        MyApplication.db.collection("news")
            // add 부분에 , 임의로 만든 data 를 추가.
            .add(data)
            // 파이어스토어에 데이터가 잘 저장된 경우 동작하는 함수
            .addOnSuccessListener {
                // 일반 데이터 파이어 스토어에 저장이 잘되었을 겨웅에만
                // 이미지를 스토리지에 저장할 수 있도록 함
                uploadImage(it.id)
            }
            .addOnFailureListener{
                // 데이터 추가 실패시 실행
                Log.d("kkang", "data save error", it)
            }
    }

    // 업로드
    private fun uploadImage(docId: String){
        // 매개변수 부분은, 글 작성시, docId 라고, 문서번호(자동생성) 예) 5Ju6dQ9crjs401U9PbkJ
        // MyApplication -> 스토리지 사용하기 위한 객체.
        val storage = MyApplication.storage
        // 스토리지 객체에서 reference 를 이용해서, 해당 객체를 바인딩.
        val storageRef = storage.reference
        // imgRef 라는 객체로 업로드 및 다운로드를 실행하는데, 여기서는 업로드 부분.
        //child -> 상위 폴더, images 하위에 이미지 파일이 저장되는 구조.
        val imgRef = storageRef.child("images/${docId}.jpg")
        // 후처리 코드에서, 선택된 사진의 절대경로를 file라고 하는 참조형 변수에 할당.
        val file = Uri.fromFile(File(filePath))
        // imgRef 의 기능중, putFile 경로의 파일을 업로드 하는 기능.
        imgRef.putFile(file)
            // 이미지 업로드가 성공 했다면 수행되는 로직.
            .addOnSuccessListener {
                // 토스트로 저장이 잘되었다.
                Toast.makeText(this, "save ok..", Toast.LENGTH_SHORT).show()
                // AddActivity 수동으로 종료. 생명주기로 치면, onDestroy()
                finish()
            }
            // 실패시 수행할 로직.
            .addOnFailureListener{
                Log.d("kkang", "file save error", it)
            }
    }
}