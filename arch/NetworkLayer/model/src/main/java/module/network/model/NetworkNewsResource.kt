package module.network.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import module.network.model.util.InstantSerializer
import module.network.model.util.NewsResourceTypeSerializer

@Serializable
data class NetworkNewsResource(
    val id: String,
    val title: String,
    val content: String,
    val url: String,
    val headerImageUrl: String,
    @Serializable(InstantSerializer::class)
    val publishDate: Instant,
    @Serializable(NewsResourceTypeSerializer::class)
    val type: NewsResourceType,
    val topics: List<String>  = listOf()
)
