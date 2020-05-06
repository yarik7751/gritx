package com.solbegsoft.gritx.core.repositories

import com.solbegsoft.gritx.core.remote.IGritxApiService
import com.solbegsoft.gritx.core.remote.dto.signin.SignInDto
import com.solbegsoft.gritx.core.remote.dto.signup.SignUpParams
import io.reactivex.Single
import javax.inject.Inject

class SignUpRepository @Inject constructor(
    private val gritxApiService: IGritxApiService
): ISignUpRepository {

    companion object {
        private const val KEY_API = "cf90471d-c3d7-4c0d-b0cb-7992df6fa21f"
    }

    override fun signUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ): Single<SignInDto> {
        val params = SignUpParams(KEY_API, SignUpParams.UserInfo(
            lastName = lastName,
            firstName = firstName,
            email = email,
            password = password
        ))

        return gritxApiService.signUp(params)
    }
}

interface ISignUpRepository {

    fun signUp(email: String, password: String, firstName: String, lastName: String): Single<SignInDto>
}