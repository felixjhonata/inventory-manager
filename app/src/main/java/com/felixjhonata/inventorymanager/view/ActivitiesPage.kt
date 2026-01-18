package com.felixjhonata.inventorymanager.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.felixjhonata.inventorymanager.R
import com.felixjhonata.inventorymanager.ui.theme.InventoryManagerTheme
import com.felixjhonata.inventorymanager.viewmodel.ActivitiesPageViewModel

@Composable
private fun ActivityItem(
  activityId: String,
  activityDate: String,
  productName: String,
  amount: String,
  isIn: Boolean,
  modifier: Modifier = Modifier
) {
  Card(modifier = modifier) {
    Row(
      Modifier
        .padding(12.dp)
        .fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Card(
        modifier = Modifier
          .width(48.dp)
          .aspectRatio(1f),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
          containerColor = if (isIn) Color.Green else Color.Red
        )
      ) {
        Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
        ) {
          Text(
            if (isIn) "IN" else "OUT",
            style = TextStyle(
              fontWeight = FontWeight.Bold,
              color = if (isIn) Color.Black else Color.White
            )
          )
        }
      }

      Column(
        Modifier.weight(1f)
      ) {
        Text(
          activityId,
          style = TextStyle(
            fontSize = 12.sp,
            color = Color.Gray
          )
        )
        Text(
          productName,
          style = TextStyle(
            fontSize = 18.sp
          ),
          overflow = TextOverflow.Ellipsis,
          maxLines = 1
        )
      }

      Column(
        horizontalAlignment = Alignment.End
      ) {
        Text(
          activityDate,
          style = TextStyle(
            fontSize = 12.sp,
            color = Color.Gray
          ),
          textAlign = TextAlign.End
        )
        Text(
          amount,
          style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
          ),
          overflow = TextOverflow.Ellipsis,
          textAlign = TextAlign.End
        )
      }
    }
  }
}

@Composable
fun NoActivityPage(modifier: Modifier = Modifier) {
  Box(
    modifier = modifier, contentAlignment = Alignment.Center
  ) {
    Text(
      stringResource(R.string.no_activity),
      style = TextStyle(fontSize = 18.sp, color = Color.Gray)
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivitiesPageAppBar(modifier: Modifier = Modifier) {
  TopAppBar(
    modifier = modifier,
    title = {
      Text(
        stringResource(R.string.activities),
        style = TextStyle(
          fontWeight = FontWeight.Bold,
          fontSize = 24.sp
        )
      )
    },
    actions = {
      IconButton(
        {}
      ) {
        Icon(
          Icons.Default.Search,
          ""
        )
      }
    }
  )
}

@RequiresApi(Build.VERSION_CODES.O) @OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivitiesPage(
  modifier: Modifier = Modifier,
  viewModel: ActivitiesPageViewModel = hiltViewModel()
) {
  val lazyActivityWithProduct = viewModel.activities.collectAsLazyPagingItems()

  Scaffold(
    modifier = modifier,
    topBar = {
      ActivitiesPageAppBar()
    },
    floatingActionButton = {
      FloatingActionButton(
        {}
      ) {
        Icon(Icons.Default.Add, "")
      }
    }
  ) { innerPadding ->
    when {
      lazyActivityWithProduct.loadState.refresh is LoadState.NotLoading && lazyActivityWithProduct.itemCount <= 0 -> {
        NoActivityPage(
          Modifier.padding(innerPadding)
            .fillMaxSize()
        )
      }

      else -> {
        LazyColumn(
          Modifier
            .padding(innerPadding)
            .fillMaxWidth()
        ) {
          items(
            count = lazyActivityWithProduct.itemCount,
            key = lazyActivityWithProduct.itemKey { it.activity.activityId }) { index ->
            lazyActivityWithProduct[index]?.let {
              ActivityItem(
                it.activity.activityId,
                viewModel.formatMillisDateTime(it.activity.activityDate),
                it.product.productName,
                viewModel.formatNumber(it.activity.amount),
                isIn = it.activity.amount >= 0,
                Modifier.padding(
                  start = 24.dp, end = 24.dp, bottom = 12.dp
                )
              )
            }
          }
        }
      }
    }
  }
}

@Preview(locale = "id", showBackground = true)
@Composable
private fun ActivitiesPagePreview() {
  InventoryManagerTheme {
    ActivityItem(
      "ACT-001",
      "20 Aug 2025",
      "Product 1",
      "20x",
      false
    )
  }
}