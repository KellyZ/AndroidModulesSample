package module.data.database

import androidx.room.Embedded
import androidx.room.Entity
import kotlinx.serialization.Serializable
import module.data.model.Plant

@Entity(tableName = "plants", primaryKeys = ["plantId"])
data class PlantEntity(
    @Embedded
    val plant: Plant
){

}
