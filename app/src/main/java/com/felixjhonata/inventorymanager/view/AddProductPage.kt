package com.felixjhonata.inventorymanager.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.felixjhonata.inventorymanager.model.ui_event.AddEditProductPageUiEvent
import com.felixjhonata.inventorymanager.view.component.AddEditProductScreen
import com.felixjhonata.inventorymanager.viewmodel.AddProductPageViewModel

@Composable
fun AddProductPage(
  showSnackbar: (String) -> Unit,
  onBackPressed: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: AddProductPageViewModel = hiltViewModel()
) {
  LaunchedEffect(Unit) {
    viewModel.uiEvent.collect { uiEvent ->
      when(uiEvent) {
        AddEditProductPageUiEvent.OnBackPressed -> {
          onBackPressed()
        }

        is AddEditProductPageUiEvent.ShowSnackbar -> {
          showSnackbar(uiEvent.text)
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