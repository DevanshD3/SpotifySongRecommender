package com.dev.project.spotify.client

import com.dev.project.spotify.client.generic.CredentialsClient
import com.dev.project.spotify.client.generic.GenericRestClient
import com.dev.project.spotify.client.response.Artist
import com.dev.project.spotify.client.response.ArtistsResponse
import com.dev.project.spotify.client.response.Categories
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class GenreClient(
    @Value("\${spotify.base.url}") private val spotifyBaseUrl: String,
    private val credentialsClient: CredentialsClient,
    private val genericRestClient: GenericRestClient
) {
    suspend fun getAllGenres(): Categories {
        val url = "$spotifyBaseUrl/browse/categories?limit=50"
        val spotifyAuthToken = credentialsClient.getCredentials().accessToken
        val headers = mapOf("Authorization" to "Bearer $spotifyAuthToken")

        return genericRestClient.get(url, headers, Categories::class.java).map { it.body!! }.awaitSingle()
    }
}