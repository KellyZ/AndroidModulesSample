package module.feature.plant

import android.text.method.LinkMovementMethod
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import module.feature.plant.databinding.ItemPlantDescriptionBinding

@Composable
fun PlantDetailScreen(
    plantId: String,
    plantDetailViewModel: PlantDetailViewModel = hiltViewModel(),
) {

    plantDetailViewModel.setPlantId(plantId)
    val plantUiState = plantDetailViewModel.plantUiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    when(plantUiState.value) {
        is PlantUiState.Loading ->{}
        is PlantUiState.Success -> {
            val plant = (plantUiState.value as PlantUiState.Success).plant
            Column(Modifier.verticalScroll(scrollState)) {
                AsyncImage(
                    model = plant.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(278.dp),
                    contentScale = ContentScale.Crop
                )

                PlantInfo(
                    name = plant.name,
                    wateringInterval = plant.wateringInterval,
                    description = plant.description)
            }

        }
    }

}

@Composable
fun PlantInfo(
    name: String,
    wateringInterval: Int,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(24.dp)) {
        Text(
            text = name,
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 16.dp
                )
                .align(Alignment.CenterHorizontally)
        )

        PlantDescrition(desc = description)
    }
}

@Composable
private fun PlantDescrition(desc: String) {
    AndroidViewBinding(ItemPlantDescriptionBinding::inflate) {
        plantDesc.text = HtmlCompat.fromHtml(
            desc,
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )
        plantDesc.movementMethod = LinkMovementMethod.getInstance()
        plantDesc.linksClickable = true
    }
}