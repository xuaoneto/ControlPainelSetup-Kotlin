package com.example.controlmysetup

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var inputip: TextInputEditText
    private lateinit var enterbutton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        this.inputip = findViewById(R.id.inputIp)
        this.enterbutton = findViewById(R.id.enter)

        enterbutton.setOnClickListener{
            goToSecondScreen()
        }
    }

    private fun goToSecondScreen(){
        val secondScreen = Intent(this,ControlsPainel::class.java);
        val params = Bundle();
        val ip:String = this.inputip.text.toString();
        params.putString("ip", ip);
        startActivity(secondScreen);
    }



}