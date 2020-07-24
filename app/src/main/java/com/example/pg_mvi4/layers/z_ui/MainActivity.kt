package com.example.pg_mvi4.layers.z_ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.observe
import com.example.pg_mvi4.R
import com.example.pg_mvi4.layers.data_layer.Repo
import com.example.pg_mvi4.layers.domain.XCountDomainObj
import com.example.pg_mvi4.layers.view_models.XCountVM
import com.example.pg_mvi4.models.Intent
import com.example.pg_mvi4.models.XCountModelResult
import com.example.tmcommonkotlin.easyToast
import com.example.tmcommonkotlin.logz
import com.example.tmcommonkotlin.vmFactoryFactory
import com.trello.rxlifecycle4.android.lifecycle.kotlin.bindToLifecycle
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val intentStream = PublishSubject.create<Intent>()
    val xCountVM: XCountVM by viewModels { vmFactoryFactory { XCountVM(XCountDomainObj()) } }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        xCountVM.mergeIntentStream(intentStream)
        setupClickListeners()
        setupObservers()

        // debug
        Repo.readProducts().bindToLifecycle(this).subscribe {
            logz("Products size:${it.size}")
        }
    }

    private fun setupObservers() {
        xCountVM.stateStream.observe(this) {
            textView1.text = it.xcount1
            textView2.text = it.xcount2
        }
        xCountVM.eventStream.bindToLifecycle(this).subscribe {
            when (it) {
                is XCountModelResult.newSum -> easyToast("Sum:${it.sumValue}")
            }
        }
    }

    private fun setupClickListeners() {
        btn_1.setOnClickListener {
            intentStream.onNext(Intent.BumpXCount1)
        }
        btn_2.setOnClickListener {
            intentStream.onNext(Intent.BumpXCount2)
        }
        btn_toastSum.setOnClickListener {
            intentStream.onNext(Intent.ToastSum)
        }
    }
}