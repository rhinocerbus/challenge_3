package com.example.challenge.view.armor_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.challenge.R
import com.example.challenge.model.ElementType
import com.example.challenge.view.abstracts.BottomSheetWrapHeight
import com.example.challenge.viewmodel.ArmorListViewModel
import kotlinx.android.synthetic.main.sheet_filter_options.*

class FilterOptionsDialogSheet constructor(private val armorViewModel: ArmorListViewModel) : BottomSheetWrapHeight() {

    companion object {
        private const val TAG = "FILTER"

        fun build(armorListViewModel: ArmorListViewModel, themeColor: Int = -1): FilterOptionsDialogSheet {
            val fragment = FilterOptionsDialogSheet(armorListViewModel)
            return fragment
        }

        fun show(farg: FilterOptionsDialogSheet, fragmentManager: FragmentManager) {
            farg.show(fragmentManager, TAG)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sheet_filter_options, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
}