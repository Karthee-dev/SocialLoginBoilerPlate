package arivista.login.db.remote


import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import arivista.login.MApplication
import arivista.login.db.InsertAsyncTask
import arivista.login.db.local.AppDatabase
import arivista.login.db.local.UserDao
import arivista.login.model.AddressModel
import arivista.login.model.LoginRequest
import arivista.login.model.User
import arivista.login.utils.NetworkUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
//    val address: LiveData<List<AddressModel>>

    fun clearUserCached() {
        executor.execute { userDao.deleteAll() }
    }

    @SuppressLint("CheckResult")
    fun loginUser(email: String, password: String) {


        val request = LoginRequest(email, password)

        var user1 = User()
        user1.name = email
        user1.accesstoken = "asdasd"
        user1.role = "admin"
        user1.userguid = "sadaasd"
//                    db.userDao().insert(user1)

        InsertAsyncTask(userDao).execute(user1)


    }

    fun getAddress(pincode: String): MutableLiveData<AddressModel> {
        var data = MutableLiveData<AddressModel>()


        NetworkUtils.getAPIService()!!.create(GetAddress::class.java).address(pincode, "Android_Key")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    Log.e("Result", "There are ${result.stateName} ")
                    data.value = result
                }, { error ->
                    error.printStackTrace()
                    Log.e("Result Error", "There are ${error.localizedMessage} ")

                })

        return data
    }


}

