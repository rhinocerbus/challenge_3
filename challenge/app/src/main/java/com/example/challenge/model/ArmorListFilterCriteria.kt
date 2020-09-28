package com.example.challenge.model

data class ArmorListFilterCriteria(
    var name: String? = null,
    val resistTypes: MutableSet<ElementType> = mutableSetOf(),
    val damageTypes: MutableSet<ElementType> = mutableSetOf(),
    var hasSkill: Boolean = false
) {
    fun isDefault(): Boolean {
        return name == null &&
                resistTypes.isEmpty() &&
                damageTypes.isEmpty() &&
                !hasSkill
    }
}

/*
TODO
        test screen rotate (done)
        fix skills recycler shrink
        probably also applies to crafting
        gajau boots alpha doesnt show set piecesF

 */