package com.dev.project.spotify.service.impl

import com.dev.project.spotify.client.GenreClient
import com.dev.project.spotify.client.response.GenreDetails
import com.dev.project.spotify.service.GenreService
import org.springframework.stereotype.Service

@Service
class GenreServiceImpl(
    private val genreClient: GenreClient
): GenreService {
    override suspend fun getAllGenres(): List<GenreDetails> {
        val genres = genreClient.getAllGenres().categories.items.map {
            GenreDetails(it.name, it.icons.first().url)
        }
        return genres
    }
}