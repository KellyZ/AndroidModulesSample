package module.network.fake

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import module.network.api.NetworkDataSource
import module.network.fake.util.FakeAssetManager
import module.network.model.NetworkChangeList
import module.network.model.NetworkNewsResource
import module.network.model.NetworkTopic
import module.network.model.unsplash.UnsplashPhotoResponse
import javax.inject.Inject

class FakeNetworkDataSource @Inject constructor(
    private val assets: FakeAssetManager,
    private val networkJson: Json
) : NetworkDataSource {

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    companion object {
        private const val NEWS_ASSET = "news.json"
        private const val TOPICS_ASSET = "topics.json"
    }

    override suspend fun getTopics(ids: List<String>?): List<NetworkTopic> =
        withContext(ioDispatcher) {
            assets.open(TOPICS_ASSET).use(networkJson::decodeFromStream)
        }

    override suspend fun getNewsResources(ids: List<String>?): List<NetworkNewsResource> =
        withContext(ioDispatcher) {
            assets.open(NEWS_ASSET).use(networkJson::decodeFromStream)
        }

    override suspend fun getTopicChangeList(after: Int?): List<NetworkChangeList> {
        return getTopics().mapToChangeList(NetworkTopic::id)
    }

    override suspend fun getNewsResourceChangeList(after: Int?): List<NetworkChangeList> {
        return getNewsResources().mapToChangeList(NetworkNewsResource::id)
    }

    override suspend fun searchUnsplashPhotos(
        query: String,
        page: Int,
        perPage: Int
    ): UnsplashPhotoResponse {
        TODO("Not yet implemented")
    }

}

private fun <T> List<T>.mapToChangeList(
    idGetter: (T) -> String,
) = mapIndexed { index, item ->
    NetworkChangeList(
        id = idGetter(item),
        changeListVersion = index,
        isDelete = false
        )
}