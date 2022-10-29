package hu.bme.aut.android.wordcard.data.collection

import androidx.room.*

@Dao
interface CollectionDao {
    @Query("SELECT * FROM collection")
    fun getAll(): List<Collection>

    @Insert
    fun insert(shoppingItems: Collection): Long

    @Update
    fun update(shoppingItem: Collection)

    @Delete
    fun deleteItem(shoppingItem: Collection)
}