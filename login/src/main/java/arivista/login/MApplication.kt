package arivista.login

import android.app.Application
import android.content.Context
import arivista.login.utils.Initializer

class MApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        context = applicationContext
        Initializer.context = context.applicationContext

    }

    companion object {
        lateinit var context: Context
    }
}