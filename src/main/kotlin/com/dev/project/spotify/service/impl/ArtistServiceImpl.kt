package com.dev.project.spotify.service.impl

import com.dev.project.spotify.client.ArtistClient
import com.dev.project.spotify.client.response.Artist
import com.dev.project.spotify.service.ArtistService
import kotlinx.coroutines.*
import org.springframework.stereotype.Service

@Service
class ArtistServiceImpl(
    private val artistClient: ArtistClient
) : ArtistService {
    override suspend fun getArtistInfo(spotifyId: String): Artist {
        return artistClient.getArtistInfo(spotifyId)
    }

    override suspend fun getAllArtistInfo(spotifyIds: List<String>): List<Artist> {
        val artists = mutableListOf<Artist>()
        withContext(Dispatchers.IO) {
            spotifyIds.map { id ->
                async {
                    artists.add(artistClient.getArtistInfo(id))
                }
            }.awaitAll()
        }
        return artists
    }

    override suspend fun getAllArtistsByGenres(genres: List<String>): List<Artist> {
        val artists =  withContext(Dispatchers.IO) {
            genres.map { genre ->
                async {
                    artistClient.getArtistsByGenre(genre)
                }
            }.awaitAll()
        }.flatten()

        val uniqueArtists = artists.distinctBy { it.id }

        return uniqueArtists.sortedByDescending { it.popularity }
    }
}