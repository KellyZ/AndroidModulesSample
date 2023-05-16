package module.feature.topic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import module.network.model.NetworkTopic

@Composable
internal fun TopicScreen(
    viewModel: TopicViewModel = hiltViewModel(),
) {
    val topicsUiState by viewModel.topicsUiState.collectAsStateWithLifecycle()
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 8.dp),
    ) {
        when(topicsUiState){
            TopicsUiState.Loading -> {}
            TopicsUiState.Error -> {}
            is TopicsUiState.Success -> {
                val topics = (topicsUiState as TopicsUiState.Success).topics
                items(topics ,
                    key = {topic -> topic.id }
                ) {item->
                    TopicItem(topic = item, modifier = Modifier.wrapContentHeight())
                }
            }
        }

    }
}


@Composable
fun TopicItem(
    topic: NetworkTopic,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 16.dp)
    ) {
        if (topic.imageUrl.isEmpty()) {
            Icon(
                modifier = modifier
                    .background(MaterialTheme.colors.surface)
                    .padding(4.dp),
                imageVector = Icons.Default.Person,
                contentDescription = null
            )
        } else {
            AsyncImage(
                model = topic.imageUrl,
                contentDescription = null,
                colorFilter = null,
                modifier = Modifier.size(64.dp))
        }
        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier) {
            Text(
                text = topic.name,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.padding(
                    vertical = if (topic.shortDescription.isEmpty()) 0.dp else 4.dp,
                )
            )
            if (topic.shortDescription.isNotEmpty()) {
                Text(
                    text = topic.shortDescription,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}