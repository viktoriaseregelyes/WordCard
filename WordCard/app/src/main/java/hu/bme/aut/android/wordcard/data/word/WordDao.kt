package hu.bme.aut.android.wordcard.data.word

import androidx.room.*

@Dao
interface WordDao {
    @Query("SELECT * FROM word")
    fun getAll(): List<Word>

    @Insert
    fun insert(word: Word): Long

    @Update
    fun update(word: Word)

    @Delete
    fun deleteItem(word: Word)
}