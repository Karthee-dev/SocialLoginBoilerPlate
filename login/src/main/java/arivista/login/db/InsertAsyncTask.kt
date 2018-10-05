package arivista.login.db

import android.os.AsyncTask
import arivista.login.db.local.UserDao
import arivista.login.model.User

 class InsertAsyncTask internal constructor(private val mAsyncTaskDao: UserDao) : AsyncTask<User, Void, Void>() {

        override fun doInBackground(vararg params: User): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }