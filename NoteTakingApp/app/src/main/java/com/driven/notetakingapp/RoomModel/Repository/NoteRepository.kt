package com.driven.notetakingapp.RoomModel.Repository

import com.driven.notetakingapp.RoomModel.DataBase.Note_DataBase
import com.driven.notetakingapp.RoomModel.Model.Note

class NoteRepository(val database:Note_DataBase) {
    suspend fun insertNote(note: Note) = database.getNote().insertNote(note)
    suspend fun updateNote(note: Note) = database.getNote().updateNote(note)
    suspend fun deleteNote(note: Note) = database.getNote().deleteNote(note)

    fun getAllNotes() = database.getNote().getAllNotes()
    fun searchNote(query:String?) = database.getNote().searchNote(query)

}