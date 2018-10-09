package arivista.login.ui.ui.register

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import arivista.login.R
import arivista.login.databinding.RegisterFragmentBinding
import arivista.login.viewmodel.RegisterViewModel

class RegisterFragment : Fragment() {

    lateinit var binding: RegisterFragmentBinding

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.register_fragment, container, false)
        var myView: View = binding.root
        binding.executePendingBindings()



        return myView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)

        binding.viewModel = viewModel



        binding.pincode.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                try {
                    var pincode = s.toString()


                    viewModel.getAddress(pincode)
                } catch (e: Exception) {
                }
            }
        })


        viewModel.liveData?.observe(this, Observer { response ->

            Log.e("response", response?.districtName)
        })

        // TODO: Use the ViewModel
    }

}
