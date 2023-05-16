package module.data.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import module.data.api.LoginRepository
import module.data.api.UnsplashRepository
import module.data.repository.DataUnsplashRepository
import module.data.repository.OfflineLoginRepository

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindLoginRepository(
        loginRepository: OfflineLoginRepository
    ) : LoginRepository

    @Binds
    fun bindsUnsplashRepositoty(
        unsplashRepository: DataUnsplashRepository
    ): UnsplashRepository

}