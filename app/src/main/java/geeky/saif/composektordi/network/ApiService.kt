package geeky.saif.composektordi.network

import android.util.Log
import geeky.saif.composektordi.dataClass.Post
import geeky.saif.composektordi.dataClass.PostResponse
import geeky.saif.composektordi.dataClass.ProductList
import geeky.saif.composektordi.utility.constants.AppConstants
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

import javax.inject.Inject

interface ApiService {
    suspend fun getPosts(): List<Post>
    suspend fun getProducts(): List<ProductList.Product>
}

class ApiServiceImpl @Inject constructor(private val client: HttpClient) : ApiService {

    /*get post*/
    override suspend fun getPosts(): List<Post> {
        val url = "posts" // Assuming this is a relative URL
        val fullUrl = "${AppConstants.BASE_URL}/$url" // Concatenate base URL and the endpoint
        Log.d("ApiServiceImpl", "Full URL: $fullUrl") // Log the full URL

        try {
            // Make the GET request and receive the response as PostResponse
            val response: PostResponse = client.get(fullUrl).body()
            Log.d("ApiServiceImpl", "API Response: $response")

            // Return the list of posts from the response
            return response.posts
        } catch (e: Exception) {
            // Log the error if the API call fails
            Log.e("ApiServiceImpl", "Error in API call", e)
            throw e
        }
    }

    /*get products*/
    override suspend fun getProducts(): List<ProductList.Product> {
        val url = "products" // Assuming this is a relative URL
        val fullUrl = "${AppConstants.BASE_URL}/$url" // Concatenate base URL and the endpoint
        Log.d("ApiServiceImpl", "Full URL: $fullUrl") // Log the full URL

        try {
            // Make the GET request and receive the response as ProductList
            val response: ProductList = client.get(fullUrl).body()
            Log.d("ApiServiceImpl", "API Response: $response")

            // Return the list of products from the response or an empty list if products is null
            return response.products
        } catch (e: Exception) {
            // Log the error if the API call fails
            Log.e("ApiServiceImpl", "Error in API call", e)
            throw e
        }
    }



}


