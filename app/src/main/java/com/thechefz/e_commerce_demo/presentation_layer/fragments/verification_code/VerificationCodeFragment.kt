package com.thechefz.e_commerce_demo.presentation_layer.fragments.verification_code

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.thechefz.e_commerce_demo.R
import kotlinx.android.synthetic.main.verification_code_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class VerificationCodeFragment : Fragment() {

    companion object {
        fun newInstance() = VerificationCodeFragment()
    }


    private val verificationViewModel: VerificationCodeViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.verification_code_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener()
        observe()
    }

    private fun observe() {
        verificationViewModel.confirmBtnObserve.observe(viewLifecycleOwner, Observer {
            btnConfirm.isEnabled = it
        })

        verificationViewModel.validCodeObserve.observe(viewLifecycleOwner, Observer {
            if (it) {
                // open home
            }

        }, loadingObserver = Observer { }, commonErrorObserver = Observer {
            Toast.makeText(context, it.getMessage(), Toast.LENGTH_SHORT).show()
        })
    }

    private fun listener() {
        pinView.addTextChangedListener {
            verificationViewModel.isValidCode(it.toString())
        }

        btnConfirm.setOnClickListener {
            verificationViewModel.checkCode(pinView.text.toString())
        }
    }

}