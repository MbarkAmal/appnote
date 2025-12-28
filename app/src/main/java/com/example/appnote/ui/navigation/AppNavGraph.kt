package com.example.appnote.ui.navigation

import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appnote.ui.screens.*
import com.example.appnote.ui.viewmodel.NoteViewModel

@Composable
fun AppNavGraph(
    noteViewModel: NoteViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {

        // Splash Screen
        composable(Routes.SPLASH) {
            SplashScreen(
                onLoginClick = { navController.navigate(Routes.LOGIN) },
                onSignUpClick = { navController.navigate(Routes.SIGNUP) }
            )
        }


        // Login Screen
        composable(Routes.LOGIN) {
            LoginScreen(
                onLogin = { pin ->
                    // TODO: check PIN
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true } // remove Login from back stack
                    }
                },

                )
        }

        // SignUp Screen
        composable(Routes.SIGNUP) {
            SignUpScreen(
                onSignUp = { name, email, pin ->
                    // TODO: save user info in Room / SharedPreferences
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.SIGNUP) { inclusive = true } // remove SignUp from back stack
                    }
                }
            )
        }

        // Home Screen
        composable(Routes.HOME) {
            HomeScreen(
                navController = navController,
                viewModel = noteViewModel,
                onAddNote = {
                    navController.navigate(Routes.ADD_NOTE)
                },
                onNoteClick = { id ->
                    navController.navigate("${Routes.NOTE_DETAILS}/$id")
                }
            )
        }




        // add note
// add note
        composable(Routes.ADD_NOTE) {
            AddNoteScreen(
                viewModel = noteViewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // setting page
        composable(Routes.Settings) {
            SettingsScreen(navController = navController)
        }


        // notte detail page
        composable("${Routes.NOTE_DETAILS}/{noteId}") { backStackEntry ->
            val id = backStackEntry.arguments
                ?.getString("noteId")
                ?.toInt() ?: 0

            NoteDetailsScreen(
                viewModel = noteViewModel,
                noteId = id,
                onBack = { navController.popBackStack() }
            )
        }
    }
}