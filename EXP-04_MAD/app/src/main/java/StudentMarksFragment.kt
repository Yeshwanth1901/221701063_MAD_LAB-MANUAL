package com.example.exp_4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class StudentMarksFragment : Fragment(R.layout.student_marks_fragment) {

    private lateinit var editTextMarks: EditText
    private lateinit var editTextTotal: EditText
    private lateinit var textViewGrade: TextView
    private lateinit var textViewStatus: TextView
    private lateinit var buttonCalculate: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Use findViewById to bind views
        editTextMarks = view.findViewById(R.id.editTextMarks)
        editTextTotal = view.findViewById(R.id.editTextTotal)
        textViewGrade = view.findViewById(R.id.textViewGrade)
        textViewStatus = view.findViewById(R.id.textViewStatus)
        buttonCalculate = view.findViewById(R.id.buttonCalculate)

        // Handle button click
        buttonCalculate.setOnClickListener {
            calculateGradeAndStatus()
        }
    }

    private fun calculateGradeAndStatus() {
        val marks = editTextMarks.text.toString().toIntOrNull() ?: 0
        val total = editTextTotal.text.toString().toIntOrNull() ?: 100

        val grade: String = when {
            marks >= total * 0.9 -> "A"
            marks >= total * 0.75 -> "B"
            marks >= total * 0.5 -> "C"
            else -> "F"
        }

        val status = if (marks >= total * 0.5) "Passed" else "Failed"

        // Update UI
        textViewGrade.text = "Grade: $grade"
        textViewStatus.text = "Status: $status"
    }
}