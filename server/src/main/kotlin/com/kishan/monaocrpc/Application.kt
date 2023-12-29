package com.kishan.monaocrpc

import FileOpenRequest
import FileSaveRequest
import Greeting
import SERVER_PORT
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

// Written bu ChatGPT
fun isPathInDirectory(filePath: String, directory: String): Boolean {
    val normalizedFilePath: Path = Paths.get(filePath).normalize()
    val normalizedDirectory: Path = Paths.get(directory).normalize()

    // Check if the file path starts with the directory path
    return normalizedFilePath.startsWith(normalizedDirectory)
}

fun Application.module() {
    install(
        ContentNegotiation
    ) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }

    install(CORS) {
        allowHost("localhost:8080")
        allowHeader(HttpHeaders.ContentType)
    }


    routing {
        get("/") {
            call.respondText("Ktor: ${Greeting().greet()}")
        }
        // POST file.open with params: path
        post("/file.open") {
            val req = call.receive<FileOpenRequest>();

            // Return contents
            try {
                val contents = Paths.get(req.path).toFile().readText()
                call.respondText(contents)
            } catch (e: Exception) {
                call.respond(
                    HttpStatusCode.InternalServerError,
                    "Failed to read file: ${e.message}"
                )
                return@post
            }
        }

        // Update a file, providing it's contents
        post("/file.save") {
            val req = call.receive<FileSaveRequest>();

            // Write contents
            try {
                val file = Paths.get(req.path).toFile()
                file.writeText(req.contents)
                call.respondText("OK")
            } catch (e: Exception) {
                call.respond(
                    HttpStatusCode.InternalServerError,
                    "Failed to write file: ${e.message}"
                )
                return@post
            }

        }
    }
}
