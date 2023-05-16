package module.data.model

import java.util.Calendar
import java.util.Calendar.DAY_OF_YEAR

@kotlinx.serialization.Serializable
data class Plant(
    var plantId: String,
    var name: String,
    var description: String,
    var growZoneNumber: Int ,
    var wateringInterval: Int = 7,
    var imageUrl: String = "",
) {
    fun shouldBeWatered(since: Calendar, lastWateringDate: Calendar) =
        since > lastWateringDate.apply { add(DAY_OF_YEAR, wateringInterval) }

    override fun toString(): String {
        return name
    }
}
