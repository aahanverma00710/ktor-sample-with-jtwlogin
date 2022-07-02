package com.example.jwtConfig

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.example.models.userinfo.UserDataDao
import com.example.utils.Constants
import java.util.*

object JwtConfig {
    private const val secret = "my-secret" // use your own secret
    internal const val issuer = "com.aahan"  // use your own issuer
    private const val validityInMs = 36_000_00 * 24 // 1 day
    private val algorithm = Algorithm.HMAC512(secret)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()

    /**
     * Produce a token for this combination of name and password
     */
    fun generateToken(user: UserDataDao): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withClaim(Constants.JWT_CLAIM_PARAM, user.id)
        .withExpiresAt(getExpiration())  // optional
        .sign(algorithm)

    /**
     * Calculate the expiration Date based on current time + the given validity
     */
    private fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)

}