package com.example.doortodooradminapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
        setContentView(R.layout.admin_login)


  }
}
