package hu.bme.aut.android.wordcard.data.profile

import androidx.room.*

@Entity(tableName = "profile", indices = [Index(value = arrayOf("email"), unique = true)])
data class Profile(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "password") var password: String
) { }