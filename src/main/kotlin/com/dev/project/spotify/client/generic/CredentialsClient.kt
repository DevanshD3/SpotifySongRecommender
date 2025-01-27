package com.dev.project.spotify.client.generic

import com.dev.project.spotify.client.response.Credentials
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import java.lang.StringBuilder
import java.time.Instant

@Component
class CredentialsClient(
    @Value("\${spotify.client.id}") private val clientId: String,
    @Value("\${spotify.client.secret}") private val clientSecret: String,
    private val webClientBuilder: WebClient.Builder,
    private val objectMapper: ObjectMapper
) {

    private var cachedCredentials: Credentials? = null
    private var tokenExpirationTime: Instant? = null

    suspend fun getCredentials(): Credentials {
        val currentTime = Instant.now()
        if (cachedCredentials != null && tokenExpirationTime?.isAfter(currentTime) == true) {
            return cachedCredentials!!
        }
        val url = "https://accounts.spotify.com/api/token"
        val body = "grant_type=client_credentials&client_id=$clientId&client_secret=$clientSecret"

        val webClient = webClientBuilder.baseUrl("https://accounts.spotify.com").build()

        val response = webClient.post()
            .uri(url)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .body(BodyInserters.fromValue(body))
            .retrieve()
            .bodyToMono(String::class.java)
            .awaitSingle()

        val credentials = objectMapper.readValue(response, Credentials::class.java)

        cachedCredentials = credentials
        tokenExpirationTime = currentTime.plusSeconds(credentials.expiresIn!!)
        return credentials
    }

}