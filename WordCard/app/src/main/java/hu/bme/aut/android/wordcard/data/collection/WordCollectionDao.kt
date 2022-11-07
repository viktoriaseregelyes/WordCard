package hu.bme.aut.android.wordcard.data.collection

import androidx.room.*

@Dao
interface WordCollectionDao {
    @Query("SELECT * FROM wordcollection WHERE wordcollection.profile_email LIKE :email")
    fun getProfileCollection(email: String?): List<WordCollection>

    @Insert
    fun insert(wordcollection: WordCollection): Long

    @Update
    fun update(wordcollection: WordCollection)

    @Delete
    fun deleteItem(wordcollection: WordCollection)
}