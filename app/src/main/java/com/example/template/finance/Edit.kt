package com.example.template.finance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.template.R
import kotlinx.android.synthetic.main.activity_edit.*

class Edit : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        supportActionBar?.title="Edit"
        val Family_info=intent.getStringArrayListExtra("Family_info")
        Log.d("Family111122",Family_info.toString())
        Existing_family_Id.text=Family_info[0]
        Username.setText(Family_info[1])
        Amount.text=Family_info[2]
    }
}
