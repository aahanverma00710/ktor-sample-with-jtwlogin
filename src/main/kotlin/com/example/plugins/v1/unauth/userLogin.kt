package com.example.plugins.v1.unauth

import com.example.intefaces.v1.login.UserSource
import com.example.intefaces.v1.login.UserSourceImpl
import com.example.jwtConfig.JwtConfig
import com.example.models.userinfo.GenericResponse
import com.example.models.userinfo.LoginUserInfo
import com.example.models.userinfo.UserDataDao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.json.simple.JSONObject

fun Route.userLogin() {
    val userSource: UserSource = UserSourceImpl()

    route("/v1") {
        post("/login") {
            val data = call.receive<LoginUserInfo>()
            try {
                userSource.findUserByCredentials(data)?.let {
                    val token  =   JwtConfig.generateToken(it)
                    val genricResponse = GenericResponse<UserDataDao>()
                    genricResponse.message =  "Login Successful"
                    genricResponse.token =  token
                    genricResponse.data = it
                    call.respond(HttpStatusCode.OK, genricResponse)
                } ?: kotlin.run {
                    val message = "Invalid login credentials"
                    call.respond(HttpStatusCode.BadRequest, JSONObject(mapOf("message" to message)))
                }
            }catch (e:Exception){
                e.printStackTrace()
                val message = "Invalid login credentials"
                call.respond(HttpStatusCode.BadRequest, JSONObject(mapOf("message" to message)))
            }

        }
    }
}