package com.example.test10_12_jjh.test12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test10_12_jjh.R
import com.example.test10_12_jjh.databinding.ActivityApiTestBinding
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class ApiTestActivity : AppCompatActivity() {
    lateinit var binding : ActivityApiTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityApiTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textView.text = ""
        binding.button.setOnClickListener {
            val thread = NetworkThread()
            thread.start()
            thread.join()
        }
    }

    inner class NetworkThread : Thread() {
        override fun run() {
            Log.d("apitest", "start")
            val key = "/FFdZti8UpV2Ku/EnEYvg=="
            val where = "DT_0001"
            val date = "20230726"
            val site = "http://www.khoa.go.kr/api/oceangrid/tideObsPreTab/search.do?ServiceKey=".plus(key).plus("&ObsCode=").plus(where).plus("&Date=").plus(date).plus("&ResultType=json")
            val url = URL(site)
            val conn = url.openConnection()
            val input = conn.getInputStream()
            val isr = InputStreamReader(input)
            val br = BufferedReader(isr)
            var str : String? = null
            val buf = StringBuffer()
            do{
                str = br.readLine()
                if(str!=null){
                    buf.append(str)
                }
            } while (str!=null)
            // 전체가 객체로 묶여있기 때문에 객체형태로 가져옴
            val root = JSONObject(buf.toString())
            val response = root.getJSONObject("result").getJSONObject("meta")
            val item = root.getJSONObject("result").getJSONArray("data") // 객체 안에 있는 item이라는 이름의 리스트를 가져옴

            Log.d("apitest", "now")

            // 화면에 출력
            runOnUiThread {
                Log.d("apitest", "now2")
                // 페이지 수만큼 반복하여 데이터를 불러온다.
                binding = ActivityApiTestBinding.inflate(layoutInflater)
                setContentView(binding.root)
                binding.textView.append("1. 관측소 ID : ${response.getString("obs_post_id")}\n")
                binding.textView.append("2. 관측소 명 : ${response.getString("obs_post_name")}\n")
                binding.textView.append("3. 관측소 위도 : ${response.getString("obs_lat")}\n")
                binding.textView.append("4. 관측소 경도 : ${response.getString("obs_lon")}\n")
                Log.d("apitest", "now3")
                for(i in 0 until item.length()) {
                    // 쪽수 별로 데이터를 읽는다.
                    val jObject = item.getJSONObject(i)
                    binding.textView.append("조석정보 : ${jObject.getString("hl_code")}\n")
                    binding.textView.append("tph_level : ${jObject.getString("tph_level")}\n")
                    binding.textView.append("tph_time : ${jObject.getString("tph_time")}\n")
                }
            }
       }
    }
}