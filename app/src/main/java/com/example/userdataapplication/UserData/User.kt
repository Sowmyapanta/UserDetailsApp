package com.example.userdataapplication.UserData

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "userData")
data class User(
    @PrimaryKey(autoGenerate = true) val id : Int,
    val FirstName : String,
    val LastName  : String,
    val Age : Int,
    val Country :String,
) : Parcelable
