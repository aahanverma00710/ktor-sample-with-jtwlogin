package com.example.plugins

import com.example.plugins.v1.unauth.userLogin
import com.example.plugins.v1.unauth.userSignUp
import com.example.plugins.v1.upload.fileUpload
import com.example.plugins.v1.user.userInfo
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {

    routing {
        customerRouting()
        userLogin()
        userSignUp()
        userInfo()
        fileUpload()
    }
}
