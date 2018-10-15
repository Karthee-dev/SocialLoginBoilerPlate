package arivista.login.ui


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import arivista.login.R
import arivista.login.databinding.ActivityLoginBinding
import arivista.login.viewmodel.LoginViewModel

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {

    private var mViewModel: LoginViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)

        // Get the ViewModel.
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding.viewModel = mViewModel

        mViewModel!!.isSuccess.observe(this, Observer {
            if (it!!) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        })

        binding.edtEmail.addTextChangedListener(TextChange())
        binding.edtPassword.addTextChangedListener(TextChange())

        binding.btnLogin.setOnClickListener {
            mViewModel!!.onBtnLoginClick()
        }

        binding.register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    inner class TextChange : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }


        override fun beforeTextChanged(s: CharSequence, start: Int,
                                       count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence, start: Int,
                                   before: Int, count: Int) {
            try {
                if (s.length > 1)
                    mViewModel!!.onEmailChange()
            } catch (e: Exception) {
            }
        }
    }
}


