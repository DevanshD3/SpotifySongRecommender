package com.dev.project.spotify.client.response

data class Categories(
    val categories: CategoriesObject
)

data class CategoriesObject(
    val items: List<Category>
)

data class Category (
    val id : String,
    val icons: List<CategoryImage>,
    val name: String
)


data class CategoryImage(
    val height: Int,
    val url: String,
    val width: Int
)

