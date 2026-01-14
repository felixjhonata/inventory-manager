package com.felixjhonata.inventorymanager.view.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.felixjhonata.inventorymanager.R
import com.felixjhonata.inventorymanager.model.ui_model.AddEditProductPageUiModel
import com.felixjhonata.inventorymanager.viewmodel.AddEditProductPageViewModel
import com.felixjhonata.inventorymanager.viewmodel.EditProductPageViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddEditProductPageAppBar(
  viewModel: AddEditProductPageViewModel,
  isEditPage: Boolean,
  modifier: Modifier = Modifier
) {
  TopAppBar(
    modifier = modifier,
    navigationIcon = {
      IconButton(viewModel::onBackPressed) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, "")
      }
    },
    title = {
      Text(
        stringResource(if (isEditPage) R.string.edit_product else R.string.add_product),
        style = TextStyle(
          fontSize = 24.sp,
          fontWeight = FontWeight.Bold
        )
      )
    },
    actions = {
      if (isEditPage) {
        IconButton({
          (viewModel as? EditProductPageViewModel)?.onDeleteProduct()
        }) {
          Icon(Icons.Default.Delete, "")
        }
      }
    }
  )
}

@Composable
fun AddEditProductScreen(
  uiModel: AddEditProductPageUiModel,
  viewModel: AddEditProductPageViewModel,
  modifier: Modifier = Modifier
) {
  val isEditPage = viewModel is EditProductPageViewModel

  Scaffold(
    modifier = modifier,
    topBar = {
      AddEditProductPageAppBar(
        viewModel,
        isEditPage
      )
    }
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .padding(innerPadding)
        .padding(24.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      TextField(
        uiModel.productSku,
        viewModel::onProductSkuValueChange,
        modifier = Modifier
          .fillMaxWidth(),
        label = {
          Text(stringResource(R.string.product_sku))
        },
        isError = uiModel.isProductSkuError,
        supportingText = {
          Text(uiModel.productSkuErrorMsg)
        }
      )

      TextField(
        uiModel.productName,
        viewModel::onProductNameValueChange,
        modifier = Modifier
          .fillMaxWidth(),
        label = {
          Text(stringResource(R.string.product_name))
        },
        isError = uiModel.isProductNameError,
        supportingText = {
          Text(uiModel.productNameErrorMsg)
        }
      )

      TextField(
        uiModel.amount,
        viewModel::onAmountValueChange,
        modifier = Modifier
          .fillMaxWidth(),
        label = {
          Text(stringResource(R.string.amount))
        },
        isError = uiModel.isAmountError,
        supportingText = {
          Text(uiModel.amountErrorMsg)
        }
      )

      Spacer(Modifier.weight(1f))

      Button(
        viewModel::onSubmitButtonClick,
        modifier = Modifier.fillMaxWidth()
      ) {
        Text(stringResource(if (isEditPage) R.string.edit_product else R.string.add_product))
      }
    }
  }
}