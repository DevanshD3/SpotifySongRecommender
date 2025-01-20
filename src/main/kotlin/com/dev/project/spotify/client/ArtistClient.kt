package com.dev.project.spotify.client

import com.dev.project.spotify.client.generic.CredentialsClient
import com.dev.project.spotify.client.generic.GenericRestClient
import com.dev.project.spotify.client.response.Artist
import com.dev.project.spotify.client.response.ArtistsResponse
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ArtistClient(
    @Value("\${spotify.base.url}") private val spotifyBaseUrl: String,
    private val credentialsClient: CredentialsClient,
    private val genericRestClient: GenericRestClient
) {
    suspend fun getArtistInfo(spotifyId: String): Artist {
        val url = "$spotifyBaseUrl/artists/$spotifyId"
        val spotifyAuthToken = credentialsClient.getCredentials().accessToken
        val headers = mapOf("Authorization" to "Bearer $spotifyAuthToken")

        return genericRestClient.get(url, headers, Artist::class.java).map { it.body!! }.awaitSingle()
    }

    suspend fun getArtistsByGenre(genreId: String): List<Artist> {
        val url = "$spotifyBaseUrl/search?type=artist&q=genre:$genreId&limit=50"
        val spotifyAuthToken = credentialsClient.getCredentials().accessToken
        val headers = mapOf("Authorization" to "Bearer $spotifyAuthToken")
        return genericRestClient.get(url, headers, ArtistsResponse::class.java).map { it.body!!.artists.items }.awaitSingle()
    }
}