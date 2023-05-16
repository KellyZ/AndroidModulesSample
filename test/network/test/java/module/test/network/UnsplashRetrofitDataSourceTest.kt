package module.test.network

import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.test.runTest
import module.network.api.BuildConfig
import module.network.retrofit.RetrofitNetwork
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import kotlin.test.assertEquals

class UnsplashRetrofitDataSourceTest {

    private lateinit var subject: RetrofitNetwork

    @Before
    fun setUp() {
        subject = RetrofitNetwork(
            networkJson = Json { ignoreUnknownKeys = true },
            okHttpCallFactory = OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor()
                        .apply {
                            if (BuildConfig.DEBUG) {
                                setLevel(HttpLoggingInterceptor.Level.BODY)
                            }
                        },
                ).build()
        )
    }

    @Test
    fun testUnsplashSearchPhotos() = runTest() {
        assertEquals(
            626,
            subject.searchUnsplashPhotos("banana",1, 5).total_pages
        )
    }

}