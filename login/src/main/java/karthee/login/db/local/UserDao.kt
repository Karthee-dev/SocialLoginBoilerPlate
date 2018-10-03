package karthee.login.db.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import karthee.login.model.User

@Dao
interface UserDao {

    @get:Query("SELECT * FROM user LIMIT 1")
    val user: LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM user")
    fun deleteAll()
}
