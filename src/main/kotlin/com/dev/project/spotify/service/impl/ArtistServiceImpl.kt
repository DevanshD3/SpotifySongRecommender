package com.dev.project.spotify.service.impl

import com.dev.project.spotify.client.ArtistClient
import com.dev.project.spotify.client.response.Artist
import com.dev.project.spotify.service.ArtistService
import org.springframework.stereotype.Service

@Service
class ArtistServiceImpl(
    private val artistClient: ArtistClient
) : ArtistService {
    override suspend fun getArtistInfo(spotifyId: String): Artist {
        return artistClient.getArtistInfo(spotifyId)
    }

}