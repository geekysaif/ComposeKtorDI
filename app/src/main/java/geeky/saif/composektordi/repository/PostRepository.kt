package geeky.saif.composektordi.repository

import geeky.saif.composektordi.dataClass.Post
import geeky.saif.composektordi.dataClass.ProductList
import geeky.saif.composektordi.network.ApiService
import javax.inject.Inject

class PostRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun fetchPosts(): List<Post> {
        return try {
            apiService.getPosts()
        } catch (e: Exception) {
            emptyList() // Handle errors gracefully
        }
    }


    suspend fun fetchProducts(): List<ProductList.Product> {
        return try {
            apiService.getProducts()
        } catch (e: Exception) {
            emptyList() // Handle errors gracefully
        }
    }
}
