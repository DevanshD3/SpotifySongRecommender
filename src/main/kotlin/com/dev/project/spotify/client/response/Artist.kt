package com.dev.project.spotify.client.response

data class ArtistsResponse(
    val artists: ArtistWrapper
)

data class ArtistWrapper(
    val items: List<Artist>
)

data class Artist (
    val name: String = "Default Artist",
    val genres: List<String> = listOf(),
    val id: String,
    val images: List<ArtistImage>,
    val popularity: Int
)

data class ArtistImage(
    val url: String,
    val height: Int,
    val width: Int
)