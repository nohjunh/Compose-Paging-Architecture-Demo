package com.example.compose_paging_demo.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.compose_paging_demo.domain.model.Beer
import timber.log.Timber

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val beers = viewModel.beers.collectAsLazyPagingItems()

    LogLoadStateErrors(beers.loadState)

    Column(modifier = Modifier.fillMaxSize()) {
        DeleteDataButton(onClick = { viewModel.deleteAllBeers() })

        when (beers.loadState.refresh) {
            is LoadState.Loading -> LoadingIndicator()
            else -> BeersList(beers)
        }
    }
}

@Composable
fun LogLoadStateErrors(loadState: CombinedLoadStates) {
    LaunchedEffect(loadState) {
        if (loadState.refresh is LoadState.Error) {
            Timber.tag("loadState Error").e("${loadState.refresh as LoadState.Error}")
        }
    }
}

@Composable
fun DeleteDataButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Text(text = "데이터 삭제")
    }
}

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun BeersList(beers: LazyPagingItems<Beer>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(count = beers.itemCount) { index ->
            val item = beers[index]
            if (item != null) {
                BeerItem(
                    beer = item,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        item {
            if (beers.loadState.append is LoadState.Loading) {
                CircularProgressIndicator()
            }
        }
    }
}
