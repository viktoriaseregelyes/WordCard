package hu.bme.aut.android.wordcard.data.word

import androidx.room.*
import hu.bme.aut.android.wordcard.data.collection.WordCollection

@Dao
interface WordDao {
    @Query("SELECT * FROM word WHERE word.collection LIKE :collection")
    fun getWordCollection(collection: String?): List<Word>

    @Insert
    fun insert(word: Word): Long

    @Update
    fun update(word: Word)

    @Delete
    fun deleteItem(word: Word)
}