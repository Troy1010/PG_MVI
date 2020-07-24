package com.example.pg_mvi4.layers.z_ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pg_mvi4.R
import com.example.pg_mvi4.models.Intent
import com.example.tmcommonkotlin.logz
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val intentStream = PublishSubject.create<Intent>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupClickListeners()
        setupObservers()
    }

    private fun setupObservers() {
        intentStream.subscribe {
            logz("intentStream`it:$it")
        }
    }

    private fun setupClickListeners() {
        btn_1.setOnClickListener {
            intentStream.onNext(Intent.BumpXCount1)
        }
        btn_2.setOnClickListener {
            intentStream.onNext(Intent.BumpXCount2)
        }
    }
}