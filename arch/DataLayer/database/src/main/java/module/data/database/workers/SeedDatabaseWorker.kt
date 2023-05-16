package module.data.database.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import module.data.database.AppDatabase
import module.data.database.PlantEntity
import module.data.model.Plant

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
): CoroutineWorker(context, workerParams) {

    private val json: Json = Json {
        ignoreUnknownKeys = true
    }

    override suspend fun doWork(): Result =
        withContext(Dispatchers.IO) {
            try {
                val filename = inputData.getString(KEY_FILENAME)
                if (filename != null ){
                    applicationContext.assets.open(filename).use {inputStream->
                        val plantList: List<Plant> = inputStream.use ( json::decodeFromStream )
                        val plantEntityList: List<PlantEntity> = plantList.map { PlantEntity(it) }
                        val database = AppDatabase.getInstance(applicationContext)
                        database.plantDao().insertAll(plantEntityList)

                        Result.success()
                    }
                } else {
                    Log.e(TAG, "Error seeding database - no valid filename")
                    Result.failure()
                }
            } catch (ex: Exception) {
                Log.e(TAG, "Error seeding database", ex)
                Result.failure()
            }
        }


    companion object {
        private const val TAG = "SeedDatabaseWorker"
        const val KEY_FILENAME = "PLANT_DATA_FILENAME"
    }
}