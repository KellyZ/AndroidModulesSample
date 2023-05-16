package module.network.fake.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import module.network.api.NetworkDataSource
import module.network.fake.FakeNetworkDataSource
import module.network.fake.util.ContextAssetManager
import module.network.fake.util.FakeAssetManager

@Module
@InstallIn(SingletonComponent::class)
interface NetworkBindModule {

    @Binds
    fun providesFakeAssetsManager(
        assetManager: ContextAssetManager
    ): FakeAssetManager

    @Binds
    fun bindsNetworkDataSource(
        networkDataSourceModule: FakeNetworkDataSource
    ): NetworkDataSource
}