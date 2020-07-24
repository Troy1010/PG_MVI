package com.example.pg_mvi4.layers.z_ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pg_mvi4.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        btn_1.setOnClickListener {
            textView1.text = 5.toString()
        }
        btn_2.setOnClickListener {
            textView2.text = 7.toString()
        }
    }
}