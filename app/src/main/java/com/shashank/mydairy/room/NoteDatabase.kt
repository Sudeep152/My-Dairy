package com.shashank.mydairy.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shashank.mydairy.dao.NoteDao
import com.shashank.mydairy.entity.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}