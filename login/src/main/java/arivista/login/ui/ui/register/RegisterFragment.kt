package arivista.login.ui.ui.register

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
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
                    if (pincode.length == 6)
                        viewModel.getAddress(pincode)
                } catch (e: Exception) {
                }
            }
        })




        viewModel.streetlist.observeForever { it ->
            // Initialize a new array with elements
            // Initialize a new array adapter object
            val adapter = ArrayAdapter<String>(
                    activity, // Context
                    android.R.layout.simple_dropdown_item_1line, // Layout
                    it // Array
            )


            // Set the AutoCompleteTextView adapter
            binding.streetname.setAdapter(adapter)

        }


        // Auto complete threshold
        // The minimum number of characters to type to show the drop down
        binding.streetname.threshold = 1


        // Set an item click listener for auto complete text view
        binding.streetname.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            // Display the clicked item using toast
            Toast.makeText(context, "Selected : $selectedItem", Toast.LENGTH_SHORT).show()
        }


        // Set a dismiss listener for auto complete text view
        binding.streetname.setOnDismissListener {
            Toast.makeText(context, "Suggestion closed.", Toast.LENGTH_SHORT).show()
        }


        // Set a focus change listener for auto complete text view
        binding.streetname.onFocusChangeListener = View.OnFocusChangeListener { view, b ->
            if (b) {
                // Display the suggestion dropdown on focus
                binding.streetname.showDropDown()
            }
        }


        binding.submit.setOnClickListener {
            if (viewModel.postAddress()) {

                activity!!.finish()
            }
        }

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


        // TODO: Use the ViewModel
    }

}
