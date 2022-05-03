package com.test.sukanyatest.data.models.quotes

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "quote")
data class Result(
    @PrimaryKey(autoGenerate = true)
    val quoteId:Int?,
    val _id: String?,
    val author: String?="no author",
    val authorSlug: String?,
    val content: String?,
    val dateAdded: String?,
    val dateModified: String?,
    val length: Int?,
    val tags: List<String>?
)