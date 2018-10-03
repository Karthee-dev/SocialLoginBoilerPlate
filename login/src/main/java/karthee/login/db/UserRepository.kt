package karthee.login.db

import android.arch.lifecycle.LiveData
import android.util.Log
import karthee.login.MApplication
import karthee.login.db.local.AppDatabase
import karthee.login.db.local.UserDao
import karthee.login.model.LoginRequest
import karthee.login.model.User
import karthee.login.utils.NetworkUtils
import karthee.login.utils.RxUtils
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class UserRepository {
    internal var userDao: UserDao = AppDatabase.getAppDatabase(MApplication.context).userDao()
    internal var executor: Executor = Executors.newSingleThreadExecutor()

    val user: LiveData<User>
        get() = userDao.user

    fun clearUserCached() {
        executor.execute { userDao.deleteAll() }
    }

    fun loginUser(email: String, password: String) {


        val request = LoginRequest(email, password)

        NetworkUtils.getAPIService().login(request)
                .compose(RxUtils.applySchedulers())
                .subscribe(
                        { response: User ->
                            executor.execute {
                                Log.e("api", response?.name.toString())
                                userDao.insert(response!!)
                            }
                        },
                        { e: Throwable ->
                            e.printStackTrace()
                            Log.e("api", e.toString())
                        }
                )
    }
}

