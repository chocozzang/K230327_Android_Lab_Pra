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
            val site = "http://www.khoa.go.kr/api/oceangrid/DataType/search.do?ServiceKey=" + key + "&ObsCode=" + where + "&Date=" + date + "&ResultType=json"
            val url = URL(site)
            val conn = url.openConnection()
            Log.d("apitest", "connected")
            val input = conn.getInputStream()
            Log.d("apitest", "input")
            val isr = InputStreamReader(input)
            Log.d("apitest", "isr")
            val br = BufferedReader(isr)
            Log.d("apitest", "br")

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
            val response = root.getJSONObject("result").getJSONArray("meta")
            val item = root.getJSONObject("result").getJSONArray("data") // 객체 안에 있는 item이라는 이름의 리스트를 가져옴

            Log.d("apitest", response.getString(0))
            Log.d("apitest", response.getString(1))
            Log.d("apitest", response.getString(2))
            Log.d("apitest", response.getString(3))
            Log.d("apitest", response.getString(4))
            Log.d("apitest", item.getString(0))

            // 화면에 출력
            runOnUiThread {
                // 페이지 수만큼 반복하여 데이터를 불러온다.
                for(i in 0 until item.length()) {
                    // 쪽수 별로 데이터를 읽는다.
                    val jObject = item.getJSONObject(i)
                    binding.textView.append("조석정보\n")
                    binding.textView.append("1. 관측소 ID: ${ JSON_Parse(jObject,"obs_post_id")}\n")
                    binding.textView.append("2. 관측소명: ${JSON_Parse(jObject,"obs_post_name")}\n")
                    binding.textView.append("3. 관측소 위도: ${JSON_Parse(jObject,"obs_lat")}\n")
                    binding.textView.append("4. 관측소 경도: ${ JSON_Parse(jObject,"obs_lon")}\n")
                    binding.textView.append("5. 저조/고조: ${JSON_Parse(jObject,"hl_code")}\n")
                }
            }
        }

        // 함수를 통해 데이터를 불러온다.
        fun JSON_Parse(obj:JSONObject, data : String): String {

            // 원하는 정보를 불러와 리턴받고 없는 정보는 캐치하여 "없습니다."로 리턴받는다.
            return try {

                obj.getString(data)

            } catch (e: Exception) {
                "없습니다."
            }
        }
    }
}