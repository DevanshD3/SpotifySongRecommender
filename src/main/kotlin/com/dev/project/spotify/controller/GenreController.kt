package com.dev.project.spotify.controller

import com.dev.project.spotify.client.response.GenreDetails
import com.dev.project.spotify.service.GenreService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/genre")
class GenreController(
    private val genreService: GenreService
) {

    @CrossOrigin(origins = ["http://localhost:3000"])
    @GetMapping("/all")
    suspend fun getAllGenres(): List<GenreDetails> {
        return genreService.getAllGenres()
    }
}