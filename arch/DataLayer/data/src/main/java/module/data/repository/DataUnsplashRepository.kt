package module.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import module.data.api.UnsplashRepository
import module.network.api.NetworkDataSource
import module.network.model.unsplash.UnsplashPhotoResponse
import javax.inject.Inject

class DataUnsplashRepository @Inject constructor(
    val dataSource: NetworkDataSource
): UnsplashRepository {

    override fun searchPhotos(query: String, page: Int, per_page: Int): Flow<UnsplashPhotoResponse> {
        return flow {
            emit(
                dataSource.searchUnsplashPhotos(query, page, per_page)
            )
        }.flowOn(Dispatchers.IO)
    }
}