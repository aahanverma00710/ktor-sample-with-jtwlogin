package com.example.utils

import com.example.models.userinfo.UserDataDao
import io.ktor.server.application.*
import io.ktor.server.auth.*

val ApplicationCall.user get() = authentication.principal<UserDataDao>()

val userInfoTable = ArrayList<UserDataDao>()

const val defaultId = 1
val createdAtTime = System.currentTimeMillis()