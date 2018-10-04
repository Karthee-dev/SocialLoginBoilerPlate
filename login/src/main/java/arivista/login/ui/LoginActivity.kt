package arivista.login.ui


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import arivista.login.R
import arivista.login.databinding.ActivityLoginBinding
import arivista.login.viewmodel.LoginViewModel
import com.google.gson.Gson

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {
    // UI references

    private var mViewModel: LoginViewModel? = null
    //    ActivityLoginBinding binding;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)

        // Get the ViewModel.
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding.viewModel = mViewModel


        // The observer updates the UI when Login Operation is successful
        mViewModel!!.user.observe(this, Observer { user ->
            if (user != null) {
                Log.e("LoginActivity", Gson().toJson(user))

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


