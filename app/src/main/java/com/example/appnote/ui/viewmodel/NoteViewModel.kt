package com.example.appnote.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import com.example.appnote.data.local.NoteEntity
import com.example.appnote.data.repository.NoteRepository

class NoteViewModel(
    private val repository: NoteRepository
) : ViewModel() {

    val notes: Flow<List<NoteEntity>> = repository.getNotes()

    fun addNote(title: String, content: String ) {
        viewModelScope.launch {
            repository.insert(
                NoteEntity(
                    title = title,
                    content = content ,

                )
            )
        }
    }

    fun updateNote(id: Int, title: String, content: String) {
        viewModelScope.launch {
            val oldNote = repository.getNote(id) ?: return@launch

            repository.update(
                oldNote.copy(
                    title = title,
                    content = content
                )
            )
        }
    }


    suspend fun getNoteById(id: Int): NoteEntity? {
        return repository.getNote(id)
    }

    fun deleteNote(note: NoteEntity) {
        viewModelScope.launch {
            repository.delete(note)
        }
    }
}
