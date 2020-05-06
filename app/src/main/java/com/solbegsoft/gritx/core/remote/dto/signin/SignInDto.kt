package com.solbegsoft.gritx.core.remote.dto.signin

import com.google.gson.annotations.SerializedName

data class SignInDto(
    @SerializedName("id") val id: String?,
    @SerializedName("value") val token: String?
)