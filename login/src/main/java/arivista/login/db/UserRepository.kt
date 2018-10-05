package arivista.login.db.remote


import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import arivista.login.MApplication
import arivista.login.db.InsertAsyncTask
import arivista.login.db.local.AppDatabase
import arivista.login.db.local.UserDao
import arivista.login.model.LoginRequest
import arivista.login.model.User
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class UserRepository {
    var appDatabase = AppDatabase.getAppDatabase(MApplication.context)
    private var userDao: UserDao = appDatabase.userDao()
    private var executor: Executor = Executors.newSingleThreadExecutor()

    val user: LiveData<User>
        get() = userDao.user

    val userlist: LiveData<List<User>>
        get() = userDao.userlist

    fun clearUserCached() {
        executor.execute { userDao.deleteAll() }
    }

    @SuppressLint("CheckResult")
    fun loginUser(email: String, password: String) {


        val request = LoginRequest(email, password)

        var user1 = User()
        user1.name = email
        user1.accesstoken= "asdasd"
        user1.role="admin"
        user1.userguid="sadaasd"
//                    db.userDao().insert(user1)

        InsertAsyncTask(userDao).execute(user1)



    }
}

