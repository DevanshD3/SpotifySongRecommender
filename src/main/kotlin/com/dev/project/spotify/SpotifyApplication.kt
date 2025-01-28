package com.dev.project.spotify

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class SpotifyApplication

fun main(args: Array<String>) {
	runApplication<SpotifyApplication>(*args)
}
