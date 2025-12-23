package com.example.appnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appnote.data.local.NoteDatabase
import com.example.appnote.data.repository.NoteRepository
import com.example.appnote.ui.navigation.AppNavGraph

import com.example.appnote.ui.viewmodel.NoteViewModel
import com.example.appnote.ui.viewmodel.NoteViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current

            val db = remember { NoteDatabase.getDatabase(context) }
            val repo = remember { NoteRepository(db.noteDao()) }

            val noteViewModel: NoteViewModel = viewModel(
                factory = NoteViewModelFactory(repo)
            )

            AppNavGraph(noteViewModel)
        }
        }
    }
