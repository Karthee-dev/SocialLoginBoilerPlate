package karthee.login.db.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import karthee.login.model.LoginResponse;

@Dao
public interface UserDao {
    
    @Query("SELECT * FROM user LIMIT 1")
    LiveData<LoginResponse.User> getUser();

    @Insert
    (onConflict = OnConflictStrategy.REPLACE)
    void insert(LoginResponse.User user);

    @Delete
    void delete(LoginResponse.User user);

    @Query("DELETE FROM user")
    void deleteAll();
}
