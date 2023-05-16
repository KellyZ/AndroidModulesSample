package module.data.api

import kotlinx.coroutines.flow.Flow
import module.data.model.Plant

interface PlantRepository {

    fun getPlants(): Flow<List<Plant>>

    fun getPlant(plantId: String): Flow<Plant>

}