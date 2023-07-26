package com.example.test13_16_17_18.test13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityMain443Binding
import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.DefaultExecutor.thread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

// 코드 링크 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /test13/src/main/java/com/example/test13/MainActivity443.kt
// 뷰 링크 :
// https://github.com/lsy3709/AndroidLab/blob/master
// /test13/src/main/res/layout/activity_main443.xml
class MainActivity443 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain443Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            // 해결책 1 : 핸들러 방식
//            var sum = 0L
//            // 무거운 연산 : CPU 테스트
//            var time = measureTimeMillis {
//                for(i in 1..2_000_000_000){
//                    sum += i
//                }
//            }
//            Log.d("kkang","time : $time")
//            binding.resultView.text = "sum : $sum"
//
//            val handler=object: Handler(){
//                override fun handleMessage(msg: Message) {
//                    super.handleMessage(msg)
//                    binding.resultView.text = "sum : ${msg.arg1}"
//                }
//            }
//
//            thread {
//                var sum = 0L
//                var time = measureTimeMillis {
//                    for (i in 1..2_000_000_000) {
//                        sum += i
//                    }
//                    val message = Message()
//                    message.arg1=sum.toInt()
//                    handler.sendMessage(message)
//                }
//                Log.d("kkang", "time : $time")
//            }
            
            // 해결책 2 : 코루틴 방식
            // 코투린 데이터 결과를 메시지로 전달
            val channel = Channel<Int>()
            
            // 코루틴, 스코프(클래스)를 구성해서
            // 메인 스코프, 백그라운드 스코프
            // 백그라운드에서 1) 무거운 연산(CPU 연산) 2) 네트워크, 파일 IO 작업 Dispatchers.IO
            // 3) 메인 작업 Dispatchers.Main
            val backgroundScope = CoroutineScope(Dispatchers.Default + Job())
            backgroundScope.launch {
                var sum = 0L
                var time = measureTimeMillis {
                    for (i in 1..10_000_000_000) {
                        sum += i
                    }
                }
                Log.d("test13", "time : $time")
                // 결과값을 메인에 전달함
                channel.send(sum.toInt())
            }
            
            // 메인스코프
            // GlobalScope : 시스템의 스코프를 사용함
            val mainScope= GlobalScope.launch(Dispatchers.Main) {
                channel.consumeEach {
                    binding.resultView.text = "sum : $it"
                }
            }


        }
    }
}