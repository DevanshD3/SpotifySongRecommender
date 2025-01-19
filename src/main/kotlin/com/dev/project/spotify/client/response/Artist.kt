package com.dev.project.spotify.client.response

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