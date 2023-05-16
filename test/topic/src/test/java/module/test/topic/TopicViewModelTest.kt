package module.test.topic

import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import module.feature.topic.TopicViewModel
import module.feature.topic.TopicsUiState
import module.network.model.NetworkTopic
import module.test.common.util.MainDispatcherRule
import module.test.data.TestTopicRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class TopicViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val topicRepository = TestTopicRepository()
    private lateinit var viewModel: TopicViewModel

    @Before
    fun setup() {
        viewModel = TopicViewModel(topicRepository)
    }

    @Test
    fun matchsTopicsFromRepository() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.topicsUiState.collect()
        }

        assertIs<TopicsUiState.Loading>(viewModel.topicsUiState.value)
        topicRepository.sendTopics(testInputTopics)
        val topicFromRepository = topicRepository.getTopic(testInputTopics[0].id).first()
        var topicsFromViewModel: List<NetworkTopic> = (viewModel.topicsUiState.value as TopicsUiState.Success).topics
        assertEquals(topicFromRepository, topicsFromViewModel.find { topic-> topic.id == testInputTopics[0].id })
        collectJob.cancel()
    }
}

private const val TOPIC_1_NAME = "Android Studio"
private const val TOPIC_2_NAME = "Build"
private const val TOPIC_3_NAME = "Compose"
private const val TOPIC_SHORT_DESC = "At vero eos et accusamus."
private const val TOPIC_LONG_DESC = "At vero eos et accusamus et iusto odio dignissimos ducimus."
private const val TOPIC_URL = "URL"
private const val TOPIC_IMAGE_URL = "Image URL"

private val testInputTopics = listOf(
    NetworkTopic(
        id = "0",
        name = TOPIC_1_NAME,
        shortDescription = TOPIC_SHORT_DESC,
        longDescription = TOPIC_LONG_DESC,
        url = TOPIC_URL,
        imageUrl = TOPIC_IMAGE_URL,
    ),
    NetworkTopic(
        id = "1",
        name = TOPIC_2_NAME,
        shortDescription = TOPIC_SHORT_DESC,
        longDescription = TOPIC_LONG_DESC,
        url = TOPIC_URL,
        imageUrl = TOPIC_IMAGE_URL,
    ),
    NetworkTopic(
        id = "2",
        name = TOPIC_3_NAME,
        shortDescription = TOPIC_SHORT_DESC,
        longDescription = TOPIC_LONG_DESC,
        url = TOPIC_URL,
        imageUrl = TOPIC_IMAGE_URL,
    ),
)

private val testOutputTopics = listOf(
    NetworkTopic(
        id = "0",
        name = TOPIC_1_NAME,
        shortDescription = TOPIC_SHORT_DESC,
        longDescription = TOPIC_LONG_DESC,
        url = TOPIC_URL,
        imageUrl = TOPIC_IMAGE_URL,
    ),
    NetworkTopic(
        id = "1",
        name = TOPIC_2_NAME,
        shortDescription = TOPIC_SHORT_DESC,
        longDescription = TOPIC_LONG_DESC,
        url = TOPIC_URL,
        imageUrl = TOPIC_IMAGE_URL,
    ),
    NetworkTopic(
        id = "2",
        name = TOPIC_3_NAME,
        shortDescription = TOPIC_SHORT_DESC,
        longDescription = TOPIC_LONG_DESC,
        url = TOPIC_URL,
        imageUrl = TOPIC_IMAGE_URL,
    ),
)