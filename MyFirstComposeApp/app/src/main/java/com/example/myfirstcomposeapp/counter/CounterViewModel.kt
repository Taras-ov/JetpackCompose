package com.example.myfirstcomposeapp.counter

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class CounterViewModel : ViewModel() {
    val counter = MutableStateFlow(0)
    val step = MutableStateFlow(0)
}