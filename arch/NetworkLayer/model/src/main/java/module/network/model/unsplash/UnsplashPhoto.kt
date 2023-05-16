package module.network.model.unsplash

@kotlinx.serialization.Serializable
data class UnsplashPhoto(
    val id: String,
    val urls: UnsplashPhotoUrls,
    val user: UnsplashPhotoUser
)