package module.data.fake.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import module.data.api.TopicsRepository
import module.data.fake.FakeTopicRepository

@Module
@InstallIn(SingletonComponent::class)
interface FakeModule {

    @Binds
    fun provideFakeTopicRepository(
        topicRepository: FakeTopicRepository
    ): TopicsRepository

}