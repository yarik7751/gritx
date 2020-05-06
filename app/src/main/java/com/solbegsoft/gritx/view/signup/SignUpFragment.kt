package com.solbegsoft.gritx.view.signup

import androidx.fragment.app.Fragment
import com.solbegsoft.gritx.R
import com.solbegsoft.gritx.di.signin.SignUpComponent
import com.solbegsoft.gritx.tools.extensions.onTextChanged
import com.solbegsoft.gritx.view.base.BaseFragment
import com.solbegsoft.gritx.view.base.IBaseView
import kotlinx.android.synthetic.main.fragment_sign_up.*
import javax.inject.Inject

class SignUpFragment: BaseFragment(R.layout.fragment_sign_up), ISignUpView {

    @Inject
    lateinit var presenter: SignUpPresenter

    override fun onAttachPresenter() {
        presenter.onAttach(this)
    }

    override fun initComponent() {
        SignUpComponent.get().inject(this)
    }

    override fun onDetachPresenter() {
        presenter.onDetach()
    }

    override fun initView() {
        signUpFirstName.onTextChanged { presenter.firstName = it }
        signUpLastName.onTextChanged { presenter.lastName = it }
        signUpEmail.onTextChanged { presenter.email = it }
        signUpPassword.onTextChanged { presenter.password = it }

        signUpSubmit.setOnClickListener { presenter.submit() }
    }

    companion object {

        fun newInstance(): Fragment = SignUpFragment()
    }
}

interface ISignUpView: IBaseView {

}