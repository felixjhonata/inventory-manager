package com.felixjhonata.inventorymanager.view

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.felixjhonata.inventorymanager.ui.theme.InventoryManagerTheme

@Composable
fun ActivityItem(count: Int, modifier: Modifier = Modifier) {
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
        colors = CardDefaults.cardColors(containerColor = Color.Green)
      ) {
        Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
        ) {
          Text("IN", style = TextStyle(fontWeight = FontWeight.Bold))
        }
      }

      Column(
        Modifier.weight(1f)
      ) {
        Text(
          "PROD-2508-${count.toString().padStart(4, '0')}",
          style = TextStyle(
            fontSize = 12.sp,
            color = Color.Gray
          )
        )
        Text(
          "Samsung S25 Ultra",
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
          "8 Aug 2025",
          style = TextStyle(
            fontSize = 12.sp,
            color = Color.Gray
          ),
          textAlign = TextAlign.End
        )
        Text(
          "200x",
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivitiesPageAppBar(modifier: Modifier = Modifier) {
  TopAppBar(
    modifier = modifier,
    title = {
      Text(
        "Activities Page",
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivitiesPage(
  modifier: Modifier = Modifier
) {
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
    Column(Modifier.padding(innerPadding)) {
      LazyColumn(
        Modifier
          .fillMaxWidth()
      ) {
        item {
          Spacer(Modifier.height(12.dp))
        }

        item {
          Text(
            "August 2025",
            modifier = Modifier
              .padding(
                start = 24.dp,
                end = 24.dp,
                bottom = 12.dp
              ),
            style = TextStyle(
              fontWeight = FontWeight.Bold,
              fontSize = 18.sp
            )
          )
        }

        items(20) { count ->
          ActivityItem(
            count,
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

@Preview(locale = "id", showBackground = true)
@Composable
private fun ActivitiesPagePreview() {
  InventoryManagerTheme {
    ActivitiesPage()
  }
}