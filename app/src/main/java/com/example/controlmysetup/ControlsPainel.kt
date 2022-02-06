package com.example.controlmysetup


import android.graphics.Color.rgb
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity


class ControlsPainel : AppCompatActivity() {
    private lateinit var switch1: Button;
    private lateinit var switch2: Button;
    private lateinit var switch3: Button;
    private lateinit var changeip: Button;
    private lateinit var seekbar1: SeekBar;
    private lateinit var seekbar2: SeekBar;
    private lateinit var seekbar3: SeekBar;
    private lateinit var previewcolor: View;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controls_painel);

        this.switch1 = findViewById(R.id.switch1);
        this.switch2 = findViewById(R.id.switch2);
        this.switch3 = findViewById(R.id.switch3);
        this.changeip = findViewById(R.id.changeip);
        this.seekbar1 = findViewById(R.id.seekBar1)
        this.seekbar2 = findViewById(R.id.seekBar2)
        this.seekbar3 = findViewById(R.id.seekBar3)
        this.previewcolor = findViewById(R.id.previewcolor)

        this.seekbar1.setOnSeekBarChangeListener(OnChange());
        this.seekbar2.setOnSeekBarChangeListener(OnChange());
        this.seekbar3.setOnSeekBarChangeListener(OnChange());

        var state1 = false;
        var state2 = false;
        var state3 = false;

        switch1.setOnClickListener{
            state1 = !state1;
            changeText(switch1, state1);
            changeBg(switch1, state1);
        }
        switch2.setOnClickListener{
            state2 = !state2;
            changeText(switch2, state2);
            changeBg(switch2, state2);
        }
        switch3.setOnClickListener{
            state3 = !state3;
            changeText(switch3, state3);
            changeBg(switch3, state3);
        }
        changeip.setOnClickListener{
            finish();
        }

    }

    fun getIntentParams():String{
        val param:String =  intent.extras?.getString("ip").toString();
        return param;
    }

    private fun changeBg(button:Button, state: Boolean){
        if(state){
            button.setBackgroundResource(R.drawable.onswitchbutton);
        }else{
            button.setBackgroundResource(R.drawable.offswitchbutton);
        }
        button.animate().apply {
            duration = 150;
            scaleX(1.1f);
            scaleY(1.1f);
        }.withEndAction{
            button.animate().apply {
                duration = 150;
                scaleX(1f);
                scaleY(1f);
            }.start();
        }
    }

    private fun changeText(button: Button, state:Boolean){
        if(state){
            button.text = "ON";
        }else{
            button.text = "OFF";
        }
    }

    fun getValuesOfSliders(): IntArray{
        val values : IntArray = intArrayOf(0,0,0)

        values[0] = seekbar1.progress
        values[1] = seekbar2.progress
        values[2] = seekbar3.progress
        return values
    }

    fun setPreviewColor(colors:IntArray){
        previewcolor.setBackgroundColor(rgb(colors[0],colors[1],colors[2]))
    }

    inner class OnChange : SeekBar.OnSeekBarChangeListener {
        private lateinit var colors: IntArray

        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            this.colors = getValuesOfSliders();
            this@ControlsPainel.setPreviewColor(colors)

        }
        override fun onStartTrackingTouch(seekBar: SeekBar?) {};
        override fun onStopTrackingTouch(seekBar: SeekBar?) {};
    }

}