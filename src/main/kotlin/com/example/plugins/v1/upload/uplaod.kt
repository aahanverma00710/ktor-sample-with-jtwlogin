package com.example.plugins.v1.upload

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Route.fileUpload(){

    route("/fileUpload") {
        var fileDescription = ""
        var fileName = ""
        var myFile =
        get("/{name}") {
            // get filename from request url
            val filename = call.parameters["name"]!!
            // construct reference to file
            // ideally this would use a different filename
            val file = File("uploads/$filename")
            if(file.exists()) {
                call.respondFile(file)
            }
            else call.respond(HttpStatusCode.NotFound)
        }
        post("/upload") {
            val multipartData = call.receiveMultipart()
            multipartData.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        fileDescription = part.value
                    }
                    is PartData.FileItem -> {
                        fileName = part.originalFileName as String
                        val fileBytes = part.streamProvider().readBytes()
                        val file = File("uploads/$fileName")
                       /* if (!file.exists()){
                            file.mkdir()
                        }*/
                        file.writeBytes(fileBytes)
                    }
                    is PartData.BinaryItem -> {
                        println(part.name)
                    }
                    is PartData.BinaryChannelItem -> {

                    }
                }
            }

            call.respondText("$fileDescription is uploaded to 'uploads/$fileName'")
        }
    }
}