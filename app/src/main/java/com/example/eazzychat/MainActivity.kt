package com.example.eazzychat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eazzychat.Screens.LoginScreen
import com.example.eazzychat.Screens.SignUpScreen
import com.example.eazzychat.ui.theme.EazzyChatTheme

sealed class DestinationScreen(var route: String){
    object SignUp : DestinationScreen(route = "SignUp")
    object Login : DestinationScreen(route = "Login")
    object Profile : DestinationScreen(route = "profile")
    object ChatList : DestinationScreen(route = "ChatList")
    object SingleChat : DestinationScreen(route = "singleChat/{chatId}"){
        fun createRoute(id : String) = "singleChat/$id"
    }

    object StatusList : DestinationScreen(route = "StatusList")
    object SingleStatus : DestinationScreen(route = "singleStatus/{userId}"){
        fun createRoute(userId : String) = "singleStatus/$userId"
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EazzyChatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    chatAppNavigation()
                }
            }
        }
    }

    @Composable
    fun chatAppNavigation() {
        val navController = rememberNavController()
        var vm = hiltViewModel<ECViewModel>()

        NavHost(navController = navController, startDestination = DestinationScreen.SignUp.route){
            composable(DestinationScreen.SignUp.route){
                SignUpScreen(navController, vm)
            }
            composable(DestinationScreen.Login.route){
                LoginScreen()
            }
        }
    }
}
