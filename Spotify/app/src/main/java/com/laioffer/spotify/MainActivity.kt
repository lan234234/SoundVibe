package com.laioffer.spotify

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import coil.compose.AsyncImage
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.laioffer.spotify.datamodel.Section
import com.laioffer.spotify.network.NetworkApi
import com.laioffer.spotify.network.NetworkModule
import com.laioffer.spotify.ui.theme.SpotifyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject

// customized extend AppCompatActivity
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // field injection
    @Inject
    lateinit var api: NetworkApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // find the bottom nav view
        val navView = findViewById<BottomNavigationView>(R.id.nav_view)

        // get navController
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController // get

        navController.setGraph(R.navigation.nav_graph)

        NavigationUI.setupWithNavController(navView, navController)

        // https://stackoverflow.com/questions/70703505/navigationui-not-working-correctly-with-bottom-navigation-view-implementation
//        navView.setOnItemSelectedListener{
//            NavigationUI.onNavDestinationSelected(it, navController)
//            navController.popBackStack(it.itemId, inclusive = false)
//            true
//        }

        // Coroutine
        GlobalScope.launch(Dispatchers.IO) {
            val call: Call<List<Section>> = api.getHomeFeed()
            val feed = call.execute().body()
            Log.d("Network", feed.toString())
        }
    }
}

@Composable
fun LoadSection(text: String) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            color = Color.White
        )
    }
}

@Composable
fun AlbumCover() {
    Column {
        Box(modifier = Modifier.size(160.dp)) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
                model = "https://upload.wikimedia.org/wikipedia/en/d/d1/Stillfantasy.jpg",
                contentDescription = null
            )

            Text(
                text = "Still Fantasy",
                color = Color.White,
                modifier = Modifier.padding(bottom = 4.dp, start = 4.dp).align(Alignment.BottomStart)
            )
        }

        Text(
            text = "Jay Chou",
            modifier = Modifier.padding(start = 4.dp, top = 4.dp),
            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
            color = Color.LightGray,
        )
    }
}

@Preview
@Composable
fun PreviewAlbumCover() {
    SpotifyTheme {
        Surface {
            AlbumCover()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SpotifyTheme {
        Surface {
            LoadSection("Screen is loading")
        }
    }
}