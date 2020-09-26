package com.example.challenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge.model.ArmorPiece
import com.example.challenge.model.ArmorRepository
import kotlinx.coroutines.launch
import kotlinx.serialization.ImplicitReflectionSerializer

@ImplicitReflectionSerializer
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


    private var filterTerm: String? = null

    fun loadArmorData() {
        viewModelScope.launch {
            armorListData.clear()

            val a = ArmorRepository.fetchArmorData(this)
            armorListData.addAll(a)
            firstLoad = false
            _armorList.postValue(armorListData)
        }
    }

    fun updateFilterTerm(term: String?) {
        if(filterTerm == term) return

        filterTerm = term
        if(filterTerm == null) {
            _armorList.postValue(armorListData)
            return
        }

        val filtered = armorListData.filter {
            it.name.contains(filterTerm!!, true)
        }
        _armorList.postValue(filtered)
    }

    private fun updateFilteredArmor() {

    }

    override fun onCleared() {
        super.onCleared()
        armorListData.clear()
        firstLoad = true
    }
}