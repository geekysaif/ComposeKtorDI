package geeky.saif.composektordi.dataClass

import kotlinx.serialization.Serializable


@Serializable
data class Post(
    val id: Int,
    val title: String,
    val body: String,
    val reactions: Reactions,
    val views: Int,
    val userId: Int
)

@Serializable
data class Reactions(
    val likes: Int,
    val dislikes: Int
)

@Serializable
data class PostResponse(
    val posts: List<Post>
)

