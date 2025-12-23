package com.example.appnote.data.repository

import com.example.appnote.data.local.NoteDao
import com.example.appnote.data.local.NoteEntity

class NoteRepository(private val dao: NoteDao) {

    fun getNotes() = dao.getAllNotes()

    suspend fun getNote(id: Int) = dao.getNoteById(id)

    suspend fun insert(note: NoteEntity) = dao.insert(note)

    suspend fun update(note: NoteEntity) = dao.update(note)

    suspend fun delete(note: NoteEntity) = dao.delete(note) // ✅ ADD THIS
}
