package geeky.saif.composektordi.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import geeky.saif.composektordi.dataClass.Post
import geeky.saif.composektordi.dataClass.ProductList
import geeky.saif.composektordi.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: PostRepository
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _products = MutableStateFlow<List<ProductList.Product>>(emptyList())
    val products: StateFlow<List<ProductList.Product>> = _products

    suspend fun loadProduct() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null // Reset error on every load attempt
            try {
                val result = repository.fetchProducts()
                _products.value = result
            } catch (e: Exception) {
                Log.e("PostViewModel", "Error fetching posts", e)
                _error.value = e.localizedMessage // Set error message
            } finally {
                _loading.value = false
            }
        }
    }



}
