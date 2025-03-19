package geeky.saif.composektordi.utility.loadImage

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun LoadImage(title: String) {
    AsyncImage(
        model = title,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(10.dp)),
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun LoadImage1(title: String) {
    AsyncImage(
        model = title,
        contentDescription = null,
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .clip(RoundedCornerShape(10.dp)),
        contentScale = ContentScale.FillBounds
    )
}