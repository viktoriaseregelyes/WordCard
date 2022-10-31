package hu.bme.aut.android.wordcard.data.profile

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface ProfileDao {
    @Query("SELECT * FROM profile")
    fun getAll(): List<Profile>

    @Query("SELECT id FROM profile WHERE profile.email LIKE :email")
    fun getID(email: String): Long

    @Insert
    fun insert(profile: Profile): Long

    @Update
    fun update(profile: Profile)

    @Query("SELECT COUNT(*) FROM profile WHERE profile.email LIKE :email AND profile.password LIKE :password")
    fun getProfile(email: String, password: String): Int

    @Query("SELECT COUNT(*) FROM profile WHERE profile.email LIKE :email")
    fun getProfileWithoutPassword(email: String): Int
}