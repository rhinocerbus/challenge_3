package com.example.challenge.view.armor_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import com.example.challenge.R
import com.example.challenge.model.ElementType
import com.example.challenge.view.abstracts.BottomSheetWrapHeight
import com.example.challenge.viewmodel.ArmorListViewModel
import kotlinx.android.synthetic.main.sheet_filter_options.*

class FilterOptionsDialogSheet : BottomSheetWrapHeight() {

    companion object {
        private const val TAG = "FILTER"

        fun show(fragmentManager: FragmentManager): FilterOptionsDialogSheet {
            val fragment = FilterOptionsDialogSheet()
            fragment.show(fragmentManager, TAG)
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.sheet_filter_options
    }

    private val armorViewModel: ArmorListViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFilterState()

        res_fire.setOnCheckedChangeListener { buttonView, isChecked ->
            armorViewModel.updateFilterResistance(ElementType.FIRE, isChecked)
        }
        res_water.setOnCheckedChangeListener { buttonView, isChecked ->
            armorViewModel.updateFilterResistance(ElementType.WATER, isChecked)
        }
        res_ice.setOnCheckedChangeListener { buttonView, isChecked ->
            armorViewModel.updateFilterResistance(ElementType.ICE, isChecked)
        }
        res_thunder.setOnCheckedChangeListener { buttonView, isChecked ->
            armorViewModel.updateFilterResistance(ElementType.THUNDER, isChecked)
        }
        res_dragon.setOnCheckedChangeListener { buttonView, isChecked ->
            armorViewModel.updateFilterResistance(ElementType.DRAGON, isChecked)
        }

        has_skill.setOnCheckedChangeListener { buttonView, isChecked ->
            armorViewModel.updateFilterHasSkill(isChecked)
        }

        dmg_fire.setOnCheckedChangeListener { buttonView, isChecked ->
            armorViewModel.updateFilterDamage(ElementType.FIRE, isChecked)
        }
        dmg_water.setOnCheckedChangeListener { buttonView, isChecked ->
            armorViewModel.updateFilterDamage(ElementType.WATER, isChecked)
        }
        dmg_ice.setOnCheckedChangeListener { buttonView, isChecked ->
            armorViewModel.updateFilterDamage(ElementType.ICE, isChecked)
        }
        dmg_thunder.setOnCheckedChangeListener { buttonView, isChecked ->
            armorViewModel.updateFilterDamage(ElementType.THUNDER, isChecked)
        }
        dmg_dragon.setOnCheckedChangeListener { buttonView, isChecked ->
            armorViewModel.updateFilterDamage(ElementType.DRAGON, isChecked)
        }
    }

    private fun initFilterState() {
        res_fire.isChecked = armorViewModel.filterLiveData.value!!.resistTypes.contains(ElementType.FIRE)
        res_water.isChecked = armorViewModel.filterLiveData.value!!.resistTypes.contains(ElementType.WATER)
        res_ice.isChecked = armorViewModel.filterLiveData.value!!.resistTypes.contains(ElementType.ICE)
        res_thunder.isChecked = armorViewModel.filterLiveData.value!!.resistTypes.contains(ElementType.THUNDER)
        res_dragon.isChecked = armorViewModel.filterLiveData.value!!.resistTypes.contains(ElementType.DRAGON)

        has_skill.isChecked = armorViewModel.filterLiveData.value!!.hasSkill

        dmg_dragon.isChecked = armorViewModel.filterLiveData.value!!.damageTypes.contains(ElementType.FIRE)
        dmg_water.isChecked = armorViewModel.filterLiveData.value!!.damageTypes.contains(ElementType.WATER)
        dmg_ice.isChecked = armorViewModel.filterLiveData.value!!.damageTypes.contains(ElementType.ICE)
        dmg_thunder.isChecked = armorViewModel.filterLiveData.value!!.damageTypes.contains(ElementType.THUNDER)
        dmg_dragon.isChecked = armorViewModel.filterLiveData.value!!.damageTypes.contains(ElementType.DRAGON)
    }
}