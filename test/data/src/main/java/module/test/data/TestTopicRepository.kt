package module.test.data

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import module.data.api.TopicsRepository
import module.network.model.NetworkTopic

class TestTopicRepository: TopicsRepository {

    private val topicsFlow: MutableSharedFlow<List<NetworkTopic>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun getTopics(): Flow<List<NetworkTopic>> = topicsFlow

    override fun getTopic(id: String): Flow<NetworkTopic> {
        return topicsFlow.map { topics -> topics.find { it.id == id }!! }
    }

    fun sendTopics(topics: List<NetworkTopic>) {
        topicsFlow.tryEmit(topics)
    }
}