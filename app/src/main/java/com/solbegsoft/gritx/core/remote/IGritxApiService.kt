package com.solbegsoft.gritx.core.remote

import com.solbegsoft.gritx.core.remote.dto.signin.SignInDto
import com.solbegsoft.gritx.core.remote.dto.signin.SignInParams
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface IGritxApiService {

    @POST("authorization")
    fun signIn(@Body params: SignInParams): Single<SignInDto>
}