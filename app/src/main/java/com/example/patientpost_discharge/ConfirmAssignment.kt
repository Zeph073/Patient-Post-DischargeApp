package com.example.patientpost_discharge

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.patientpost_discharge.databinding.ActivityConfirmAssignmentBinding
import com.example.patientpost_discharge.doctor.AssignCHP
import com.google.firebase.database.FirebaseDatabase

class ConfirmAssignment : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmAssignmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmAssignmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")

//        val sharedPreferences1 = getSharedPreferences("Prefs" , MODE_PRIVATE)
//        val userName = sharedPreferences1.getString("name" , null)

        val sharedPreferences = getSharedPreferences("MyPrefs" , MODE_PRIVATE)
        val uid = sharedPreferences.getString("uid" , null).toString()

        FirebaseDatabase.getInstance().getReference("Final details/$uid").child("assignedTo").setValue(name)
        FirebaseDatabase.getInstance().getReference("Current Patients/$uid").child("chpEmail").setValue(email)
        binding.confirm.setOnClickListener {
            Toast.makeText(
                this@ConfirmAssignment,
                "Assigned to $name",
                Toast.LENGTH_SHORT
            ).show()
            startActivity(Intent(this@ConfirmAssignment , CurrentPatients::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            finish()

        }
        binding.cancel.setOnClickListener {
            startActivity(Intent(this@ConfirmAssignment, AssignCHP::class.java))
            finish()
        }


    }
}