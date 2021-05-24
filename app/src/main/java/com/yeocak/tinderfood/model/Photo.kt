package com.yeocak.tinderfood.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Photos")
data class Photo(
    @PrimaryKey val photoLink: String,
    val photoValue: String
)