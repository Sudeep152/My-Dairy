package com.shashank.mydairy.repository

import com.shashank.mydairy.entity.Note

interface NoteRepository {
    suspend fun insertNote(note: Note)
    suspend fun getNoteForDate(date:String): List<Note>
}