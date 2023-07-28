package com.example.test13_16_17_18.test18reqres

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.test13_16_17_18.databinding.ActivityMain8Binding
import com.example.test13_16_17_18.test18reqres.Model.UserListModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 참고 경로 :
// https://github.com/lsy3709/K230201AndroidLab/tree/master/test18
// 구성 retrofit(2), recyclerView(어댑터 뷰홀더), retrofit 설정, 받아오는 데이터 모델링

// 순서 :
// 1) MainActivity 복사 -> 뷰 바인딩 변경
// 2) 뷰 코드 복사 :
// https://github.com/lsy3709/K230201AndroidLab/blob/master/test18/src/main/res/layout/activity_main.xml
// -> tools:context 이름 맞게 변경
// 3) MyApplication.kt - 일반 클래스 작업
// 경로 : https://github.com/lsy3709/K230201AndroidLab/blob/master/test18/src/main/java/com/example/test18/MyApplication.kt
// Manifest 설정 -> 인터넷 사용 권한 (외부 통신용)
// <uses-permission android:name="android.permission.INTERNET" />
// application 속성 추가 android:name=".test18reqres.MyApplication"
// 4) 인터페이스 작업 (MyApplication -> INetworkService)
// https://github.com/lsy3709/K230201AndroidLab/blob/master/test18/src/main/java/com/example/test18/retrofit/INetworkService.kt
// 5) 모델 - UserListModel 추가 -> 서버에서 넘어온 데이터를 임시 객체에 담아서 작업의 편의성을 높임
// https://github.com/lsy3709/K230201AndroidLab/blob/master/test18/src/main/java/com/example/test18/Model/UserListModel.kt
// https://github.com/lsy3709/K230201AndroidLab/blob/master/test18/src/main/java/com/example/test18/Model/UserModel.kt
// + Call Import -> Retrofit2.Call
// + MainActivity8 -> Call/Callback/Response 모두 Retrofit2로 import
// 7) Recycler View에 받아온 데이터를 출력함
// https://github.com/lsy3709/AndroidLab/blob/master/test18/src/main/java/com/example/test18/retrofit2/MyAdapter.kt
// 목록 아이템 요소(Recycler View)들에 대한 뷰 작업이 필요함
// 경로 :
// https://github.com/lsy3709/AndroidLab/blob/master/test18/src/main/res/layout/item_retrofit.xml
// + MyAdapter -> Call/Callback/Response 모두 Retrofit2로 import
// 코드 경로 :
// https://github.com/lsy3709/K230201AndroidLab/blob/master/test18/src/main/java/com/example/test18/MainActivity3.kt
// 뷰 경로 :
// https://github.com/lsy3709/K230201AndroidLab/blob/master/test18/src/main/res/layout/activity_main3.xml
class MainActivity8 : AppCompatActivity() {
    lateinit var binding : ActivityMain8Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain8Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //test , MyApplication에서 만든 형으로 다운캐스팅해서, 해당 객체 생성.
        // applicationContext -> 시스템에 등록된 설정 파일
        // 이 변수를 MyApplication으로 형변환 (다운캐스트)
        // MyApplication의 networkService를 변수로 받아서
        // networkService의 메소드를 사용할 것임 -> interface INetworkService 사용
        // INetworkService.kt
        val networkService = (applicationContext as MyApplication).networkService

        // INetworkService에 정의된 추상 함수이고, MyApplication 클래스에 붙였다.
        // 예  doGetUserList(2)
        // 여기에 함수의 형식이 계속 변경이 됩니다.
        val userListCall = networkService.doGetUserList("2")


        // 버튼1을 누르면 해당 test1()의 결과값 받아오기.
        binding.btn1test1.setOnClickListener {
            // 해당 애너테이션 정의 부분 사용해보기 예제2) @GET("users/list?sort=desc")
            // 다른 함수 사용
            // ++ val test1 = networkService.test1()
            // ++ fun doGetUserList(@Query("page") page: String): Call<UserListModel> 사용
            val test1 = networkService.doGetUserList("1")
            // 실제 네트워크 통신 부분의 시작임 -> test1.enqueue
            // 응답이 성공시 -> onResponse 함수가 호출
            //       실패시 -> onFailure 함수가 호출
            // onResponse 매개변수 정의 부분의 구조를 파악할 것
            // Parameter #1 -> Call, Parameter #2 -> Response
            // 결과는, Call한 결과를 Response에 데이터의 객체로써 넘어옴
            test1.enqueue(object : Callback<UserListModel> {
                override fun onResponse(call: Call<UserListModel>, response: Response<UserListModel>) {
                    val userModelSample = response.body()
                    Log.d("lsy", "test1()호출예제2이고 값조회: ${userModelSample}")
                }
                // 결과 :
                // UserListModel(page=1, perPage=6, total=12, totalPages=2,
                // data=[UserModel(id=1, firstName=George, lastName=Bluth, avatar=https://reqres.in/img/faces/1-image.jpg, email=george.bluth@reqres.in, avatarBitmap=null),
                // UserModel(id=2, firstName=Janet, lastName=Weaver, avatar=https://reqres.in/img/faces/2-image.jpg, email=janet.weaver@reqres.in, avatarBitmap=null),
                // UserModel(id=3, firstName=Emma, lastName=Wong, avatar=https://reqres.in/img/faces/3-image.jpg, email=emma.wong@reqres.in, avatarBitmap=null),
                // UserModel(id=4, firstName=Eve, lastName=Holt, avatar=https://reqres.in/img/faces/4-image.jpg, email=eve.holt@reqres.in, avatarBitmap=null),
                // UserModel(id=5, firstName=Charles, lastName=Morris, avatar=https://reqres.in/img/faces/5-image.jpg, email=charles.morris@reqres.in, avatarBitmap=null),
                // UserModel(id=6, firstName=Tracey, lastName=Ramos, avatar=https://reqres.in/img/faces/6-image.jpg, email=tracey.ramos@reqres.in, avatarBitmap=null)])

                override fun onFailure(call: Call<UserListModel>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }


        // 실제 통신을 해서 사용하기. 서버와 통신해서, 데이터를 받는다.
        userListCall.enqueue(
            //Callback : import 함.
            object : Callback<UserListModel> {
                // Callback 인터페이스를 구현한 특정 표현식을 쓸때,
                // 의무적으로 재정의를 해야하는 함수2개.
                // onResponse : 서버로 부터 응답 성공 했을 때, 수행하는 함수.
                // onFailure : 실패 했을 때, 수행하는 함수.
                override fun onResponse(
                    call: Call<UserListModel>,
                    response: Response<UserListModel>
                ) {
                    // 정상적으로 서버로부터 데이터를 받아왔다.
                    // 실제로 데이터를 받고나서, response.body 곳에 data가 있음.
                    val userList = response.body()

                    // 받아온 데이터를 콘솔에 출력.
                    // 실제 작업은 뷰에 바인딩.
                    // 리사이클러 뷰를 작업.
                    Log.d("lsy", "userList(response.body)의 값: ${userList} ")
                }

                override fun onFailure(call: Call<UserListModel>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
}