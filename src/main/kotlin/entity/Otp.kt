package entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Otp(
    @SerialName("code")
    val code: String
)
