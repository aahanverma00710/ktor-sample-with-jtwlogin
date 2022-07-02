package com.example.plugins.v1.unauth

import com.example.intefaces.v1.signup.UserSIgnUp
import com.example.intefaces.v1.signup.UserSignUpImpl
import com.example.jwtConfig.JwtConfig
import com.example.models.userinfo.GenericResponse
import com.example.models.userinfo.SignUpDao
import com.example.models.userinfo.UserDataDao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.json.simple.JSONObject

fun Route.userSignUp() {
    val userSource: UserSIgnUp = UserSignUpImpl()
    route("/v1") {
        post("/signUp") {
            val formParameters = call.receiveParameters()
            val email = formParameters["email"].toString()
            val password =formParameters["password"].toString()
            val firstName  =formParameters["firstName"].toString()
            val lastName  =formParameters["lastName"].toString()
            val phone  =formParameters["phoneNumber"].toString()

            if (userSource.checkIfEmailExist(email ?: "")){
                val data = SignUpDao(firstName,lastName,email,phone,password)
                val user = userSource.getCreatedUser(data)
                userSource.updateList(user)
                val token  =   JwtConfig.generateToken(user)

                val genricResponse = GenericResponse<UserDataDao>()
                genricResponse.message =  "UserCreated Successful"
                genricResponse.token =  token
                genricResponse.data = user
                call.respond(HttpStatusCode.Created, genricResponse)
            } else{
                val message = "Email Id Already Exist"
                call.respond(HttpStatusCode.BadRequest, JSONObject(mapOf("message" to message)))

            }
        }
    }
}