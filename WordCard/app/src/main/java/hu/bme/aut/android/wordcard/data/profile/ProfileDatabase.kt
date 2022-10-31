package hu.bme.aut.android.wordcard.data.profile

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Profile::class], version = 8)
abstract class ProfileDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao

    companion object {
        fun getDatabase(applicationContext: Context): ProfileDatabase {
            return Room.databaseBuilder(
                applicationContext,
                ProfileDatabase::class.java,
                "profile"
            ).fallbackToDestructiveMigration().build();
        }
    }
}