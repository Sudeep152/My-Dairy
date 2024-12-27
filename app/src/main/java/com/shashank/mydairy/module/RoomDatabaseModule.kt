package com.shashank.mydairy.module

import android.content.Context
import androidx.room.Room
import com.shashank.mydairy.dao.NoteDao
import com.shashank.mydairy.repository.NoteRepository
import com.shashank.mydairy.repository.NoteRepositoryImpl
import com.shashank.mydairy.room.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext appContext: Context): NoteDatabase {
        return Room.databaseBuilder(appContext, NoteDatabase::class.java, "note_database").build()

    }

    @Singleton
    @Provides
    fun provideDao(noteDatabase: NoteDatabase) = noteDatabase.noteDao()


    @Provides
    @Singleton
    fun provideRepository(noteDao: NoteDao): NoteRepository {
        return NoteRepositoryImpl(noteDao)
    }


}