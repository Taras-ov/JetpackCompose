package com.example.myfirstcomposeapp.counter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class CounterFragment : Fragment() {
    private val viewModel by viewModels<CounterViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CounterViewModel() as? T ?: throw IllegalStateException()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = ComposeView(requireContext())
        view.setContent {
            val counterState = viewModel.counter.collectAsState()
            val stepState = viewModel.step.collectAsState()

            View(
                counter = counterState.value,
                step = stepState.value,
                counterSetter = { newCount -> viewModel.counter.value = newCount },
                stepSetter = { newStep -> viewModel.step.value = newStep }
            )
        }

        return view
    }
}

@Composable
fun View(
    counter: Int,
    step: Int,
    counterSetter: (Int) -> Unit,
    stepSetter: (Int) -> Unit
) {
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Button(onClick = { counterSetter(counter - step) }) {
                Text(text = "-")
            }
            TextField(
                value = counter.toString(),
                onValueChange = { newText -> newText.toIntOrNull()?.let { counterSetter(it) } },
                Modifier
                    .width(250.dp)
                    .padding(4.dp)
            )

            Button(onClick = { counterSetter(counter + step) }) {
                Text(text = "+")
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Button(onClick = { stepSetter(step - 1) }) {
                Text(text = "-")
            }
            TextField(
                value = step.toString(),
                onValueChange = { newText -> newText.toIntOrNull()?.let { stepSetter(it) } },
                Modifier
                    .width(250.dp)
                    .padding(4.dp)
            )

            Button(onClick = { stepSetter(step + 1) }) {
                Text(text = "+")
            }
        }
    }
}

@Preview
@Composable
fun PreviewMyText() {
    View(
        counter = 0,
        step = 0,
        counterSetter = { print("new count is $it") },
        stepSetter = { print("new step is $it") }
    )
}