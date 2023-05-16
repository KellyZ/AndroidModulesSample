package module.network.api

import module.network.model.NetworkChangeList
import module.network.model.NetworkNewsResource
import module.network.model.NetworkTopic
import module.network.model.unsplash.UnsplashPhotoResponse

interface NetworkDataSource {

    suspend fun getTopics(ids: List<String>?=null) : List<NetworkTopic>

    suspend fun getNewsResources(ids: List<String>? = null) : List<NetworkNewsResource>

    suspend fun getTopicChangeList(after: Int? = null) : List<NetworkChangeList>

    suspend fun getNewsResourceChangeList(after: Int? = null): List<NetworkChangeList>

    suspend fun searchUnsplashPhotos(query: String, page: Int, perPage: Int): UnsplashPhotoResponse
}