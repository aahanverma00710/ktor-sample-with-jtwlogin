package com.example.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import org.json.simple.JSONObject

fun Application.exceptionHandling(){

    install(StatusPages){
        exception<Throwable> { call, cause ->
            val map = HashMap<String,Any>()
            map["message"] = cause
            call.respond(HttpStatusCode.BadRequest, JSONObject(map))
        }
    }
}