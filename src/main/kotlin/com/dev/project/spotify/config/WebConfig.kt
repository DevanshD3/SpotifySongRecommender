package com.dev.project.spotify.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**") // Allow all paths to be accessed
            .allowedOrigins("http://localhost:3000") // Allow requests from frontend
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow these HTTP methods
            .allowedHeaders("*") // Allow any headers
            .allowCredentials(true)
    }
}