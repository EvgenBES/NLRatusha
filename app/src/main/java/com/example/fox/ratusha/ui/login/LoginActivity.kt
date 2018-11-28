package com.example.fox.ratusha.ui.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.fox.ratusha.R
import com.example.fox.ratusha.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), LoginView {

    private val presenter = LoginPresenter(this, LoginInteractor())
    private var animUpDown: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


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

    override fun onResume() {
        loadLogin()
        super.onResume()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun setUsernameError() {
        username.error = getString(R.string.username_error)
    }

    override fun navigateToHome() {
        saveLogin()
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun saveLogin() {
        val sPreft = getSharedPreferences("sharedLogin", Context.MODE_PRIVATE)
        val ed: SharedPreferences.Editor = sPreft.edit()
        ed.putString("username", username.text.toString())
        ed.apply()
    }


    fun loadLogin() {
        val sPref = getSharedPreferences("sharedLogin", Context.MODE_PRIVATE)
        val loadUserName = sPref.getString("username", "")
        username.setText(loadUserName)
    }
}