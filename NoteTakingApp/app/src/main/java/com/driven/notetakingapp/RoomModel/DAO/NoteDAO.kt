package com.driven.notetakingapp.RoomModel.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.driven.notetakingapp.RoomModel.Model.Note

@Dao
interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)
    @Delete
    suspend fun deleteNote(note:Note)
    @Update
    suspend fun updateNote(note:Note)

    @Query("SELECT * FROM Notes ORDER BY id DESC")
    fun getAllNotes():LiveData<List<Note>>

    @Query("SELECT *FROM Notes WHERE Title LIKE :query OR NoteBody LIKE :query")
    fun searchNote(query:String?):LiveData<List<Note>>
}