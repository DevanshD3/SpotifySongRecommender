package com.dev.project.spotify.service

import com.dev.project.spotify.client.response.Artist
import com.dev.project.spotify.client.response.GenreDetails

interface GenreService {
    suspend fun getAllGenres(): List<GenreDetails>
    suspend fun getGenreArtistMapping(): Map<GenreDetails, List<Artist>>
}