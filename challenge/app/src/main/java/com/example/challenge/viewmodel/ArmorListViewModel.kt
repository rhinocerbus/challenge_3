package com.example.challenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge.model.ArmorListFilterCriteria
import com.example.challenge.model.ArmorPiece
import com.example.challenge.model.ArmorRepository
import com.example.challenge.model.ElementType
import kotlinx.coroutines.launch

class ArmorListViewModel : ViewModel() {

    companion object {
        enum class SortMethod {
            NAME,
            RARITY,
            RANK
        }

        enum class FilterType {
            TYPE
        }
    }

    var firstLoad = true
        private set
    private val armorListData: MutableList<ArmorPiece> = mutableListOf()
    private val _armorList = MutableLiveData<List<ArmorPiece>>()
    val armorList: LiveData<List<ArmorPiece>>
        get() = _armorList


    val filter = ArmorListFilterCriteria()


    private var detailPiece: ArmorPiece? = null
    private val _detailsPieceLiveData = MutableLiveData<ArmorPiece>()
    val detailsPieceLiveData: LiveData<ArmorPiece>
        get() = _detailsPieceLiveData

init {
    loadArmorData()
}

    fun loadArmorData() {
        viewModelScope.launch {
            armorListData.clear()

            val a = ArmorRepository.fetchArmorData(this)
            armorListData.addAll(a)
            firstLoad = false
            _armorList.postValue(armorListData)
        }
    }

    fun updateDetailPiece(detailPiece: ArmorPiece?) {
        this.detailPiece = detailPiece
        if(detailPiece != null)
            _detailsPieceLiveData.postValue(detailPiece)
    }

    fun updateFilterTerm(term: String?) {
        filter.name = term
        if(filter.isDefault()) {
            _armorList.postValue(armorListData)
            return
        }

        updateFilteredArmor()
    }

    fun updateFilterResistance(elem: ElementType, enabled: Boolean) {
        if(enabled) {
            if(filter.resistTypes.contains(elem)) return
            filter.resistTypes.add(elem)
            updateFilteredArmor()
        } else {
            if(!filter.resistTypes.contains(elem)) return
            filter.resistTypes.remove(elem)
            updateFilteredArmor()
        }
    }

    fun updateFilterDamage(elem: ElementType, enabled: Boolean) {
        if(enabled) {
            if(filter.damageTypes.contains(elem)) return
            filter.damageTypes.add(elem)
            updateFilteredArmor()
        } else {
            if(!filter.damageTypes.contains(elem)) return
            filter.damageTypes.remove(elem)
            updateFilteredArmor()
        }
    }

    fun updateFilterHasSkill(enabled: Boolean) {
        filter.hasSkill = enabled
        updateFilteredArmor()
    }

    private fun updateFilteredArmor() {
        var filtered = armorListData.toList()
        if(filter.name != null) {
            filtered = filtered.filter { it.name.contains(filter.name!!, true) }
        }
        if(filter.resistTypes.isNotEmpty()) {
            filtered = filtered.filter { it.hasResistances(filter.resistTypes) }
        }
        if(filter.hasSkill) {
            filtered = filtered.filter { it.skills.size > 0 }
        }
        if(filter.damageTypes.isNotEmpty()) {
            filtered = filtered.filter { it.hasDamage(filter.damageTypes) }
        }
        _armorList.postValue(filtered)
    }

    fun getSetPieces(piece: ArmorPiece): ArrayList<ArmorPiece> {
        val pieceIds = piece.armorSet.pieces
        pieceIds.remove(piece.id)
        val setPieces: ArrayList<ArmorPiece> = arrayListOf()
        for(p in armorListData) {
            if(pieceIds.contains(p.id)) {
                setPieces.add(p)
            }
        }
        return setPieces
    }

    override fun onCleared() {
        super.onCleared()
        armorListData.clear()
        firstLoad = true
    }
}