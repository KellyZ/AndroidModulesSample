package module.data.database.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import module.data.api.PlantRepository
import module.data.database.repository.DBPlantRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {

    @Binds
    fun bindPlantRepository(
        plantRepository: DBPlantRepository
    ): PlantRepository

}