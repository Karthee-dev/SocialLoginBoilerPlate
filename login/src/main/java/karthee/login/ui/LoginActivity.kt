package karthee.login.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast

import karthee.login.R
import karthee.login.databinding.ActivityLoginBinding
import karthee.login.model.User
import karthee.login.viewmodel.LoginViewModel

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {
    // UI references

    private var mViewModel: LoginViewModel? = null
    //    ActivityLoginBinding binding;
    val userLiveData: MutableLiveData<User> = MutableLiveData()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)

        // Get the ViewModel.
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding.viewModel = mViewModel




        //        // The observer updates the UI when Login Operation is successful
        mViewModel!!.user.observe(this, Observer{ user ->
            if (user != null) {
                Toast.makeText(this@LoginActivity, "Login success", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                Log.d("LoginActivity", "value user is null")
                // Show ERROR
            }
        })


        binding.btnLogin.setOnClickListener { view: View ->
            mViewModel!!.onBtnLoginClick()

        }
    }


}

private fun <T> LiveData<T>.observe(loginActivity: LoginActivity, observer: Observer<User>) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

