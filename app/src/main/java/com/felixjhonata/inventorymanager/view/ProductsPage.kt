package com.felixjhonata.inventorymanager.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.felixjhonata.inventorymanager.R
import com.felixjhonata.inventorymanager.model.entity.Product
import com.felixjhonata.inventorymanager.ui.theme.InventoryManagerTheme
import com.felixjhonata.inventorymanager.util.BaseUtil
import com.felixjhonata.inventorymanager.viewmodel.ProductsPageViewModel

@Composable
private fun ProductItem(
  product: Product,
  onProductClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier,
    onClick = onProductClick
  ) {
    Row(
      Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically
    ) {
      Column(
        Modifier.weight(1f)
      ) {
        Text(
          product.sku, style = TextStyle(
            fontSize = 12.sp, color = Color.Gray
          )
        )
        Text(
          product.productName, style = TextStyle(
            fontSize = 18.sp
          )
        )
      }

      Text(
        BaseUtil.formatNumber(product.amount), style = TextStyle(
          fontSize = 18.sp, fontWeight = FontWeight.Bold
        )
      )
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductsPageAppBar(modifier: Modifier = Modifier) {
  TopAppBar(
    modifier = modifier,
    title = {
      Text(
        stringResource(R.string.products), style = TextStyle(
          fontSize = 24.sp, fontWeight = FontWeight.Bold
        )
      )
    },
  )
}

@Composable
private fun NoProductView(modifier: Modifier = Modifier) {
  Box(
    modifier = modifier, contentAlignment = Alignment.Center
  ) {
    Text(
      stringResource(R.string.no_product), style = TextStyle(
        fontSize = 18.sp, color = Color.Gray
      )
    )
  }
}

@Composable
fun ProductsPage(
  onAddProductButtonClick: () -> Unit,
  onProductClick: (Product) -> Unit,
  modifier: Modifier = Modifier,
  viewModel: ProductsPageViewModel = hiltViewModel()
) {
  val lazyProductItems = viewModel.products.collectAsLazyPagingItems()

  Scaffold(modifier = modifier, topBar = {
    ProductsPageAppBar()
  }, floatingActionButton = {
    FloatingActionButton(onAddProductButtonClick) {
      Icon(Icons.Default.Add, "")
    }
  }) { innerPadding ->
    when {
      lazyProductItems.loadState.refresh is LoadState.NotLoading
          && lazyProductItems.itemCount <= 0 -> {
        NoProductView(
          Modifier
            .padding(innerPadding)
            .fillMaxSize()
        )
      }
      else -> {
        LazyColumn(
          modifier = Modifier.padding(innerPadding)
        ) {
          items(
            lazyProductItems.itemCount,
            key = lazyProductItems.itemKey { it.sku }
          ) { index ->
            val product = lazyProductItems[index]
            product?.let {
              ProductItem(
                it,
                { onProductClick(it) },
                Modifier.padding(
                  start = 24.dp,
                  end = 24.dp,
                  bottom = 12.dp
                )
              )
            }
          }
        }
      }
    }
  }
}

@Preview @Composable private fun ProductsPagePreview() {
  InventoryManagerTheme {
    ProductsPage({}, {})
  }
}