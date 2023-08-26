package com.laioffer.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laioffer.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    private val TAG = "lifecycle"

    // create new object
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate")

        setContent { // content is UI
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
                }
            }
        }

        // networking call
    }

//    1. learning life cycle-------------------

//    override fun onStart() {
//        super.onStart()
//
//        Log.d(TAG, "onStart")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        // UI is ready
//
//        Log.d(TAG, "onResume")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d(TAG, "onPause")
//    }
//
//    override fun onStop() {
//        super.onStop()
//
//        Log.d(TAG, "onStop")
//        // UI destroyed
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d(TAG, "onDestroy")
//    }
}

//    2. learning compose layout-------------------

@Composable
fun ArtistCardBox() {
    Column {
        Text("Alfred Sisley")
        Text("3 minutes ago")
    }
}

@Preview
@Composable
fun previewArtistCardBox() {
    ArtistCardBox()
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = Modifier.background(Color.Green)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}

