package com.driven.notetakingapp.RoomModel.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.driven.notetakingapp.RoomModel.DAO.NoteDAO
import com.driven.notetakingapp.RoomModel.Model.Note

@Database(entities = [Note::class], version = 1)
abstract class Note_DataBase : RoomDatabase() {

    abstract fun getNote():NoteDAO
    companion object{
        @Volatile
        private var instance:Note_DataBase?=null
        private val lock = Any()

        operator fun invoke(context: Context) = instance?:
        synchronized(lock){
            instance?:
            createDatabase(context).also{
                instance=it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                Note_DataBase::class.java,
                "Note_DB").build()
    }
}