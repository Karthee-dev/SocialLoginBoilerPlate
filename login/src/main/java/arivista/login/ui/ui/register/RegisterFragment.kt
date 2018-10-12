package arivista.login.ui.ui.register

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
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

        binding.streetname.threshold = 1
        binding.village.threshold = 1

        binding.pincode.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                try {
                    var pincode = s.toString()
                    Log.e("pincode length", pincode.length.toString())
                    if (pincode.length == 6)
                        viewModel.getAddress(pincode)
                } catch (e: Exception) {
                }
            }
        })
        binding.streetname.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                try {
                    if (s.length == 1)
                        viewModel.getStreet(s.toString())
                } catch (e: Exception) {
                }
            }
        })

        viewModel.streetlist.observeForever { it ->
            // Initialize a new array with elements
            val adapter = ArrayAdapter<String>(
                    activity, // Context
                    android.R.layout.simple_dropdown_item_1line, // Layout
                    it // Array
            )

            // Set the AutoCompleteTextView adapter
            binding.streetname.setAdapter(adapter)

        }
        viewModel.villagelist.observeForever { it ->
            // Initialize a new array with elements
            // Initialize a new array adapter object
            val adapter = ArrayAdapter<String>(
                    activity, // Context
                    android.R.layout.simple_dropdown_item_1line, // Layout
                    it // Array
            )


            // Set the AutoCompleteTextView adapter
            binding.village.setAdapter(adapter)

            binding.village.requestFocus()
            binding.village.showDropDown()


            val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm!!.showSoftInput(binding.village, InputMethodManager.SHOW_IMPLICIT)
        }
        viewModel.isSuccess.observeForever {
            activity!!.finish()

        }

        binding.submit.setOnClickListener {
            viewModel.postAddress()
        }

    }

}
