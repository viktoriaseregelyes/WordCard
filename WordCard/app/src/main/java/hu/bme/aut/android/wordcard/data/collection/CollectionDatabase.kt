package hu.bme.aut.android.wordcard.data.collection

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Collection::class], version = 1)
abstract class CollectionDatabase : RoomDatabase() {
    abstract fun collectionDao(): CollectionDao

    companion object {
        fun getDatabase(applicationContext: Context): CollectionDatabase {
            return Room.databaseBuilder(
                applicationContext,
                CollectionDatabase::class.java,
                "collection"
            ).build();
        }
    }
}