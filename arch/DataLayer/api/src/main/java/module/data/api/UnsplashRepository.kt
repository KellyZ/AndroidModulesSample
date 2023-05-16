package module.data.api

import kotlinx.coroutines.flow.Flow
import module.network.model.unsplash.UnsplashPhotoResponse

interface UnsplashRepository {

    fun searchPhotos(query: String, page: Int, per_page: Int): Flow<UnsplashPhotoResponse>

}