import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import geeky.saif.composektordi.R
import geeky.saif.composektordi.utility.constants.AppConstants

@Composable
fun GlobalBottomBar(
    selectedTab: String,
    onHomeClicked: () -> Unit,
    onProductClicked: () -> Unit,
    onSettingClicked: () -> Unit,
    onCategoryClicked: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        color = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomBarItem(
                title = "Home",
                iconRes = R.drawable.slider1,
                isSelected = selectedTab == AppConstants.HOME,
                onClick = onHomeClicked
            )

            BottomBarItem(
                title = "Product",
                iconRes = R.drawable.slider2,
                isSelected = selectedTab == AppConstants.PRODUCTS,
                onClick = onProductClicked
            )

            BottomBarItem(
                title = "Setting",
                iconRes = R.drawable.slider3,
                isSelected = selectedTab == "Setting",
                onClick = onSettingClicked
            )

            BottomBarItem(
                title = "Category",
                iconRes = R.drawable.slider4,
                isSelected = selectedTab == "Category",
                onClick = onCategoryClicked
            )
        }
    }
}

@Composable
fun BottomBarItem(
    title: String,
    iconRes: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = onClick) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier.size(24.dp),
                alpha = if (isSelected) 1f else 0.5f // Highlight selected tab
            )
        }
        Text(
            text = title,
            modifier = Modifier.padding(top = 3.dp),
            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}
