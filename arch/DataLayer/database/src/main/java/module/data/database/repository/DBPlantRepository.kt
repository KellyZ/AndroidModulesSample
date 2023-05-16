package module.data.database.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import module.data.api.PlantRepository
import module.data.database.PlantDao
import module.data.model.Plant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DBPlantRepository @Inject constructor(
    private val plantDao: PlantDao,
): PlantRepository {

    override fun getPlants(): Flow<List<Plant>>  = plantDao.getPlants().flowOn(Dispatchers.IO).map {
        it.map { plantEntity -> plantEntity.plant }
    }

    override fun getPlant(plantId: String): Flow<Plant> {
        return plantDao.getPlant(plantId).map { it.plant }
    }

}