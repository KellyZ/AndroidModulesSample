package module.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import module.network.api.NetworkDataSource
import module.network.model.NetworkChangeList
import module.network.model.NetworkNewsResource
import module.network.model.NetworkTopic
import module.network.model.unsplash.UnsplashPhotoResponse
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

private interface SampleNetworkApi {

    @GET(value = "topics")
    suspend fun getTopics(@Query("id") ids: List<String>?): List<NetworkTopic>
}

private interface UnsplashNetworkApi {
    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("client_id") clientId: String = "KEsrc3kDFPmw4n7A2sF97JceuQiz3nACpISOLwTRCHg"
    ): UnsplashPhotoResponse
}

private const val SampleBaseUrl = "http://www.sample.com"
private const val UnsplashBaseUrl = "https://api.unsplash.com/"

/**
 *  Wrapper for data provided such as Serializable
 */
private data class NetworkResponse<T>(
    val data: T,
)


class RetrofitNetwork constructor(
    networkJson: Json,
    okHttpCallFactory: Call.Factory
) : NetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(SampleBaseUrl)
        .callFactory(okHttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType())
        )
        .build()
        .create(SampleNetworkApi::class.java)

    private val unsplashApi = Retrofit.Builder()
        .baseUrl(UnsplashBaseUrl)
        .callFactory(okHttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType())
        )
        .build()
        .create(UnsplashNetworkApi::class.java)

    override suspend fun getTopics(ids: List<String>?): List<NetworkTopic> {
        TODO("Not yet implemented")
    }

    override suspend fun getNewsResources(ids: List<String>?): List<NetworkNewsResource> {
        TODO("Not yet implemented")
    }

    override suspend fun getTopicChangeList(after: Int?): List<NetworkChangeList> {
        TODO("Not yet implemented")
    }

    override suspend fun getNewsResourceChangeList(after: Int?): List<NetworkChangeList> {
        TODO("Not yet implemented")
    }

    override suspend fun searchUnsplashPhotos(
        query: String,
        page: Int,
        perPage: Int
    ): UnsplashPhotoResponse {
        return unsplashApi.searchPhotos(query,page,perPage)
    }
}