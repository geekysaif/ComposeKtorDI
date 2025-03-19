package geeky.saif.composektordi.viewScreens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import geeky.saif.composektordi.dataClass.Post
import geeky.saif.composektordi.utility.checkinternet.NoInternetUI
import geeky.saif.composektordi.utility.checkinternet.isConnectedToInternet
import geeky.saif.composektordi.utility.constants.AppConstants
import geeky.saif.composektordi.utility.loadImage.LoadImage
import geeky.saif.composektordi.viewmodels.PostViewModel

@Composable
fun PostScreenData(viewModel: PostViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val isConnected = context.isConnectedToInternet()

    // Show UI based on the connection status
    if (isConnected) {
        // Call loadPosts to fetch data when the composable is first shown
        LaunchedEffect(Unit) {
            viewModel.loadPosts()
        }
    }
    else {
        NoInternetUI()
    }

    val posts by viewModel.posts.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        error?.let {
            Text(
                text = "Error: $it",
                color = Color.Red,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            )
        }
        if (posts.isNotEmpty()) {
           // PostList(posts)
            // Show the PostList if there are posts
            if (posts.isNotEmpty()) {
                PostList(posts = posts, onItemClick = { post ->
                    // Handle item click, e.g., navigate to a detail screen
                   //  Log.d("Post Clicked", "Post Clicked: ${post}")
                    // You can perform any action when the post is clicked
                })
            }
        }
    }
}

@Composable
fun PostList(posts: List<Post>, onItemClick: (Post) -> Unit) {
    LazyColumn(modifier = Modifier.padding(10.dp)) {
        items(posts.size) { index ->
            val post = posts[index]
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onItemClick(post)
                        Log.d("Post Title", "Post Title: ${post.title}")
                    } // Make the item clickable
            ) {

                Text(text = "Title: ${post.title}", modifier = Modifier.padding(5.dp)
                    .clickable { onItemClick(post)
                        Log.d("Post Title", "Post Title: ${post.body}")},)


                Text(text = "Body: ${post.body}", modifier = Modifier.padding(5.dp)
                    .clickable { onItemClick(post)
                        Log.d("Post Body", "Post Body: ${post.body}")} )


                Box(modifier = Modifier.padding(9.dp)
                    .clickable {
                        Log.d("Post Image", "Post Image: ${post.userId}")
                    }) {
                    LoadImage(AppConstants.DEMO_IMAGE)
                    //Text(text = "Title: ${post.title}", modifier = Modifier.padding(5.dp))
                }
            }
        }
    }
}

