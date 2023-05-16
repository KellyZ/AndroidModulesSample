package module.network.model.unsplash

@kotlinx.serialization.Serializable
data class UnsplashPhotoUser(
    val name:String,
    val username: String
){
    val attributionUrl: String
        get() {
            return "https://unsplash.com/$username?utm_source=sunflower&utm_medium=referral"
        }
}
