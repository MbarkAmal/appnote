package com.example.appnote.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.*

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailsScreen(
    noteId: Int
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Note Details") })
        }
    ) { padding ->
        Text(
            text = "Note ID: $noteId",
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        )
    }
}

