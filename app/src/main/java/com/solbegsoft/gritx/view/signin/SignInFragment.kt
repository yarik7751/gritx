package com.solbegsoft.gritx.view.signin

import androidx.fragment.app.Fragment
import com.solbegsoft.gritx.BuildConfig
import com.solbegsoft.gritx.R
import com.solbegsoft.gritx.di.signin.SignInComponent
import com.solbegsoft.gritx.tools.extensions.onTextChanged
import com.solbegsoft.gritx.view.base.BaseFragment
import com.solbegsoft.gritx.view.base.IBaseView
import kotlinx.android.synthetic.main.fragment_sign_in.*
import javax.inject.Inject

class SignInFragment: BaseFragment(R.layout.fragment_sign_in), ISignInView {

    @Inject
    lateinit var presenter: SignInPresenter

    override fun initComponent() {
        SignInComponent.get().inject(this)
    }

    override fun onAttachPresenter() {
        presenter.onAttach(this)
    }

    override fun onDetachPresenter() {
        presenter.onDetach()
    }

    override fun initView() {
        signInEmail.onTextChanged { presenter.email = it }

        signInPassword.onTextChanged { presenter.password = it }

        signInSubmit.setOnClickListener { presenter.submit() }

        signInRegister.setOnClickListener { presenter.navigateToSignUp() }

        if(BuildConfig.DEBUG) {
            signInEmail.setText("dev.ios@well-advised.com")
            signInPassword.setText("Shadelands123")
        }
    }

    companion object {

        fun newInstance(): Fragment = SignInFragment()
    }
}

interface ISignInView: IBaseView