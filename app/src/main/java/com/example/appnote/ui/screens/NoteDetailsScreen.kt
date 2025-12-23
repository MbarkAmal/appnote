package com.example.appnote.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

import com.example.appnote.data.local.NoteDatabase
import com.example.appnote.data.local.NoteEntity
import com.example.appnote.data.repository.NoteRepository
import com.example.appnote.ui.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailsScreen(
    viewModel: NoteViewModel,
    noteId: Int,
    onBack: () -> Unit

) {

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    // 🔹 Load note by ID
    LaunchedEffect(noteId) {
        val note = viewModel.getNoteById(noteId)
        note?.let {
            title = it.title
            content = it.content
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Edit Note") })
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Content") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 4
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.updateNote(
                        id = noteId ,
                        title = title,
                        content = content
                    )
                    onBack()
                }
            ) {
                Text("Save Changes")
            }
        }
    }
}
