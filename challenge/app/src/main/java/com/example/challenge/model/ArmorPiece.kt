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

enum class ElementType {
    FIRE,
    WATER,
    ICE,
    THUNDER,
    DRAGON
}

@kotlinx.serialization.Serializable
data class ArmorPiece(
    val id: Int, val type: String, val rank: String, val rarity: Int,
    val defense: ArmorDefense, val resistances: ArmorResistances, val attributes: ArmorAttributes,
    val name: String, val slots: ArrayList<ArmorSlot>, val skills: ArrayList<ArmorSkill>,
    val armorSet: ArmorSet, val assets: ArmorAssets? = null, val crafting: Crafting
) {

    init {
        if (skills.size == 0) {
            print("Asdf")
        }
    }

    fun hasResistances(elems: Set<ElementType>): Boolean {
        for (e in elems) {
            when (e) {
                ElementType.FIRE -> if (resistances.fire <= 0) return false
                ElementType.WATER -> if (resistances.water <= 0) return false
                ElementType.ICE -> if (resistances.ice <= 0) return false
                ElementType.THUNDER -> if (resistances.thunder <= 0) return false
                ElementType.DRAGON -> if (resistances.dragon <= 0) return false
            }
        }
        return true
    }

    fun hasDamage(elems: Set<ElementType>): Boolean {
        if (skills.size == 0) return false
        val skillsCopy = skills.clone() as ArrayList<ArmorSkill>
        for (e in elems) {
            when (e) {
                ElementType.FIRE -> if (skillsCopy.dropWhile {
                        it.modifiers.damageFire == null
                    }.isEmpty()) return false
                ElementType.WATER -> if (skillsCopy.dropWhile {
                        it.modifiers.damageWater == null
                    }.isEmpty()) return false
                ElementType.ICE -> if (skillsCopy.dropWhile {
                        it.modifiers.damageIce == null
                    }.isEmpty()) return false
                ElementType.THUNDER -> if (skillsCopy.dropWhile {
                        it.modifiers.damageThunder == null
                    }.isEmpty()) return false
                ElementType.DRAGON -> if (skillsCopy.dropWhile {
                        it.modifiers.damageDragon == null
                    }.isEmpty()) return false
            }
        }
        return true
    }
}

@kotlinx.serialization.Serializable
data class ArmorDefense(
    val base: Int = 0,
    val max: Int = 0,
    val augmented: Int = 0
)

@kotlinx.serialization.Serializable
data class ArmorResistances(
    val fire: Int = 0,
    val water: Int = 0,
    val ice: Int = 0,
    val thunder: Int = 0,
    val dragon: Int = 0
)

@kotlinx.serialization.Serializable
data class ArmorAttributes(val requiredGender: String? = null)

@kotlinx.serialization.Serializable
data class ArmorSlot(val rank: Int = 0)

@kotlinx.serialization.Serializable
data class ArmorSkill(
    val id: Int, val level: Int, val modifiers: ArmorModifiers, val description: String,
    val skill: Int, val skillName: String
)

@kotlinx.serialization.Serializable
data class ArmorModifiers(
    val health: Int = -1,
    val attack: Int = -1,
    val defense: Int = -1,
    val sharpnessBonus: Int = -1,
    val affinity: Int = -1,
    val damageFire: String? = null,
    val damageWater: String? = null,
    val damageIce: String? = null,
    val damageThunder: String? = null,
    val damageDragon: String? = null,
    val resistFire: String? = null,
    val resistWater: String? = null,
    val resistIce: String? = null,
    val resistThunder: String? = null,
    val resistDragon: String? = null
) {

    fun getActiveModierStrings(): Pair<String, String>? {
        var names = ""
        var values = ""
        if (health > 0) {
            names += "/nHealth"
            values += "/n${health}"
        }
        if (attack > 0) {
            names += "/nAttack"
            values += "/n${attack}"
        }
        if (defense > 0) {
            names += "/nDefense"
            values += "/n${defense}"
        }
        if (sharpnessBonus > 0) {
            names += "/nSharpness"
            values += "/n${sharpnessBonus}"
        }
        if (affinity > 0) {
            names += "/nAffinity"
            values += "/n${affinity}"
        }
        if (damageFire != null) {
            names += "/nFire damage"
            values += "/n${damageFire}"
        }
        if (damageWater != null) {
            names += "/nWater damage"
            values += "/n${damageWater}"
        }
        if (damageIce != null) {
            names += "/nIce damage"
            values += "/n${damageIce}"
        }
        if (damageThunder != null) {
            names += "/nThunder damage"
            values += "/n${damageThunder}"
        }
        if (damageDragon != null) {
            names += "/nDragon damage"
            values += "/n${damageDragon}"
        }
        if (resistFire != null) {
            names += "/nFire resist"
            values += "/n${resistFire}"
        }
        if (resistWater != null) {
            names += "/nFire resist"
            values += "/n${resistWater}"
        }
        if (resistIce != null) {
            names += "/nFire resist"
            values += "/n${resistIce}"
        }
        if (resistThunder != null) {
            names += "/nFire resist"
            values += "/n${resistThunder}"
        }
        if (resistDragon != null) {
            names += "/nFire resist"
            values += "/n${resistDragon}"
        }
        if (names.isNotEmpty()) {
            //indiscriminate newline, in case of multiple, can ignore order
            names = names.drop(2)
            values = values.drop(2)
            return Pair(names, values)
        }
        return null
    }
}

@kotlinx.serialization.Serializable
data class ArmorSet(
    val id: Int, val rank: String, val name: String, val pieces: ArrayList<Int>,
    val bonus: Int? = -1
)

@kotlinx.serialization.Serializable
data class ArmorAssets(val imageMale: String?, val imageFemale: String?)
