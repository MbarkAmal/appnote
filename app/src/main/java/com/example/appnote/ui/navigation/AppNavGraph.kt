package com.example.appnote.ui.navigation

import androidx.compose.runtime.Composable
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
                viewModel = noteViewModel,
                onAddNote = {
                    navController.navigate(Routes.ADD_NOTE)
                },
                onNoteClick = { noteId ->
                    navController.navigate("${Routes.NOTE_DETAILS}/$noteId")
                }
            )
        }

        composable(Routes.ADD_NOTE) {
            AddNoteScreen(
                viewModel = noteViewModel,
                onSave = {
                    navController.popBackStack()
                }
            )
        }

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
