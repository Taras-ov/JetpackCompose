package com.example.myfirstcomposeapp

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.example.myfirstcomposeapp.counter.CounterFragment


class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commit { replace(R.id.counterFragment, CounterFragment()) }
    }
}