package com.dev.project.spotify.client.response


import com.fasterxml.jackson.annotation.JsonProperty

data class Credentials(
    @JsonProperty("access_token")
    val accessToken: String?,

    @JsonProperty("token_type")
    val tokenType: String?,

    @JsonProperty("expires_in")
    val expiresIn: Long?
)