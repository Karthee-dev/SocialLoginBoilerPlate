package arivista.login.db

import android.os.AsyncTask
import android.util.Log
import arivista.login.db.local.AddressDao
import arivista.login.model.AddressModel

class InsertAsyncTask internal constructor(private val mAsyncTaskDao: AddressDao) : AsyncTask<AddressModel, Void, Void>() {

    override fun doInBackground(vararg params: AddressModel): Void? {
        var addedID = mAsyncTaskDao.insert(params[0])
        Log.e("user added", "Inserted ID $addedID")
        return null
    }
}