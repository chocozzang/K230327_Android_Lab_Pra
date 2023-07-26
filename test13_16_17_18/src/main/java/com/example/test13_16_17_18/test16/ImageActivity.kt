package com.example.test13_16_17_18.test16

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityImageBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

// 코드 경로 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /ch16_provider/src/main/java/com/example/ch16_provider/MainActivity.kt
// 변경사항 : 바인딩 변경, ActivityImageBinding으로 변경
// /values/dimen.xml : 사이즈 변경 부분에 res에서 사용하는 부분
// 권한 설정 :
// https://developer.android.com/about/versions/13/behavior-changes-13?hl=ko
// 뷰 경로 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /ch16_provider/src/main/res/layout/activity_main.xml

// 갤러리와 카메라 연동 및 인텐트의 후처리 작업을 활용하여
// 비트맵 또는 drawable 타입으로 이미지를 처리하는 부분
// 주의사항) 미디어 서버에 접근하는 허가 부분이 조금 변경되어 이를 수정하고,
// 콘텐츠 프로바이더의 authorities 부분 주의할 것
// 내용은 그대로 사용하며, 코드 리뷰 시의 설명에서 확인

// 임의의 프로필 사진 한 장 준비 교체
// val photoURI: Uri = FileProvider.getUriForFile(
class ImageActivity : AppCompatActivity() {
    lateinit var binding: ActivityImageBinding
    lateinit var filePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mapButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + "37.5662952, 126.9779451"))
            startActivity(intent)
        }

        binding.callButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("tel:01077778888"))
            startActivity(intent)
        }

        //gallery request launcher..................
        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            try {
                val calRatio = calculateInSampleSize(
                    it.data!!.data!!,
                    resources.getDimensionPixelSize(R.dimen.imgSize),
                    resources.getDimensionPixelSize(R.dimen.imgSize)
                )
                val option = BitmapFactory.Options()
                option.inSampleSize = calRatio

                var inputStream = contentResolver.openInputStream(it.data!!.data!!)
                val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
                inputStream!!.close()
                inputStream = null

                bitmap?.let {
                    Log.d("test16", "결과 처리 전00")
                    binding.userImageView.setImageBitmap(bitmap)
                    Log.d("test16", "결과 처리 후00")
                } ?: let{
                    Log.d("test16", "bitmap null")
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }


        binding.galleryButton.setOnClickListener {
            //gallery app........................
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)
        }

        //camera request launcher.................
        // 후처리 함수에서 작업을 진행
        val requestCameraFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            // calRatio : 임의로 정한 변수,
            // 이미지 처리시 크기 문제(메모리 부족)를 해결하기 위해
            // 크기를 조정
            // calculateInSampleSize 함수는 크기를 계속 줄여 나감
            // 정수 3,, 4,, 5,, ...
            val calRatio = calculateInSampleSize(
                // 원본 데이터
                Uri.fromFile(File(filePath)),
                // 출력할 이미지의 크기를 임의로 지정
                // 크기는 /value/dimen.xml에 150dp로 지정하였음
                resources.getDimensionPixelSize(R.dimen.imgSize),
                resources.getDimensionPixelSize(R.dimen.imgSize)
            )
            // BitmapFactory 비트맵 타입으로 이미지를 그대로 처리하면 문제가 됨 (OOM)
            // 옵션을 정해서 이미지를 처리함
            val option = BitmapFactory.Options()
            // calRatio, 원본의 사진을 특정 비율에 맞게 줄인 결과 값.
            // if calRatio : 4이면 12MB 파일을 3MB로 줄여줌
            option.inSampleSize = calRatio
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            bitmap?.let {
                Log.d("test16", "결과 처리 전")
                binding.userImageView.setImageBitmap(bitmap)
                Log.d("test16", "결과 처리 후")
            }
        }

        // 카메라 버튼
        binding.cameraButton.setOnClickListener {
            //camera app......................
            //파일 준비...............
            val timeStamp: String =
                SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            // 안드로이드 시스템에서 정하는 DIRECTORY_PICTURES가 정해져 있음
            val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            // 실제 물리 파일을 준비함 "JPEG_${timeStamp}_"[ ].jpg
            val file = File.createTempFile(
                "JPEG_${timeStamp}_",
                ".jpg",
                storageDir
            )
            Log.d("test16", "cameraxyz00")
            // 파일의 실제 경로
            filePath = file.absolutePath
            // 카메라에서 찍은 사진에 접근하기 위해서, 콘텐츠 프로바이더에 요청
            // 요청시, 매니페스트에서 정한 같은 문자열을 사용한다.
            // "com.example.test13_16_17_18.fileprovider"
            Log.d("test16", "cameraxyz01")
            val photoURI: Uri = FileProvider.getUriForFile(
                this,
                "com.example.test13_16_17_18.fileprovider",
                file
            )
            Log.d("test16", "cameraxyz")
            // 현재 앱에서 외부 앱으로 가기 위해서 시스템으로 인텐트를 전달
            // 인텐트의 메시지 내용은, 액션의 문자열 카메라 앱
            // 데이터의 내용은 사진의 출력(카메라로 찍음), photoURI에 담기
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            // 후처리 함수를 호출하면, 위에 정한 후처리 작업 함수로 이동
            // 카메라 촬영 후, 체크 하고 나서 되돌아올 때 작업을 상단의 함수에서 처리함.
            Log.d("test16", "cameraqwer")
            requestCameraFileLauncher.launch(intent)
            Log.d("test16", "cameraasdf")
        }
    }

    // 이미지 크기를 줄이는 로직
    // 첫 매개변수 : 사진 원본 데이터
    // 두번째, 세번째 매개변수 : 원하는 가로 세로 크기 (출력 원하는 사진의 크기)
    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int {
        // 비트맵 객체 그대로 사용하면, 사진 원본을 그대로 사용해서 메모리 부족 현상 생김
        // 그래서 옵션이라는 속성을 사용함
        val options = BitmapFactory.Options()
        // 실제 비트맵 객체를 생성하는 것 아니고, 옵션만 설정하겠다는 것을 의미
        options.inJustDecodeBounds = true
        try {
            // 실제 원본 사진의 물리 경로에 접근해서 바이트로 읽고
            // 사진을 읽은 바이트 단위
            var inputStream = contentResolver.openInputStream(fileUri)

            //inJustDecodeBounds 값을 true 로 설정한 상태에서 decodeXXX() 를 호출.
            //로딩 하고자 하는 이미지의 각종 정보가 options 에 설정 된다.
            BitmapFactory.decodeStream(inputStream, null, options)
            // 읽었던 원본의 사진의 메모리 사용을 반납하고,
            // 다시 해당 객체에 할당을 해제함 (null)
            inputStream!!.close()
            inputStream = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //비율 계산........................
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1
        // inSampleSize 비율 계산
        // height, width 원본의 가로 세로 크기
        // 이것보다 크면 원본의 사이즈를 반으로 줄여주는 작업을 진행함
        if (height > reqHeight || width > reqWidth) {

            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }
}