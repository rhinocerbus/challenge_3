package com.example.challenge.view.armor_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import com.example.challenge.R
import com.example.challenge.model.ElementType
import com.example.challenge.view.abstracts.BottomSheetWrapHeight
import com.example.challenge.viewmodel.ArmorListViewModel
import kotlinx.android.synthetic.main.sheet_filter_options.*

/**
 *
 * TODO:
 * - add dynamic range filtering (rarity, level, etc.)
 * - expand skill type filtering
 * -- optimize to crawl armor skills to populate skill types
 * - add sort options
 */

class FilterOptionsDialogSheet : BottomSheetWrapHeight() {

    companion object {
        private const val TAG = "FILTER"

        fun show(fragmentManager: FragmentManager): FilterOptionsDialogSheet {
            val fragment = FilterOptionsDialogSheet()
            fragment.show(fragmentManager, TAG)
            return fragment
        }
    }

    private val armorViewModel: ArmorListViewModel  by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sheet_filter_options, null)
    }

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
        res_fire.isChecked = armorViewModel.filter.resistTypes.contains(ElementType.FIRE)
        res_water.isChecked = armorViewModel.filter.resistTypes.contains(ElementType.WATER)
        res_ice.isChecked = armorViewModel.filter.resistTypes.contains(ElementType.ICE)
        res_thunder.isChecked = armorViewModel.filter.resistTypes.contains(ElementType.THUNDER)
        res_dragon.isChecked = armorViewModel.filter.resistTypes.contains(ElementType.DRAGON)

        has_skill.isChecked = armorViewModel.filter.hasSkill

        dmg_dragon.isChecked = armorViewModel.filter.damageTypes.contains(ElementType.FIRE)
        dmg_water.isChecked = armorViewModel.filter.damageTypes.contains(ElementType.WATER)
        dmg_ice.isChecked = armorViewModel.filter.damageTypes.contains(ElementType.ICE)
        dmg_thunder.isChecked = armorViewModel.filter.damageTypes.contains(ElementType.THUNDER)
        dmg_dragon.isChecked = armorViewModel.filter.damageTypes.contains(ElementType.DRAGON)
    }
}