package com.example.exp_4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.EditText
import android.widget.Button

class StudentBasicDetailsFragment : Fragment(R.layout.student_basic_details_fragment) {

    private lateinit var editTextName: EditText
    private lateinit var editTextAge: EditText
    private lateinit var editTextAddress: EditText
    private lateinit var buttonSubmit: Button

    interface OnBasicDetailsEnteredListener {
        fun onBasicDetailsEntered(name: String, age: Int, address: String)
    }

    private var listener: OnBasicDetailsEnteredListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Use findViewById to manually bind views
        editTextName = view.findViewById(R.id.editTextName)
        editTextAge = view.findViewById(R.id.editTextAge)
        editTextAddress = view.findViewById(R.id.editTextAddress)
        buttonSubmit = view.findViewById(R.id.buttonSubmit)

        // Ensure the listener is set when the fragment is attached
        if (activity is OnBasicDetailsEnteredListener) {
            listener = activity as OnBasicDetailsEnteredListener
        }

        // Handle the submit button click
        buttonSubmit.setOnClickListener {
            val name = editTextName.text.toString()
            val age = editTextAge.text.toString().toIntOrNull() ?: 0
            val address = editTextAddress.text.toString()

            listener?.onBasicDetailsEntered(name, age, address)
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}