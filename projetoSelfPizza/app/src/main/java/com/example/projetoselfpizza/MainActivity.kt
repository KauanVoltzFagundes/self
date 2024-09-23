package com.example.projetoselfpizza

import Item
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.projetoselfpizza.ui.theme.ProjetoSelfPizzaTheme
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.StyledPlayerView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import coil.compose.rememberImagePainter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        // Make the status bar disappear (full screen)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )

        val dbHelper = ItemDatabaseHelper(this)

        // Faz a requisição para buscar os itens

        RetrofitInstance.api.getUsers().enqueue(object : Callback<List<Item>> {
            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {

                if (response.isSuccessful) {
                    val items = response.body() ?: emptyList()

                    // Atualiza o banco de dados com os itens retornados
                    dbHelper.updateTableWithItems(items)

                    // Atualiza a interface com os novos itens
                    setContent {
                        ProjetoSelfPizzaTheme {
                            primeiraTela(dbHelper)
                        }
                    }
                } else {

                    setContent {
                        ProjetoSelfPizzaTheme {
                            primeiraTela(dbHelper)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
              println("aqui" + t)
                setContent {
                    ProjetoSelfPizzaTheme {
                        primeiraTela(dbHelper)
                    }
                }
            }
        })
    }

}



@Composable
fun primeiraTela(dbHelper: ItemDatabaseHelper) {
    val items by remember { mutableStateOf(dbHelper.getAllItems()) }
    println(items)
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Carousel(mediaList = items.map { it.url })
    }
}

@Composable
fun VideoPlayer(context: Context, videoUrl: String) {
    val exoPlayer = remember {
        SimpleExoPlayer.Builder(context).build().also { player ->
            val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
            player.setMediaItem(mediaItem)
            player.prepare()
            player.playWhenReady = true
        }
    }

    DisposableEffect(exoPlayer) {
        onDispose {
            exoPlayer.release()
        }
    }

    AndroidView(
        factory = { ctx ->
            StyledPlayerView(ctx).apply {
                player = exoPlayer
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Carousel(mediaList: List<String>, scrollDelay: Long = 2000) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

//    LaunchedEffect(listState) {
//        while (true) {
//            delay(scrollDelay)
//            coroutineScope.launch {
//                val nextItem = (listState.firstVisibleItemIndex + 1) % mediaList.size
//                listState.animateScrollToItem(nextItem)
//            }
//        }
//    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyRow(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .pointerInput(Unit) {}
        ) {
            items(mediaList) { url ->
                Box(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .fillParentMaxHeight()
                ) {
                    Image(
                        painter = rememberImagePainter(url),
                        contentDescription = "Imagem do carrossel",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun primeiraTelaPreview() {
    ProjetoSelfPizzaTheme {
        primeiraTela(dbHelper = ItemDatabaseHelper(LocalContext.current))
    }
}
