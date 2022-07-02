package com.example.models.userinfo

import io.ktor.server.auth.*
import kotlinx.serialization.Serializable

@Serializable
data class UserDataDao(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val password: String,
    val createdAt: Long
) : Principal

@Serializable
data class SignUpDao(
    val firstName: String? = "",
    val lastName: String? = "",
    val email: String? = "",
    val phoneNumber: String? = "",
    val password: String? = ""
)

@Serializable
data class LoginUserInfo(val email: String, val password: String) : Credential

@Serializable
class GenericResponse<T> {

    var message: String ?= null


    var data: T ?= null

    var token: String ?= null
}
