package module.data.database

import androidx.room.TypeConverter
import java.util.Calendar

class Converters {
    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun datastampTOCalendar(value: Long): Calendar = Calendar.getInstance().apply { timeInMillis = value }
}