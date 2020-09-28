package com.example.challenge.view.armor_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.challenge.R
import com.example.challenge.model.ArmorPiece
import com.example.challenge.view.abstracts.BottomSheetFixedHeight
import com.example.challenge.viewmodel.ArmorListViewModel
import kotlinx.android.synthetic.main.sheet_armor_details.*

class ArmorDetailsBottomSheet : BottomSheetFixedHeight() {

    companion object {
        private const val TAG = "ARMOR_DETAILS"

        fun show(fragmentManager: FragmentManager) {
            if(fragmentManager.findFragmentByTag(TAG) != null) {
                return
            }
            val fragment = ArmorDetailsBottomSheet()
            fragment.show(fragmentManager, TAG)
        }
    }

    private val armorViewModel: ArmorListViewModel by activityViewModels()
    lateinit var detailPiece: ArmorPiece

    val summaryAdapter: ArmorListAdapter = ArmorListAdapter()
    val setAdapter: ArmorListAdapter = ArmorListAdapter()
    val skillsAdapter: ArmorSkillAdapter = ArmorSkillAdapter()
    val craftingAdapter: CraftingAdapter = CraftingAdapter()

    override fun getLayoutId(): Int {
        return R.layout.sheet_armor_details
    }

    override fun getScreenHeightRatio(): Float {
        return 0.75f
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailPiece = armorViewModel.detailsPieceLiveData.value!!
        armorViewModel.detailsPieceLiveData.observe (this, Observer {
            detailPiece = it
            bindUI()
            scrollview.smoothScrollTo(0, 0)
        })

        summary_recycler.adapter = summaryAdapter
        summaryAdapter.updateViewType(ArmorListAdapter.Companion.DISPLAYTYPE.ROWS, summary_recycler)

        crafting_recycler.adapter = craftingAdapter
        craftingAdapter.updateViewType(crafting_recycler)

        setAdapter.selectionLiveData.observe(this, Observer {
            armorViewModel.updateDetailPiece(it)
        })
        set_recycler.adapter = setAdapter
        setAdapter.updateViewType(ArmorListAdapter.Companion.DISPLAYTYPE.ROWS, set_recycler)
    }

    private fun bindUI() {
        val glide = Glide.with(this)
        if (detailPiece.assets != null) {
            if (detailPiece.assets!!.imageMale != null) {
                glide.load(detailPiece.assets!!.imageMale).thumbnail(Glide.with(this).load(R.raw.cat2))
                    .into(armor_image_left)
            } else {
                glide.load(R.drawable.img_armor_no).into(armor_image_right)
            }
            if (detailPiece.assets!!.imageFemale != null) {
                glide.load(detailPiece.assets!!.imageFemale).thumbnail(Glide.with(this).load(R.raw.cat2))
                    .into(armor_image_right)
            } else {
                glide.load(R.drawable.img_armor_no).into(armor_image_left)
            }
        } else {
            glide.load(R.drawable.img_armor_no).into(armor_image_left)
            glide.load(R.drawable.img_armor_no).into(armor_image_right)
        }

        summaryAdapter.updateData(arrayListOf(detailPiece))

        if (detailPiece.attributes.requiredGender != null) {
            attribute_group.visibility = View.VISIBLE
            attribute_gender.text = detailPiece.attributes.requiredGender
        } else {
            attribute_group.visibility = View.GONE
        }

        if (detailPiece.skills.isEmpty()) {
            skills_group.visibility = View.GONE
        } else {
            skills_group.visibility = View.VISIBLE
            skills_recycler.adapter = skillsAdapter
            skillsAdapter.updateViewType(skills_recycler)
            skillsAdapter.updateData(detailPiece.skills)
        }

        craftingAdapter.updateData(detailPiece.crafting)

        if (detailPiece.armorSet.bonus != null) {
            set_bonus_group.visibility = View.VISIBLE
            set_bonus.text = detailPiece.armorSet.bonus.toString()
        } else {
            set_bonus_group.visibility = View.GONE
        }

        setAdapter.updateData(armorViewModel.getSetPieces(detailPiece))
    }
}