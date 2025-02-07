package com.dev.project.spotify.service.impl

import com.dev.project.spotify.client.GenreClient
import com.dev.project.spotify.client.response.Artist
import com.dev.project.spotify.client.response.GenreDetails
import com.dev.project.spotify.service.ArtistService
import com.dev.project.spotify.service.GenreService
import jakarta.annotation.PostConstruct
import kotlinx.coroutines.*
import org.springframework.cache.annotation.Cacheable
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service

@Service
class GenreServiceImpl(
    private val genreClient: GenreClient,
    private val applicationContext: ApplicationContext,
    private val artistService: ArtistService
): GenreService {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override suspend fun getAllGenres(): List<GenreDetails> {
        val genres = genreClient.getAllGenres().categories.items.map {
            GenreDetails(it.name, it.icons.first().url)
        }
        return genres
    }

    @PostConstruct
    fun preloadGenresArtistMapping() {
        // TODO can use logs too but meh...
        println("Preloading genre-artist mapping into cache...")
        coroutineScope.launch {
            runCatching {
                val proxy = applicationContext.getBean(GenreService::class.java)
                proxy.getGenreArtistMapping()
            }.onFailure { exception ->
                println("Failed to preload genre-artist mapping: ${exception.message}")
            }.onSuccess {
                println("Successfully preloaded genre-artist mapping into cache")
            }
        }
    }

    @Cacheable("genre-artist-mapping")
    override suspend fun getGenreArtistMapping(): Map<GenreDetails, List<Artist>> {
        return withContext(Dispatchers.IO){
            val genres = getAllGenres()
            val artistsByGenre = genres.map { genre ->
                async {
                    genre to artistService.getAllArtistsByGenres(listOf(genre.name))
                }
            }.awaitAll().toMap()
            artistsByGenre.filterValues { it.isNotEmpty() }
        }
    }
}