package hu.bme.aut.android.wordcard.data.collection

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WordCollection::class], version = 3)
abstract class WordCollectionDatabase : RoomDatabase() {
    abstract fun wordcollectionDao(): WordCollectionDao

    companion object {
        fun getDatabase(applicationContext: Context): WordCollectionDatabase {
            return Room.databaseBuilder(
                applicationContext,
                WordCollectionDatabase::class.java,
                "collection"
            ).fallbackToDestructiveMigration().build();
        }
    }
}