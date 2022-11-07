package hu.bme.aut.android.wordcard.data.word

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word")
data class Word(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "collection") var collection: String? = null,
    @ColumnInfo(name = "first_language") var first_language: String,
    @ColumnInfo(name = "second_language") var second_language: String
) { }