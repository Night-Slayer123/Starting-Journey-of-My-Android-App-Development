package com.driven.notetakingapp.RoomModel.Model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Notes")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo("Title")
    val noteTitle:String,
    @ColumnInfo("NoteBody")
    val noteBody:String
) : Parcelable
