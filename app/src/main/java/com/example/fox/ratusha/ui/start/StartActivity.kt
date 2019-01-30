package com.example.fox.ratusha.ui.start

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.fox.ratusha.R
import com.example.fox.ratusha.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_start.*


class StartActivity : AppCompatActivity(), StartView {

    private val presenter = StartPresenter(this)
    private var animUpDown: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        animUpDown = AnimationUtils.loadAnimation(this, R.anim.animation_up_down)
        animUpDown?.setAnimationListener(animationListener)
        blackStone.startAnimation(animUpDown)

        startNextActivity()

    }

    internal var animationListener: Animation.AnimationListener = object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation) {

        }

        override fun onAnimationEnd(animation: Animation) {
            blackStone.startAnimation(animUpDown)
        }

        override fun onAnimationRepeat(animation: Animation) {

        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }


    override fun startNextActivity() {
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)
    }

}


