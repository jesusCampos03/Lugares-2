package com.example.lugares_trabajo

import  android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.lugares_trabajo.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        binding.botonLogin.setOnClickListener(){
            hacerlogin();
        }
        binding.botonRegistrar.setOnClickListener(){
            hacerRegister();
        }

    }

    private fun hacerRegister() {
        var email = binding.etMail.text.toString()
        var clave = binding.etClave.text.toString()

        auth.createUserWithEmailAndPassword(email,clave)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful){
                    Log.d("creando Usuario" , "Registrado")
                    val user = auth.currentUser
                    if (user != null){
                        actualiza(user)
                        }
                    }else{
                        Log.d("creando usuario","fallo")
                        Toast.makeText(baseContext, "falla", Toast.LENGTH_LONG).show()
                    }
                }
            }
    private fun hacerlogin(){
        var email = binding.etMail.text.toString()
        var clave = binding.etClave.text.toString()

        auth.createUserWithEmailAndPassword(email,clave)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful){
                    Log.d("Autenticando" , "Atenticado")
                    val user = auth.currentUser
                    if (user != null){
                        actualiza(user)
                    }
                }else{
                    Log.d("Atenticado","fallo")
                    Toast.makeText(baseContext, "falla", Toast.LENGTH_LONG).show()
                }
            }
    }
    private fun actualiza(user: FirebaseUser?){
        if (user != null){
            val intent = Intent(this,Principal::class.java)
            startActivity(intent)
        }
    }
    public override fun onStart(){
        super.onStart()
        val usuario = auth.currentUser
        actualiza(usuario)
    }


}





