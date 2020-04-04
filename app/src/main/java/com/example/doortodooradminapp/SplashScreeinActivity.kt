package com.example.doortodooradminapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Fade
import android.transition.Transition
import android.view.Window
import android.view.WindowManager
import android.widget.ProgressBar

class SplashScreeinActivity : AppCompatActivity() {
    var splashScreenProgressBar:ProgressBar?=null
    var delay=3L;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen_activity)
        splashScreenProgressBar=findViewById(R.id.splash_screen_progressBar)
        val thread=Thread{
            Thread.sleep(delay*1000)
            var intent:Intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()



        }
        thread.start()
    }
}
