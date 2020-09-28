package com.example.challenge.model

@kotlinx.serialization.Serializable
data class Crafting(val materials: ArrayList<Material>)

@kotlinx.serialization.Serializable
data class Material(val quantity: Int, val item: Item)

@kotlinx.serialization.Serializable
data class Item(
    val id: Int, val rarity: Int, val carryLimit: Int, val value: Int, val name: String,
    val description: String
)