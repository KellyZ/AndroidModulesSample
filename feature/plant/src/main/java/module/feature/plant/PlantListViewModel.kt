package module.feature.plant

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import module.data.api.PlantRepository
import module.data.model.Plant
import javax.inject.Inject

@HiltViewModel
class PlantListViewModel @Inject constructor(
    private val plantRepository: PlantRepository
):ViewModel() {

    val plants: StateFlow<List<Plant>> = plantRepository.getPlants().stateIn(
        initialValue = emptyList(),
        started = SharingStarted.WhileSubscribed(5_000),
        scope = viewModelScope
    )

}