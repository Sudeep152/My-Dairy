package com.shashank.mydairy.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.shashank.mydairy.composable.HorizontalDatePicker
import com.shashank.mydairy.composable.NoteItem
import com.shashank.mydairy.composable.NotePopUp
import com.shashank.mydairy.viewmodel.NoteViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyDairyScreen(modifier: Modifier = Modifier, viewmodel: NoteViewModel = hiltViewModel()) {
    var selectedData by remember {
        mutableStateOf(LocalDate.now())
    }
    var showPopUp by remember {
        mutableStateOf(false)
    }

    val notes by viewmodel.notes.collectAsState()
    val isLoading by viewmodel.isLoading.collectAsState()

    LaunchedEffect(selectedData) {
        viewmodel.getNotesForDate(selectedData)
    }

    Scaffold(modifier = modifier, floatingActionButton = {
        FloatingActionButton(onClick = { showPopUp = true }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }) { innerPadding ->
        innerPadding.calculateTopPadding()
        Column {
            HorizontalDatePicker(
                selectedData = selectedData
            ) { date ->
                selectedData = date
                viewmodel.getNotesForDate(date)
            }
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            LazyColumn {
                items(notes) { note ->
                    NoteItem(note = note)
                }
            }
        }

        if (showPopUp) {
            NotePopUp(
                selectedDate = selectedData,
                onDismiss = { showPopUp = false }) { notes, date ->
                viewmodel.addNoteForDate(date.toString(), notes)
                showPopUp = false
            }

        }
    }

}