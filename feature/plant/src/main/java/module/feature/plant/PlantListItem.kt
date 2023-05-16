package module.feature.plant

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import module.data.model.Plant

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlantListItem(
    plant: Plant,
    onClick: ()->Unit
) {
    Card(
        onClick = onClick,
        elevation = 2.dp,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 26.dp)
    ) {
        Column(Modifier.fillMaxWidth()) {
            AsyncImage(
                model = plant.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(95.dp)
            )
            Text(
                text = plant.name,
                textAlign = TextAlign.Center,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
    }
}