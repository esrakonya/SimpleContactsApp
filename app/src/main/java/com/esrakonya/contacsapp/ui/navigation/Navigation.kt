package com.esrakonya.contacsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.esrakonya.contacsapp.data.model.Person
import com.esrakonya.contacsapp.ui.screen.PersonDetailScreen
import com.esrakonya.contacsapp.ui.screen.PersonRegisterScreen
import com.esrakonya.contacsapp.ui.screen.PersonScreen
import com.google.gson.Gson

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            PersonScreen(navController = navController)
        }
        composable("register") {
            PersonRegisterScreen(navController = navController)
        }
        composable("detail/{person}",
            arguments = listOf(navArgument("person") { type = NavType.StringType })
        ) {
            val json = it.arguments?.getString("person")
            val person = Gson().fromJson(json, Person::class.java)
            PersonDetailScreen(person = person, navController = navController)

        }
    }
}