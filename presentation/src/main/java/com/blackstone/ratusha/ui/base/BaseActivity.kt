package com.blackstone.ratusha.ui.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(){
    protected var TAG: String = "RATUSHA ${this::class.java.simpleName}"
}
