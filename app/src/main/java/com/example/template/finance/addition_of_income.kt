package com.example.template.finance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.template.R
import com.example.template.post
import kotlinx.android.synthetic.main.activity_addition_of_income.*
import org.json.JSONObject


class addition_of_income : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addition_of_income)
        supportActionBar?.title="Update"
        val jsondata=JSONObject()
        jsondata.put("fid",123)
        jsondata.put("amount",123)
        jsondata.put("timestamp","2019-09-12 17:17:17")
        jsondata.put("duration",10)
        Update_button.setOnClickListener {
            Log.d("jsondata",jsondata.toString())
            post("http://192.168.43.149:8000/cbo/payments/",jsondata.toString())
 
        }

    }
}
