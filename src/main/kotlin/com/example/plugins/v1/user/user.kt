package com.example.plugins.v1.user

import com.example.intefaces.v1.login.UserSource
import com.example.intefaces.v1.login.UserSourceImpl
import com.example.models.userinfo.GenericResponse
import com.example.models.userinfo.UserDataDao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.json.simple.JSONObject

fun Route.userInfo() {
    val user: UserSource = UserSourceImpl()

    route("v1/user") {
        authenticate("auth-jwt") {
            get("{id?}") {
                val id = call.parameters["id"] ?: return@get call.respondText(
                    "Missing id",
                    status = HttpStatusCode.BadRequest
                )
                val customer =user.findUserById(id.toInt())
                val map = HashMap<String,Any>()
                customer?.let {
                    val genricResponse = GenericResponse<UserDataDao>()
                    genricResponse.message =  "User Found Successful"
                    genricResponse.data = it
                    call.respond(HttpStatusCode.OK,genricResponse)
                } ?: kotlin.run {
                    map["message"] = "No User Found"
                    map["data"] = it
                    call.respond(HttpStatusCode.BadRequest, JSONObject(map))
                }

            }
        }

    }
}