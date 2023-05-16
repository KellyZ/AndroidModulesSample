package module.data.model

import java.util.Calendar

@kotlinx.serialization.Serializable
data class GardenPlanting(
    val plantId: String,
    val plantDate: Long = Calendar.getInstance().timeInMillis,
    val lastWateringDate: Long = Calendar.getInstance().timeInMillis
) {
    var gardenPlantingId: Long = 0
}
