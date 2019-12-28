package com.example.template.finance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.template.R

class Edit : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        supportActionBar?.title="Edit"
    }
}
