package com.example.fox.ratusha.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.fox.ratusha.R
import com.example.fox.ratusha.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {

    private val presenter = LoginPresenter(this, LoginInteractor())
    private var animUpDown: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val actionBar = supportActionBar
        actionBar!!.hide()

        loginButton.setOnClickListener { validateCredentials() }

        animUpDown = AnimationUtils.loadAnimation(this, R.anim.animation_up_down)
        animUpDown?.setAnimationListener(animationListener)
        blackStone.startAnimation(animUpDown)
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

    private fun validateCredentials() {
        presenter.validateCredentials(username.text.toString())
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun setUsernameError() {
        username.error = getString(R.string.username_error)
    }

    override fun navigateToHome() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}