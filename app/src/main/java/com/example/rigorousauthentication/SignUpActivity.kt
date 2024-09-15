package com.example.rigorousauthentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rigorousauthentication.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private  lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.button.setOnClickListener{
                val email = binding.emailEt.text.toString()
                val pass = binding.passET.text.toString()
                val confirmPass = binding.confirmPassEt.text.toString()

                if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()){
                    if (pass == confirmPass){

                        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener{
                            if(it.isSuccessful){
                                val intent = Intent(this, SignInActivity::class.java)
                                startActivity(intent)
                                finish()
                            }else
                            {
                                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }

                    }else
                    {
                        Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this, "Empty Fields Are Not Allowed !!", Toast.LENGTH_SHORT).show()
                }
        }

    }
}