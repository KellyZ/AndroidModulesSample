package module.test.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import module.data.api.TopicsRepository
import module.data.fake.FakeTopicRepository

//@Module
//@TestInstallIn(
//    components = [SingletonComponent::class],
//    replaces = []
//)
//interface TestDataModule {
//
//    @Binds
//    fun bindsTopicRepository(
//        fakeTopicRepository: FakeTopicRepository,
//    ): TopicsRepository
//
//}