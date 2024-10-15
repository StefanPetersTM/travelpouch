package com.github.se.travelpouch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.github.se.travelpouch.ui.authentication.SignInScreen
import com.github.se.travelpouch.model.ListTravelViewModel
import com.github.se.travelpouch.ui.navigation.NavigationActions
import com.github.se.travelpouch.ui.navigation.Route
import com.github.se.travelpouch.ui.navigation.Screen
import com.github.se.travelpouch.ui.overview.AddTravelScreen
import com.github.se.travelpouch.ui.theme.SampleAppTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      SampleAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize().testTag("MainScreenContainer"),
        ) {
          TravelPouchApp()
        }
      }
    }
  }
}

@Composable
fun TravelPouchApp() {
  val navController = rememberNavController()
  val navigationActions = NavigationActions(navController)
  val listTravelViewModel: ListTravelViewModel = viewModel(factory = ListTravelViewModel.Factory)

  NavHost(navController = navController, startDestination = Route.OVERVIEW) {
    navigation(
        startDestination = Screen.ADD_TRAVEL,
        route = Route.OVERVIEW,
    ) {
      composable(Screen.AUTH) { SignInScreen(navigationActions) }
    }

    navigation(
        startDestination = Screen.GREETING,
        route = Route.GREETING,
    ) {
      composable(Screen.GREETING) { Greeting() }
      composable(Screen.ADD_TRAVEL) { AddTravelScreen(listTravelViewModel, navigationActions) }
    }
  }
}
