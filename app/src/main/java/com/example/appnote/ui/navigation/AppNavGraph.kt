package com.example.appnote.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appnote.ui.screens.*

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {

        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.HOME) {
            HomeScreen(
                onAddNote = {
                    navController.navigate(Routes.ADD_NOTE)
                },
                onNoteClick = { noteId ->
                    navController.navigate("${Routes.NOTE_DETAILS}/$noteId")
                }
            )
        }

        composable("${Routes.NOTE_DETAILS}/{noteId}") {
            val id = it.arguments?.getString("noteId")!!.toInt()
            NoteDetailsScreen(id)
        }


        composable(Routes.ADD_NOTE) {
            AddNoteScreen(
                onSave = {
                    navController.popBackStack()
                }
            )
        }
    }
}
