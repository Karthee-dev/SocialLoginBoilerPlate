package arivista.login.ui


import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import arivista.login.R
import arivista.login.databinding.ActivityLoginBinding
import arivista.login.viewmodel.LoginViewModel

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


        binding.btnLogin.setOnClickListener {
            if (mViewModel!!.onBtnLoginClick()) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        binding.register.setOnClickListener {
            if (mViewModel!!.onBtnLoginClick()) {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}


