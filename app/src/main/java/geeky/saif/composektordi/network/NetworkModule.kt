package geeky.saif.composektordi.network

import io.ktor.client.plugins.contentnegotiation.ContentNegotiation

import dagger.Module
import dagger.Provides
import dagger.Binds
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import geeky.saif.composektordi.utility.constants.AppConstants
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton
import io.ktor.serialization.kotlinx.json.json


@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    // Provide the HttpClient instance
    companion object {
        @Provides
        @Singleton
        fun provideKtorClient(): HttpClient {
            return HttpClient(OkHttp) {
                install(ContentNegotiation) {
                    json(Json {
                        ignoreUnknownKeys = true // To ignore any unknown keys in JSON response
                        isLenient = true // Allows lenient parsing of JSON
                         // Use type field to resolve polymorphic types, if necessary
                    })
                }

                // Set the base URL and default content type for requests
                defaultRequest {
                    url(AppConstants.BASE_URL) // Use the base URL from constants
                    contentType(ContentType.Application.Json) // Set content type for JSON requests
                }
            }
        }
    }

    // Binds ApiService to ApiServiceImpl for dependency injection
    @Binds
    @Singleton
    abstract fun bindApiService(apiServiceImpl: ApiServiceImpl): ApiService
}
