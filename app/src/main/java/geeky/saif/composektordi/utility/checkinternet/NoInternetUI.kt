package geeky.saif.composektordi.utility.checkinternet

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import geeky.saif.composektordi.R

@Composable
fun NoInternetUI() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoadImageFromDrawable()
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "No Internet Connection",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
fun LoadImageFromDrawable() {
    Image(
        painter = painterResource(id = R.drawable.app_icon), // Replace with your drawable resource name
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(10.dp)),
        contentScale = ContentScale.FillBounds
    )
}
