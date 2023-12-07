package com.example.screentransition

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.screentransition.ui.theme.ScreenTransitionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScreenTransitionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}
/*
  MyApp() is the entry point of the app. It is a composable function that sets up the navigation graph.
 */
@Composable
fun MyApp(){
    val navController = rememberNavController() // rememberNavController() is a helper function that creates a NavController that can be used to navigate through the screens.
    NavHost(navController = navController, startDestination = "firstscreen"){  // NavHost is a composable that hosts the navigation graph.
        composable("firstscreen"){ // composable is a composable function that takes a route and a lambda as parameters.
            FirstScreen { // FirstScreen is a composable function that takes a lambda as a parameter.
                name ->
                navController.navigate("secondscreen/$name") // navController.navigate() is a function that navigates to the screen with the given route. name is passed as an argument to the route.
            }
        }
        composable("secondscreen/{name}"){
            val name = it.arguments?.getString("name") ?: "no name" // it.arguments?.getString("name") is a function that gets the argument passed to the route. ?: "no name" is the default value if the argument is null.
            SecondScreen(name) { // SecondScreen is a composable function that takes a lambda as a parameter and a name
                navController.navigate("firstscreen")
            }
        }
    }
}