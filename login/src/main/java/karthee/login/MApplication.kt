package karthee.login

import android.app.Application
import android.content.Context

class MApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        context = applicationContext
    }

    companion object {
        lateinit var context: Context
    }
}