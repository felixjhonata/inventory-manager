package com.felixjhonata.inventorymanager.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.felixjhonata.inventorymanager.model.entity.Product
import com.felixjhonata.inventorymanager.model.ui_event.AddEditProductPageUiEvent
import com.felixjhonata.inventorymanager.view.component.AddEditProductScreen
import com.felixjhonata.inventorymanager.viewmodel.EditProductPageViewModel

@Composable
fun EditProductPage(
  product: Product,
  onBackPressed: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: EditProductPageViewModel = hiltViewModel()
) {

  LaunchedEffect(Unit) {
    viewModel.initializeProduct(product)

    viewModel.uiEvent.collect { event ->
      when (event) {
        AddEditProductPageUiEvent.OnBackPressed -> {
          onBackPressed()
        }
      }
    }
  }

  val uiModel by viewModel.uiModel.collectAsState()

  AddEditProductScreen(
    uiModel,
    viewModel,
    modifier
  )
}