package com.example.projetoself

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projetoself.ui.theme.ProjetoSelfTheme
import com.google.accompanist.pager.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjetoSelfTheme {
                Carrossel()
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Carrossel() {
    val imagens = listOf(
        R.drawable.imagem1,
        R.drawable.imagem2,
        R.drawable.imagem3,
        R.drawable.imagem4,
    )

    val pagerState = rememberPagerState(initialPage = 0)
    var selectedPage by remember { mutableStateOf(pagerState.currentPage) }

    Box {
        HorizontalPager(
            count = imagens.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Image(
                painter = painterResource(id = imagens[page]),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }

        LaunchedEffect(pagerState.currentPage) {
            selectedPage = pagerState.currentPage
        }

        PageIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            count = imagens.size,
            activePage = selectedPage,
            activeColor = MaterialTheme.colorScheme.primary,
            inactiveColor = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    count: Int,
    activePage: Int,
    activeColor: Color,
    inactiveColor: Color
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(count) { pageIndex ->
            Icon(
                imageVector = if (pageIndex == activePage) Icons.Default.AddCircle else Icons.Default.AddCircle,
                contentDescription = null,
                tint = if (pageIndex == activePage) activeColor else inactiveColor,
                modifier = Modifier.padding(4.dp).size(12.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CarrosselPreview() {
        Carrossel()

}