package com.example.mim

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        var mCountdownTimer: CountDownTimer = object : CountDownTimer(3000, 3000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                finish()
                val intent = Intent(baseContext, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        mCountdownTimer.start()
    }
}