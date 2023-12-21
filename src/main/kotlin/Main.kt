import entity.Otp
import io.ktor.client.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import java.awt.SystemColor.text

const val API_PATH = "v1"
fun main() {
    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
        routing {
            get("/$API_PATH/reg/otpresend") {
                call.response.status(HttpStatusCode.OK)
            }
            post("/$API_PATH/reg/otp") {
                val otp = call.receive<Otp>()
                println(otp.code)
                if (otp.code == "1234")
                    call.response.status(HttpStatusCode.OK)
                else call.response.status(HttpStatusCode.Forbidden)
            }
        }
    }.start(wait = true)
}