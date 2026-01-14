package com.felixjhonata.inventorymanager.view

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.felixjhonata.inventorymanager.R
import com.felixjhonata.inventorymanager.model.entity.Product
import com.felixjhonata.inventorymanager.model.navigation.EditProductNav
import com.felixjhonata.inventorymanager.ui.theme.InventoryManagerTheme

@Composable
fun MainPageChildren(parentNavController: NavController, modifier: Modifier = Modifier) {
  val navController = rememberNavController()
  val currentBackStackEntry by navController.currentBackStackEntryAsState()
  val currentDestination = remember(currentBackStackEntry) {
    currentBackStackEntry?.destination?.route
  }

  Scaffold(
    modifier = modifier,
    bottomBar = {
      NavigationBar {
        NavigationBarItem(
          selected = currentDestination == "activities_page",
          onClick = {
            navController.navigate("activities_page")
          },
          icon = {
            Icon(painterResource(R.drawable.history), "")
          },
          label = {
            Text(stringResource(R.string.activities))
          },
          alwaysShowLabel = true
        )

        NavigationBarItem(
          selected = currentDestination == "products_page",
          onClick = {
            navController.navigate("products_page")
          },
          icon = {
            Icon(painterResource(R.drawable.inventory), "")
          },
          label = {
            Text(stringResource(R.string.products))
          },
          alwaysShowLabel = true
        )
      }
    }
  ) { innerPadding ->
    NavHost(
      modifier = Modifier
        .padding(innerPadding)
        .consumeWindowInsets(innerPadding),
      navController = navController,
      startDestination = "activities_page"
    ) {
      composable("activities_page") {
        ActivitiesPage()
      }

      composable("products_page") {
        ProductsPage(
          {
          parentNavController.navigate("add_product_page")
          },
          { product ->
            parentNavController.navigate(EditProductNav(
              product.sku,
              product.productName,
              product.amount
            ))
          }
        )
      }
    }
  }
}

@Composable
fun MainPage(modifier: Modifier = Modifier) {
  val navController = rememberNavController()

  NavHost(
    modifier = modifier,
    navController = navController,
    startDestination = "main_page"
  ) {
    composable("main_page") {
      MainPageChildren(navController)
    }

    composable("add_product_page") {
      AddProductPage({ navController.popBackStack() })
    }

    composable<EditProductNav> { backStackEntry ->
      val navClass: EditProductNav = backStackEntry.toRoute()
      val product = Product(
        navClass.productSku,
        navClass.productName,
        navClass.amount
      )

      EditProductPage(product, { navController.popBackStack() })
    }
  }
}

@Preview
@Composable
private fun MainPagePreview() {
  InventoryManagerTheme {
    MainPage()
  }
}