package module.feature.topic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import module.core.common.util.asResult
import module.core.common.util.Result
import module.data.api.TopicsRepository
import module.network.model.NetworkTopic
import javax.inject.Inject

@HiltViewModel
class TopicViewModel @Inject constructor(
    private val topicsRepository: TopicsRepository,
): ViewModel() {

    val topicsUiState: StateFlow<TopicsUiState> = topicsRepository.getTopics().asResult().map {result->
        when(result){
            is Result.Loading->TopicsUiState.Loading
            is Result.Success -> TopicsUiState.Success(result.data)
            is Result.Error -> TopicsUiState.Error
        }
    }
        .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = TopicsUiState.Loading
    )

}

sealed interface TopicsUiState {
    data class Success(val topics : List<NetworkTopic>) : TopicsUiState
    object Loading : TopicsUiState
    object Error : TopicsUiState
}