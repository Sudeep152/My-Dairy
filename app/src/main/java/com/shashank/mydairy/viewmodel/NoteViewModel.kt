package com.shashank.mydairy.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shashank.mydairy.entity.Note
import com.shashank.mydairy.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    @RequiresApi(Build.VERSION_CODES.O)
    fun addNoteForDate(date: String, note: String) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.insertNote(Note(date = date, content = note))
            getNotesForDate(LocalDate.parse(date))
            _isLoading.value = false
        }
    }

    fun getNotesForDate(date: LocalDate) {
        viewModelScope.launch {
            _notes.value = repository.getNoteForDate(date.toString())
        }
    }
}