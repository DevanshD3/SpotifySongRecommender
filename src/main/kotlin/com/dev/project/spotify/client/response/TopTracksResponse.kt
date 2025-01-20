package com.dev.project.spotify.client.response

import com.fasterxml.jackson.annotation.JsonProperty

data class TopTracksResponse (
    val tracks : List<Track>
)

data class Track(
    val name: String,
    val id: String,
    val popularity: Int,
    @JsonProperty("external_urls")
    val externalUrls: ExternalUrls,
    val album: Album
)

data class ExternalUrls(
    val spotify: String
)

data class Album(
    val images: List<TrackImage>
)

data class TrackImage(
    val url: String
)

data class Song(
    val name: String,
    val id: String,
    val popularity: Int,
    val playUrl: String,
    val albumImageUrl: String?
)