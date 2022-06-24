package nl.abnamro.sena.assessment.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Repo(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("full_name")
    val fullName: String,
    @SerialName("private")
    val isPrivate: Boolean,
    @SerialName("owner")
    val owner: Owner,
    @SerialName("html_url")
    val htmlUrl: String,
    @SerialName("description")
    val description: String,
    @SerialName("visibility")
    val visibility: String,
) {
    @Serializable
    data class Owner(
        @SerialName("avatar_url")
        val avatarUrl: String,
    )
}
