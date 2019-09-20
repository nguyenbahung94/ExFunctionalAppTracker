package com.example.excryptotracker.ui.splash

import android.os.Bundle
import android.os.Handler
import com.example.excryptotracker.MainActivity
import com.example.excryptotracker.R
import com.example.excryptotracker.base.BaseActivity
import com.example.excryptotracker.extensions.showSnackbar
import com.example.excryptotracker.network.NetWorkUtils
import com.example.excryptotracker.ui.home.HomeActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.splash_layout.*
import org.jetbrains.anko.act
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashView {
    @Inject
    lateinit var netWorkUtils: NetWorkUtils
    private val splashPresenter by lazy { SplashPresenter(this, netWorkUtils) }


    override fun setUpLayout() {
        setContentView(R.layout.splash_layout)
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        splashPresenter.checkRequirement()
    }

    override fun navigationToHome() {
        Handler().postDelayed({
            startActivity<HomeActivity>()
            this@SplashActivity.finish()
        }, 3000)
    }

    override fun showInternetError() {
        rootSplash.showSnackbar(R.string.error_no_internet, timeLength = Snackbar.LENGTH_INDEFINITE) {
            this.setAction(resources.getString(R.string.text_check)) {
                this.dismiss()
                act.recreate()
            }
        }
    }


    override fun initInjection() {
        (application as MainActivity).cryptoDeps.inject(this)
    }


}