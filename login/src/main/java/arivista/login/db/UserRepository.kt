package arivista.login.db.remote


import android.arch.lifecycle.LiveData
import android.util.Log
import arivista.login.MApplication
import arivista.login.db.local.AppDatabase
import arivista.login.db.local.UserDao
import arivista.login.model.LoginRequest
import arivista.login.model.User
import arivista.login.utils.NetworkUtils
import arivista.login.utils.RxUtils
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

