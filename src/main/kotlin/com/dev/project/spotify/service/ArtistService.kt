package com.dev.project.spotify.service

import com.dev.project.spotify.client.response.Artist

interface ArtistService {
    suspend fun getArtistInfo( spotifyId: String): Artist
}