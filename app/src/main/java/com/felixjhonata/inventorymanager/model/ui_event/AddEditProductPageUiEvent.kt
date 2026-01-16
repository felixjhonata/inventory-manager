package com.felixjhonata.inventorymanager.model.ui_event

sealed interface AddEditProductPageUiEvent {
  object OnBackPressed: AddEditProductPageUiEvent
  data class ShowSnackbar(val text: String): AddEditProductPageUiEvent
}