package com.example.challenge.model

import java.security.InvalidParameterException


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

enum class ElementType {
    FIRE,
    WATER,
    ICE,
    THUNDER,
    DRAGON
}

@kotlinx.serialization.Serializable
data class ArmorPiece(val id: Long, val type: String, val rank: String, val rarity: Int,
                      val defense: ArmorDefense, val resistances: ArmorResistances, val attributes: ArmorAttributes,
                      val name: String, val slots: ArrayList<ArmorSlot>, val skills: ArrayList<ArmorSkill>,
                      val armorSet: ArmorSet, val assets: ArmorAssets? = null, val crafting: Crafting) {

    fun hasResistances(elems: Set<ElementType>): Boolean {
        for(e in elems) {
            when(e) {
                ElementType.FIRE -> if(resistances.fire <= 0) return false
                ElementType.WATER -> if(resistances.water <= 0) return false
                ElementType.ICE -> if(resistances.ice <= 0) return false
                ElementType.THUNDER -> if(resistances.thunder <= 0) return false
                ElementType.DRAGON -> if(resistances.dragon <= 0) return false
            }
        }
        return true
    }

    fun hasDamage(elem: ElementType): Boolean {
        return when(elem) {
            ElementType.FIRE -> skills.map {
                it.modifiers.damageFire != null
            }.isNotEmpty()
            ElementType.WATER -> skills.map {
                it.modifiers.damageWater != null
            }.isNotEmpty()
            ElementType.ICE -> skills.map {
                it.modifiers.damageIce != null
            }.isNotEmpty()
            ElementType.THUNDER -> skills.map {
                it.modifiers.damageThunder != null
            }.isNotEmpty()
            ElementType.DRAGON -> skills.map {
                it.modifiers.damageDragon != null
            }.isNotEmpty()
        }
    }
}

@kotlinx.serialization.Serializable
data class ArmorDefense(val base: Int = 0,
                        val max: Int = 0,
                        val augmented: Int = 0)

@kotlinx.serialization.Serializable
data class ArmorResistances(val fire: Int = 0,
                            val water: Int = 0,
                            val ice: Int = 0,
                            val thunder: Int = 0,
                            val dragon: Int = 0)

@kotlinx.serialization.Serializable
data class ArmorAttributes(val requiredGender: String? = null)

@kotlinx.serialization.Serializable
data class ArmorSlot(val rank: Int = 0)

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
