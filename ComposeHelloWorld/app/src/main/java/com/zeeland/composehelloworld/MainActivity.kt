package com.zeeland.composehelloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zeeland.composehelloworld.ui.theme.ComposeHelloWorldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeHelloWorldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android.")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

@Composable
fun Greeting(name: String) {
    Surface(color = Color.Gray) {
        Text(text = "Hello $name..", modifier = Modifier.padding(24.dp))
    }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeHelloWorldTheme {
        Greeting("Zee")
    }
//    val counterState = remember { mutableStateOf(0) }
//
//    Counter(
//        count = counterState.value,
//        updateCount = { newCount ->
//            counterState.value = newCount
//        }
//    )
}