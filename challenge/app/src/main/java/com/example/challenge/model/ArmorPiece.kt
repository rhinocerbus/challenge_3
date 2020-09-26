package com.example.challenge.model


enum class ArmorType(val type: String) {
    HEAD("head"),
    CHEST("chest"),
    WAIST("waist"),
    GLOVES("gloves"),
    LEGS("legs")
}

enum class ArmorRank(val rank: String) {
    LOW("LOW"),
    HIGH("HIGH"),
    MASTER("MASTER")
}

@kotlinx.serialization.Serializable
data class ArmorPiece(val id: Long, val type: String, val rank: String, val rarity: Int,
                      val defense: ArmorDefense, val resistances: ArmorResistances, val attributes: ArmorAttributes,
                      val name: String, val slots: ArrayList<ArmorSlot>, val skills: ArrayList<ArmorSkill>,
                      val armorSet: ArmorSet, val assets: ArmorAssets? = null, val crafting: Crafting)

@kotlinx.serialization.Serializable
data class ArmorDefense(val base: Int, val max: Int, val augmented: Int)

@kotlinx.serialization.Serializable
data class ArmorResistances(val fire: Int, val water: Int, val ice: Int, val thunder: Int, val dragon: Int)

@kotlinx.serialization.Serializable
data class ArmorAttributes(val requiredGender: String? = null)

@kotlinx.serialization.Serializable
data class ArmorSlot(val rank: Int)

@kotlinx.serialization.Serializable
data class ArmorSkill(val id: Int, val level: Int, val modifiers: ArmorModifiers, val description: String,
                      val skill: Int, val skillName: String)

@kotlinx.serialization.Serializable
data class ArmorModifiers(val health: Int = -1, val attack: Int = -1, val defense: Int = -1,
                          val sharpnessBonus: Int = -1, val affinity: Int = -1,
                          val damageFire: String? = null, val damageWater: String? = null,
                          val damageIce: String? = null, val damageThunder: String? = null, val damageDragon: String? = null,
                          val resistFire: String? = null, val resistWater: String? = null,
                          val resistIce: String? = null, val resistThunder: String? = null, val resistDragon: String? = null)

@kotlinx.serialization.Serializable
data class ArmorSet(val id: Int, val rank: String, val name: String, val pieces: IntArray,
                    val bonus: Int? = -1)

@kotlinx.serialization.Serializable
data class ArmorAssets(val imageMale: String?, val imageFemale: String?)
