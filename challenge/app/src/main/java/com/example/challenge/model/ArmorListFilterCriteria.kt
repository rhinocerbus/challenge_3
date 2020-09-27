package com.example.challenge.model

import android.util.ArraySet

data class ArmorListFilterCriteria(
    var name: String? = null,
    val resistTypes: MutableSet<ElementType> = mutableSetOf(),
    val damageTypes: MutableSet<ElementType> = mutableSetOf()/*,
    var includeFireRes: Boolean = false,
    var includeWaterRes: Boolean = false,
    var includeIceRes: Boolean = false,
    var includeThunderRes: Boolean = false,
    var includeDragonRes: Boolean = false,
    var includeFireDmg: Boolean = false,
    var includeWaterDmg: Boolean = false,
    var includeIceDmg: Boolean = false,
    var includeThunderDmg: Boolean = false,
    var includeDragonDmg: Boolean = false*/
) {
    fun isDefault(): Boolean {
        return name == null &&
                resistTypes.isEmpty() &&
                damageTypes.isEmpty()
                /*!includeFireRes &&
                !includeWaterRes &&
                !includeIceRes &&
                !includeThunderRes &&
                !includeDragonRes &&
                !includeFireDmg &&
                !includeWaterDmg &&
                !includeIceDmg &&
                !includeThunderDmg &&
                !includeDragonDmg*/
    }
}