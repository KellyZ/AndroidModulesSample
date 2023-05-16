package module.data.api

import kotlinx.coroutines.flow.Flow
import module.network.model.NetworkTopic

interface TopicsRepository {

    fun getTopics(): Flow<List<NetworkTopic>>

    fun getTopic(id: String) : Flow<NetworkTopic>

}