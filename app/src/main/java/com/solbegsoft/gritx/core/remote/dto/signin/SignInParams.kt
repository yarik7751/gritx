package com.solbegsoft.gritx.core.remote.dto.signin

import com.google.gson.annotations.SerializedName

data class SignInParams(
    @SerializedName("key") val key: String,
    @SerializedName("login") val login: String,
    @SerializedName("password") val password: String
)