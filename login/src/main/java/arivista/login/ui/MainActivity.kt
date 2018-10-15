package arivista.login.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
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
        binding.viewModel = mViewModel

        mViewModel!!.user.observe(this, Observer { userResponse ->

            if (userResponse != null) {
                binding.txtWelcome.text = ("Welcome " + userResponse!!.email)
            } else {
                logoutUser()
            }
        })
    }

    fun btnLogout(view: View) {
        logoutUser()
    }

    private fun logoutUser() {
//        mViewModel!!.clearUserData()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
