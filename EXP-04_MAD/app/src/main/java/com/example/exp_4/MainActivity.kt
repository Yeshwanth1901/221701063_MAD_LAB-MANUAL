package com.example.exp_4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load StudentBasicDetailsFragment into the container
        val basicDetailsFragment = StudentBasicDetailsFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.basicDetailsFragmentContainer, basicDetailsFragment)
        transaction.commit()

        // Load StudentMarksFragment into the container
        val marksFragment = StudentMarksFragment()
        val transactionMarks = supportFragmentManager.beginTransaction()
        transactionMarks.replace(R.id.studentMarksFragmentContainer, marksFragment)
        transactionMarks.commit()
    }
}