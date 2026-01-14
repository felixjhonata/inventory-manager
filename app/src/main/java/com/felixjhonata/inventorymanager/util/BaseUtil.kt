package com.felixjhonata.inventorymanager.util

import java.text.NumberFormat

object BaseUtil {
  fun formatNumber(number: Int): String {
    return NumberFormat.getInstance().format(number)
  }
}