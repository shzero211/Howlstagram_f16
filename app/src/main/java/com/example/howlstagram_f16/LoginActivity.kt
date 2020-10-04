package com.example.howlstagram_f16

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class  LoginActivity : AppCompatActivity() {
    var auth:FirebaseAuth ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //getinstance는 데이터읽기쓰기할때  얻어와야한다.
        auth=FirebaseAuth.getInstance()
        email_login_button.setOnClickListener {
            signinAndSignup()
        }
    }

    fun signinAndSignup(){
    auth?.createUserWithEmailAndPassword(email_edittext.text.toString(),password_edittext.text.toString())?.addOnCompleteListener {
        task ->
        if(task.isSuccessful){
            //cre ating a user account
            moveMainPage(task.result?.user)
        }else if(task.exception?.message.isNullOrEmpty()){
            //show the error message
            Toast.makeText(this,task.exception?.message,Toast.LENGTH_LONG).show()
        }else{
            //Login if you have accout
            signinEmail()
            }
        }
    }

    fun signinEmail(){
        auth?.signInWithEmailAndPassword(email_edittext.text.toString(),password_edittext.text.toString())?.addOnCompleteListener {
        task ->
            if(task.isSuccessful){
                //Login
            }else{
                //show the error message
            }
        }
    }

    fun moveMainPage(user: FirebaseUser?){
        if(user!=null){
            startActivity(Intent( this,MainActivity::class.java))
        }
    }
}