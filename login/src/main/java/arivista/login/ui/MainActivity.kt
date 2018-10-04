package arivista.login.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import arivista.login.R
import arivista.login.databinding.ActivityMainBinding

import arivista.login.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    private var mViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        mViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        // The observer updates the UI to display prefetched user details
        mViewModel!!.user.observe(this, Observer{ userResponse ->

            if (userResponse != null) {
                binding.txtWelcome.text = ("Welcome " + userResponse!!.name + " " + userResponse!!.role
                        + "\n\n" + "You are more than a " + userResponse!!.userguid)
            } else {
                logoutUser()
            }
        })
    }


    fun btnLogout(view: View) {
        logoutUser()
    }

    private fun logoutUser() {
        mViewModel!!.clearUserData()
        finish()
    }
}
