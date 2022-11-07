package hu.bme.aut.android.wordcard.data.word

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Word::class], version = 2)
abstract class WordDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        fun getDatabase(applicationContext: Context): WordDatabase {
            return Room.databaseBuilder(
                applicationContext,
                WordDatabase::class.java,
                "word"
            ).fallbackToDestructiveMigration().build();
        }
    }
}