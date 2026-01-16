package com.felixjhonata.inventorymanager.util

import android.content.Context
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import java.text.NumberFormat

object BaseUtil {
  fun formatNumber(number: Int): String {
    return NumberFormat.getInstance().format(number)
  }

  fun startScanner(
    context: Context,
    onSuccess: ((Barcode) -> Unit) = {},
    onCancelled: (() -> Unit) = {},
    onFailed: ((Exception) -> Unit) = {}
  ) {
    val options = GmsBarcodeScannerOptions.Builder()
      .enableAutoZoom()
      .build()

    val scanner = GmsBarcodeScanning.getClient(context, options)

    scanner.startScan()
      .addOnSuccessListener(onSuccess)
      .addOnCanceledListener(onCancelled)
      .addOnFailureListener(onFailed)
  }
}