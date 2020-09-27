package com.example.challenge.view.armor_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import com.example.challenge.R
import com.example.challenge.model.ArmorPiece
import com.example.challenge.view.abstracts.BottomSheetFixedHeight
import com.example.challenge.viewmodel.ArmorListViewModel
import kotlinx.android.synthetic.main.sheet_armor_details.*

class ArmorDetailsBottomSheet(private val armorListViewModel: ArmorListViewModel, private val detailPiece: ArmorPiece) : BottomSheetFixedHeight() {

    companion object {
        const val TAG = "episode_details"

        fun show(fragmentManager: FragmentManager, armorListViewModel: ArmorListViewModel, detailPiece: ArmorPiece): ArmorDetailsBottomSheet {
            val fragment = ArmorDetailsBottomSheet(armorListViewModel, detailPiece)
            fragment.show(fragmentManager, TAG)
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.sheet_armor_details
    }

    override fun getScreenHeightRatio(): Float {
        return 0.75f
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        description.text = "lkjsbadlgibsadgibasdpgiubsadgih usadgiuh sad8pguh osdaiuhgp8uhsdph usphgdu psa8duhgpsaiuhdgph sapdguhp9s8dhg p998hsdpghsapdguh psdaghpsdghupsa dgp9 hsdpg9hspadghu psadugh psahdug  "
    }
}