package karthee.login.db

import android.arch.lifecycle.LiveData
import karthee.login.MApplication
import karthee.login.db.local.AppDatabase
import karthee.login.db.local.UserDao
import karthee.login.model.LoginRequest
import karthee.login.model.LoginResponse
import karthee.login.utils.NetworkUtils
import karthee.login.utils.RxUtils
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class UserRepository {
    internal var userDao: UserDao
    internal var executor: Executor

    val user: LiveData<LoginResponse.User>
        get() = userDao.user

    init {
        this.userDao = AppDatabase.getAppDatabase(MApplication.context).userDao()
        executor = Executors.newSingleThreadExecutor()
    }

    fun clearUserCached() {
        executor.execute { userDao.deleteAll() }
    }

    fun loginUser(email: String, password: String) {


        val request = LoginRequest(email, password)

        NetworkUtils.getAPIService().login(request)
                .compose(RxUtils.applySchedulers())
                .subscribe(
                        { response: LoginResponse -> executor.execute { userDao.insert(response.user!!) } },
                        { e: Throwable -> e.printStackTrace() }
                )
    }
}

