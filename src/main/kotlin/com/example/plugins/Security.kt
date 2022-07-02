package com.example.plugins

import com.example.intefaces.v1.login.UserSource
import com.example.intefaces.v1.login.UserSourceImpl
import com.example.jwtConfig.JwtConfig
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configureSecurity() {
    val userSource: UserSource = UserSourceImpl()

    install(Authentication) {
        jwt("auth-jwt") {
            realm = JwtConfig.issuer
            verifier(JwtConfig.verifier)
            validate {
                it.payload.getClaim("id").asInt()?.let(userSource::findUserById)
            }
        }
    }
}
