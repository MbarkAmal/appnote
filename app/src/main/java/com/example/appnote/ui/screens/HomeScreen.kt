package com.example.appnote.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

import com.example.appnote.data.local.NoteDatabase
import com.example.appnote.data.repository.NoteRepository
import com.example.appnote.data.local.NoteEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onAddNote: () -> Unit,
    onNoteClick: (Int) -> Unit
) {

    val context = LocalContext.current
    val db = remember { NoteDatabase.getDatabase(context) }
    val repo = remember { NoteRepository(db.noteDao()) }

    val notes by repo.getNotes().collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Notes") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddNote) {
                Icon(Icons.Default.Add, contentDescription = "Add Note")
            }
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(notes) { note: NoteEntity ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onNoteClick(note.id) // ✅ FIXED
                        }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = note.title,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Row {
                                IconButton(onClick = { /* Edit later */ }) {
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = "Edit"
                                    )
                                }
                                IconButton(onClick = { /* Delete later */ }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete"
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))
                        Text(note.content)
                    }
                }
            }
        }
    }
}
