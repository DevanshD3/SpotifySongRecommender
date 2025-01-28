package com.dev.project.spotify.service.impl

import com.dev.project.spotify.client.GenreClient
import com.dev.project.spotify.client.response.GenreDetails
import com.dev.project.spotify.service.GenreService
import jakarta.annotation.PostConstruct
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.cache.annotation.Cacheable
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service

@Service
class GenreServiceImpl(
    private val genreClient: GenreClient,
    private val applicationContext: ApplicationContext
): GenreService {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Cacheable("genres")
    override suspend fun getAllGenres(): List<GenreDetails> {
        val genres = genreClient.getAllGenres().categories.items.map {
            GenreDetails(it.name, it.icons.first().url)
        }
        return genres
    }

    @PostConstruct
    fun preloadGenres() {
        println("Preloading genres into cache...")
        coroutineScope.launch {
            runCatching {
                val proxy = applicationContext.getBean(GenreService::class.java)
                proxy.getAllGenres()
            }.onFailure { exception ->
                println("Failed to preload genres: ${exception.message}")
            }.onSuccess {
                println("Successfully preloaded genres into cache")
            }
        }
    }
}