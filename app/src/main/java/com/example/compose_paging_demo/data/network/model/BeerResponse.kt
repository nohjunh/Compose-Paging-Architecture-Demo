package com.example.compose_paging_demo.data.network.model

import com.example.compose_paging_demo.data.database.model.BeerEntity

class BeerResponse(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val first_brewed: String,
    val image_url: String?
)

fun BeerResponse.toBeerEntity(): BeerEntity {
    return BeerEntity(
        id = this.id,
        name = this.name,
        tagline = this.tagline,
        description = this.description,
        firstBrewed = this.first_brewed,
        imageUrl = this.image_url
    )
}
