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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailsScreen(
    noteId: Int,
    onBack: () -> Unit

) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val db = remember { NoteDatabase.getDatabase(context) }
    val repo = remember { NoteRepository(db.noteDao()) }

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    // 🔹 Load note by ID
    LaunchedEffect(noteId) {
        val note = repo.getNote(noteId)
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
                    scope.launch {
                        repo.update(
                            NoteEntity(
                                id = noteId,
                                title = title,
                                content = content
                            )
                        )
                        onBack() //  go back after save
                    }
                }
            ) {
                Text("Save Changes")
            }
        }
    }
}
