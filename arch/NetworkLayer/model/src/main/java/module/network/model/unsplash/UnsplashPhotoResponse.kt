package module.network.model.unsplash

@kotlinx.serialization.Serializable
data class UnsplashPhotoResponse(
    val results: List<UnsplashPhoto>,
    val total_pages: Int
)