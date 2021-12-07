package com.thechefz.e_commerce_demo.presentation_layer.fragments.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.thechefz.e_commerce_demo.R
import com.thechefz.e_commerce_demo.presentation_layer.fragments.verification_code.VerificationCodeFragment
import com.thechefz.e_commerce_demo.utils.extensions.addFragment
import com.thechefz.e_commerce_demo.utils.extensions.replaceFragment
import kotlinx.android.synthetic.main.login_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener()
        observer()
    }

    private fun listener() {
        etEmailOrPhone.addTextChangedListener {
            checkInputs()
        }
        etPassword.addTextChangedListener {
            checkInputs()
        }
        btnLogin.setOnClickListener {
            replaceFragment(VerificationCodeFragment.newInstance(),R.id.flMainContainer)
        }
    }

    private fun checkInputs() {
        loginViewModel.isValidInput(etEmailOrPhone.text.toString(),etPassword.text.toString())
    }

    private fun observer() {
        loginViewModel.loginBtnObserve.observe(viewLifecycleOwner, Observer {
            Log.e("ahmed", it.toString())
            btnLogin.isEnabled = it
        })
    }


}