package com.dev.project.spotify.controller

import com.dev.project.spotify.client.response.Artist
import com.dev.project.spotify.client.response.Song
import com.dev.project.spotify.service.ArtistService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/artist")
class ArtistController(
    private val artistService: ArtistService
) {

    @CrossOrigin(origins = ["http://localhost:3000"])
    @GetMapping("/info/{spotifyId}")
    suspend fun getArtistInfo(@PathVariable spotifyId: String): Artist {
        return withContext(Dispatchers.IO){
            artistService.getArtistInfo(spotifyId)
        }
    }

    @CrossOrigin(origins = ["http://localhost:3000"])
    @GetMapping("/genres/{genres}")
    suspend fun getArtistsByGenres(@PathVariable genres: List<String>): List<Artist> {
        return withContext(Dispatchers.IO){
            artistService.getAllArtistsByGenres(genres)
        }
    }

    @CrossOrigin(origins = ["http://localhost:3000"])
    @GetMapping("/top-songs")
    suspend fun getTopSongsForArtists(@RequestParam artistIds: List<String>): List<Song> {
        return withContext(Dispatchers.IO) {
            artistService.getTopSongsForArtists(artistIds)
        }
    }

}