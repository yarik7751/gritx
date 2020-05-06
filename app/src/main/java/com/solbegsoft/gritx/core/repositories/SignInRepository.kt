package com.solbegsoft.gritx.core.repositories

import com.solbegsoft.gritx.core.remote.IGritxApiService
import com.solbegsoft.gritx.core.remote.dto.signin.SignInDto
import com.solbegsoft.gritx.core.remote.dto.signin.SignInParams
import io.reactivex.Single
import javax.inject.Inject

class SignInRepository @Inject constructor(
    private val gritxApiService: IGritxApiService
): ISignInRepository {

    override fun signIn(login: String, password: String): Single<SignInDto> {
        val params = SignInParams(
            login = login,
            password = password,
            key = KEY_API
        )
        return gritxApiService.signIn(params)
    }

    companion object {
        private const val KEY_API = "cf90471d-c3d7-4c0d-b0cb-7992df6fa21f"
    }
}

interface ISignInRepository {

    fun signIn(login: String, password: String): Single<SignInDto>
}