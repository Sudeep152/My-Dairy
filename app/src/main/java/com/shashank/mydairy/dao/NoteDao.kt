package com.shashank.mydairy.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.shashank.mydairy.entity.Note

@Dao
interface NoteDao {
    @Insert
    suspend fun insert(note: Note)

    @Query("Select * from Note Where date = :date")
    suspend fun getNotesByDate(date: String): List<Note>
}