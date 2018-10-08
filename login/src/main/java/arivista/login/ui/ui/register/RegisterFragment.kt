package arivista.login.ui.ui.register

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import arivista.login.R
import arivista.login.databinding.RegisterFragmentBinding
import arivista.login.viewmodel.RegisterViewModel

class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        var binding: RegisterFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.register_fragment, container, false)
        var myView: View = binding.root

        return myView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)

        // TODO: Use the ViewModel
    }

}
