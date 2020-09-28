package com.example.challenge.view.armor_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.example.challenge.R
import com.example.challenge.model.ArmorPiece
import com.example.challenge.view.abstracts.BottomSheetFixedHeight
import com.example.challenge.viewmodel.ArmorListViewModel
import kotlinx.android.synthetic.main.sheet_armor_details.*

class ArmorDetailsBottomSheet(private val armorListViewModel: ArmorListViewModel) : BottomSheetFixedHeight() {

    companion object {
        const val TAG = "ARMOR_DETAILS"

        fun show(fragmentManager: FragmentManager, armorListViewModel: ArmorListViewModel): ArmorDetailsBottomSheet {
            val fragment = ArmorDetailsBottomSheet(armorListViewModel)
            fragment.show(fragmentManager, TAG)
            return fragment
        }
    }

    lateinit var detailPiece: ArmorPiece

    val summaryAdapter: ArmorListAdapter = ArmorListAdapter()
    val setAdapter: ArmorListAdapter = ArmorListAdapter()
    lateinit var itemSummary: ArmorListAdapter.ArmorPieceViewHolder
    val selectionUpdate = MutableLiveData<ArmorPiece>()

    override fun getLayoutId(): Int {
        return R.layout.sheet_armor_details
    }

    override fun getScreenHeightRatio(): Float {
        return 0.75f
    }

    override fun getConetntViewGroup(): Int {
            return R.id.content
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailPiece = armorListViewModel.detailsPieceLiveData.value!!

        val glide = Glide.with(this)
        if(detailPiece.assets != null) {
            if(detailPiece.assets!!.imageFemale != null) {
                glide.load(detailPiece.assets!!.imageFemale).into(armor_image_left)
            } else {
                glide.load(R.drawable.img_armor_no).into(armor_image_left)
            }
            if(detailPiece.assets!!.imageMale != null) {
                glide.load(detailPiece.assets!!.imageMale).into(armor_image_right)
            } else {
                glide.load(R.drawable.img_armor_no).into(armor_image_right)
            }
        } else {
            glide.load(R.drawable.img_armor_no).into(armor_image_left)
            glide.load(R.drawable.img_armor_no).into(armor_image_right)
        }

        summary_recycler.adapter = summaryAdapter
        summaryAdapter.updateViewType(ArmorListAdapter.Companion.DISPLAYTYPE.ROWS, summary_recycler)
        summaryAdapter.updateData(arrayListOf(detailPiece))

        if(detailPiece.attributes.requiredGender != null) {
            attribute_group.visibility = View.VISIBLE
            attribute_gender.text = detailPiece.attributes.requiredGender
        } else {
            attribute_group.visibility = View.GONE
        }

        if(detailPiece.skills.isEmpty()) {
            skills_group.visibility = View.GONE
        } else {
            skills_group.visibility = View.VISIBLE
        }

        if(detailPiece.armorSet.bonus != null) {
            set_bonus.visibility = View.VISIBLE
            set_bonus.text = detailPiece.armorSet.bonus.toString()
        } else {
            set_bonus.visibility = View.GONE
        }

        set_recycler.adapter = setAdapter
        setAdapter.updateViewType(ArmorListAdapter.Companion.DISPLAYTYPE.ROWS, set_recycler)
        setAdapter.updateData(armorListViewModel.getSetPieces(detailPiece))
    }
}