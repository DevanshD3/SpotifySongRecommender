package com.dev.project.spotify.service

import com.dev.project.spotify.client.response.Artist
import com.dev.project.spotify.client.response.Song

interface ArtistService {
    suspend fun getArtistInfo( spotifyId: String): Artist
    suspend fun getAllArtistInfo( spotifyIds: List<String>): List<Artist>
    suspend fun getAllArtistsByGenres( genres: List<String>): List<Artist>
    suspend fun getTopSongsForArtists( artistIds: List<String>): List<Song>
}