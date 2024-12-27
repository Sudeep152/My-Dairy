package com.shashank.mydairy.repository

import com.shashank.mydairy.dao.NoteDao
import com.shashank.mydairy.entity.Note
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NoteRepository {
    override suspend fun insertNote(note: Note) {
        noteDao.insert(note)
    }

    override suspend fun getNoteForDate(date: String): List<Note> {
        return noteDao.getNotesByDate(date)
    }
}