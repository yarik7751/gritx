package com.solbegsoft.gritx.core.remote.dto.signup

import com.google.gson.annotations.SerializedName

data class SignUpParams(
    @SerializedName("key") val key: String,
    @SerializedName("tokenUser") val userInfo: UserInfo
) {

    data class UserInfo(
        @SerializedName("email") val email: String,
        @SerializedName("firstName") val firstName: String,
        @SerializedName("lastName") val lastName: String,
        @SerializedName("password") val password: String
    )
}