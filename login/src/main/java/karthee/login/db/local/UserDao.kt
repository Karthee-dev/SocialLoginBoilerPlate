package karthee.login.db.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import karthee.login.model.LoginResponse

@Dao
interface UserDao {

    @get:Query("SELECT * FROM user LIMIT 1")
    val user: LiveData<LoginResponse.User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: LoginResponse.User)

    @Delete
    fun delete(user: LoginResponse.User)

    @Query("DELETE FROM user")
    fun deleteAll()
}
