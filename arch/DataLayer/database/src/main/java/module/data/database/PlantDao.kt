package module.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plantEntities: List<PlantEntity>)

    @Query("SELECT * FROM plants ORDER BY name")
    fun getPlants(): Flow<List<PlantEntity>>

    @Query("SELECT * FROM plants WHERE plantId = :plantId")
    fun getPlant(plantId: String): Flow<PlantEntity>
}