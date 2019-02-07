package com.example.fox.ratusha.ui.screens.start

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.fox.ratusha.R
import com.example.fox.ratusha.ui.screens.main.MainActivity
import kotlinx.android.synthetic.main.activity_start.*


class StartActivity : AppCompatActivity(), StartView {

    private val presenter = StartPresenter(this)
    private lateinit var animUpDown: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        animUpDown = AnimationUtils.loadAnimation(this, R.anim.animation_up_down)
        blackStone.startAnimation(animUpDown)

        startNextActivity()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }


    override fun startNextActivity() {
        Handler().postDelayed({
            startActivity(MainActivity().getIntent(this))
            finish()
        }, 2500)
    }


}


