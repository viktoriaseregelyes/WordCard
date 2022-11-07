package hu.bme.aut.android.wordcard.data.collection

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wordcollection")
data class WordCollection(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "profile_email") var profile_email: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "description") var description: String
) { }