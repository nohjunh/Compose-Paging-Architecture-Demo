package com.example.compose_paging_demo.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.compose_paging_demo.domain.model.Beer

@Entity(
    tableName = "beer_resource",
)
data class BeerEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val firstBrewed: String,
    val imageUrl: String?
)

fun BeerEntity.toBeer(): Beer {
    return Beer(
        id = this.id,
        name = this.name,
        tagline = this.tagline,
        description = this.description,
        firstBrewed = this.firstBrewed,
        imageUrl = this.imageUrl
    )
}
