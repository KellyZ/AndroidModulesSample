package module.feature.plant

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import module.data.api.PlantRepository
import module.data.model.Plant
import javax.inject.Inject

@HiltViewModel
class PlantDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    plantRepository: PlantRepository
): ViewModel() {

    private val plantId: MutableStateFlow<String> = MutableStateFlow(
        savedStateHandle.get<String>(PLANT_ID_SAVED_STATE_KEY) ?: ""
    )

    fun setPlantId(id: String) {
        plantId.value = id
    }

    val plantUiState: StateFlow<PlantUiState> = plantId.flatMapLatest { id->
        if (id.isEmpty()) {
            MutableStateFlow(PlantUiState.Loading)
        } else {
            plantRepository.getPlant(plantId.value).map {
                PlantUiState.Success(it)
            }
        }
    }.stateIn(
        scope = viewModelScope,
        initialValue = PlantUiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000)
    )

    companion object {
        private const val PLANT_ID_SAVED_STATE_KEY = "plantId"
    }


}

sealed interface PlantUiState {
    object Loading: PlantUiState
    data class Success(val plant: Plant): PlantUiState
}