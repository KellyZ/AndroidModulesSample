package module.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkTopic (
    val id: String,
    val name: String = "",
    val shortDescription: String = "",
    val longDescription: String = "",
    val imageUrl: String = "",
    val url: String = "",
    val followed: Boolean = false,
    )