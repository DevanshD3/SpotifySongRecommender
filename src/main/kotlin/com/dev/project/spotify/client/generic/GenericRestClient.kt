package com.dev.project.spotify.client.generic

import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class GenericRestClient(
    private val webClient: WebClient.Builder
) {
    fun <T> get(url: String, headers: Map<String, String> = emptyMap(), responseType: Class<T>): Mono<ResponseEntity<T>> {
        return webClient.build()
            .method(HttpMethod.GET)
            .uri(url)
            .headers { httpHeaders ->
                headers.forEach { (key, value) -> httpHeaders[key] = value }
            }
            .retrieve()
            .toEntity(responseType)
    }

    fun <T, R : Any> post(url: String, body: R, headers: Map<String, String> = emptyMap(), responseType: Class<T>): Mono<ResponseEntity<T>> {
        return webClient.build()
            .method(HttpMethod.POST)
            .uri(url)
            .headers { httpHeaders ->
                headers.forEach { (key, value) -> httpHeaders[key] = value }
            }
            .bodyValue(body)
            .retrieve()
            .toEntity(responseType)
    }

    fun <T, R : Any> put(url: String, body: R, headers: Map<String, String> = emptyMap(), responseType: Class<T>): Mono<ResponseEntity<T>> {
        return webClient.build()
            .method(HttpMethod.PUT)
            .uri(url)
            .headers { httpHeaders ->
                headers.forEach { (key, value) -> httpHeaders[key] = value }
            }
            .bodyValue(body)
            .retrieve()
            .toEntity(responseType)
    }
}