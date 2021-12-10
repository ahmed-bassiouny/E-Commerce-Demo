package com.thechefz.e_commerce_demo.presentation_layer.activities.auth_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thechefz.e_commerce_demo.R
import com.thechefz.e_commerce_demo.presentation_layer.fragments.login.LoginFragment
import com.thechefz.e_commerce_demo.presentation_layer.fragments.verification_code.VerificationCodeFragment
import com.thechefz.e_commerce_demo.utils.extensions.addFragment

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        // hide action bar
        actionBar?.hide()

        addFragment(LoginFragment.newInstance(),R.id.flMainContainer)
    }
}