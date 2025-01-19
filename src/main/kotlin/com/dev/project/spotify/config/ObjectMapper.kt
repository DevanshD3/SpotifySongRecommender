package com.dev.project.spotify.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

val objectMapper: ObjectMapper = jacksonObjectMapper().apply {

    this.propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
    registerKotlinModule()
}