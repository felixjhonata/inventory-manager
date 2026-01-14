package com.felixjhonata.inventorymanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.felixjhonata.inventorymanager.ui.theme.InventoryManagerTheme
import com.felixjhonata.inventorymanager.view.MainPage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      InventoryManagerTheme {
        MainPage()
      }
    }
  }
}