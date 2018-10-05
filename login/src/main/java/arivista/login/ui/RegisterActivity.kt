package arivista.login.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import arivista.login.R
import arivista.login.ui.ui.register.RegisterFragment

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, RegisterFragment.newInstance())
                    .commitNow()
        }
    }

}
