package module.data.fake

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import module.data.api.TopicsRepository
import module.network.api.NetworkDataSource
import module.network.model.NetworkTopic
import javax.inject.Inject

class FakeTopicRepository @Inject constructor(
    private val dataSource: NetworkDataSource,
): TopicsRepository {

    override fun getTopics(): Flow<List<NetworkTopic>> = flow {
        emit(
            dataSource.getTopics()
        )
    }.flowOn(Dispatchers.IO)

    override fun getTopic(id: String): Flow<NetworkTopic> {
        return getTopics().map { it.first{ topic -> topic.id == id} }
    }
}