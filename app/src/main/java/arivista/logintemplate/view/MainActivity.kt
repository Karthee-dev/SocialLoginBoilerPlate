package arivista.logintemplate.view

import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import arivista.logintemplate.R
import arivista.logintemplate.databinding.ActivityMainBinding
import arivista.logintemplate.viewmodel.LoginViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewModel = LoginViewModel();
        binding.executePendingBindings();

    }
    companion object {

        @BindingAdapter("toastMessage")
        fun runMe(view: View, message: String?) {
            if (message != null)
                Toast.makeText(view.context, message, Toast.LENGTH_SHORT).show()
        }
    }
}


