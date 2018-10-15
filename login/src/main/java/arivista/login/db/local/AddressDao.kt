package arivista.login.db.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import arivista.login.model.AddressModel

@Dao
interface AddressDao {

    @get:Query("SELECT * FROM addressmodel LIMIT 1")
    val addressmodel: LiveData<AddressModel>

    @get:Query("SELECT * FROM addressmodel")
    val addressmodellist: LiveData<List<AddressModel>>


    @Query("SELECT * FROM addressmodel WHERE email = :email and password = :password ")
    fun getUser(email: String, password: String): LiveData<AddressModel>

    @Query("SELECT count(*) FROM addressmodel WHERE email = :email and password = :password ")
    fun isUserExists(email: String, password: String): LiveData<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(addressmodel: AddressModel): Long

//    @Delete
//    fun delete(addressmodel: AddressModel)
//
//    @Query("DELETE FROM addressmodel")
//    fun deleteAll()
}
