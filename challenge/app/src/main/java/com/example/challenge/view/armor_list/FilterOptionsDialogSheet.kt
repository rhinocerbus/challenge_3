package com.example.challenge.view.armor_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.challenge.R
import com.example.challenge.view.abstracts.BottomSheetWrapHeight
import com.example.challenge.viewmodel.ArmorListViewModel

class FilterOptionsDialogSheet constructor(private val armorViewModel: ArmorListViewModel) : BottomSheetWrapHeight() {

    companion object {
        private const val TAG = "donate"

        fun show(fragmentManager: FragmentManager, armorListViewModel: ArmorListViewModel, themeColor: Int = -1): FilterOptionsDialogSheet {
            val fragment = FilterOptionsDialogSheet(armorListViewModel)
            fragment.show(fragmentManager, TAG)
            return fragment
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.sheet_filter_options, null)

        return view
    }
}