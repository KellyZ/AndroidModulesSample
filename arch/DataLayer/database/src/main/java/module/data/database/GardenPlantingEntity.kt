package module.data.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import module.data.model.GardenPlanting
import java.util.Calendar

@Entity(
    tableName = "garden_plantings",
    foreignKeys = [
        ForeignKey(entity = PlantEntity::class, parentColumns = ["plantId"], childColumns = ["garden_plantId"])
    ],
    indices = [Index("garden_plantId")]
)
data class GardenPlantingEntity(
    @Embedded(prefix = "garden_")
    val gardenPlanting: GardenPlanting,
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var gardenPlantingId: Long = 0
}
