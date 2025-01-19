package com.dev.project.spotify.controller

import com.dev.project.spotify.client.response.Artist
import com.dev.project.spotify.service.ArtistService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/artist")
class ArtistController(
    private val artistService: ArtistService
) {

    @GetMapping("/info/{spotifyId}")
    suspend fun getArtistInfo(@PathVariable spotifyId: String): Artist {
        return withContext(Dispatchers.IO){
            artistService.getArtistInfo(spotifyId)
        }
    }
}